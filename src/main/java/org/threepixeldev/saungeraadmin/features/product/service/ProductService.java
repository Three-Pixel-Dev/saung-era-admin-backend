package org.threepixeldev.saungeraadmin.features.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.threepixeldev.saungeraadmin.features.product.dto.ProductRequest;
import org.threepixeldev.saungeraadmin.features.product.dto.ProductResponse;
import org.threepixeldev.saungeraadmin.features.product.mapper.ProductMapper;
import org.threepixeldev.saungeraadmin.shared.data.model.Category;
import org.threepixeldev.saungeraadmin.shared.data.model.Product;
import org.threepixeldev.saungeraadmin.shared.data.model.ProductCategory;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CategoryJpaRepository;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.ProductCategoryJpaRepository;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.ProductJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private static final String CACHE_NAME = "products";
    private static final String CACHE_KEY_ALL = "'all'";
    private static final String CACHE_KEY_BY_ID = "'product:' + #id";

    private final ProductJpaRepository productRepository;
    private final CategoryJpaRepository categoryRepository;
    private final ProductCategoryJpaRepository productCategoryRepository;
    private final ProductMapper productMapper;

    @Cacheable(value = CACHE_NAME, key = CACHE_KEY_ALL, unless = "#result.isEmpty()")
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .filter(p -> p.getDeletedAt() == null)
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable(value = CACHE_NAME, key = CACHE_KEY_BY_ID, unless = "#result == null")
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toResponse(product);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public ProductResponse createProduct(ProductRequest request, Long createdBy) {
        Product product = new Product();
        updateProductFields(product, request);
        product.setCreatedBy(createdBy);
        product.setUpdatedBy(createdBy);

        Product savedProduct = productRepository.save(product);

        // Handle Categories Manually for MasterEntity fields
        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            saveProductCategories(savedProduct, request.getCategoryIds(), createdBy);
        }

        // Refetch or return mapped response
        return productMapper.toResponse(savedProduct);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public ProductResponse updateProduct(Long id, ProductRequest request, Long updatedBy) {
        Product product = productRepository.findById(id)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        updateProductFields(product, request);
        product.setUpdatedBy(updatedBy);

        Product savedProduct = productRepository.save(product);

        if (request.getCategoryIds() != null) {
            // Delete old links
            productCategoryRepository.deleteByProductId(savedProduct.getId());
            // Add new links
            saveProductCategories(savedProduct, request.getCategoryIds(), updatedBy);
        }

        return productMapper.toResponse(productRepository.findById(id).orElse(savedProduct));
    }

    private void saveProductCategories(Product product, List<Long> categoryIds, Long userId) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        List<ProductCategory> productCategories = new ArrayList<>();

        for (Category category : categories) {
            ProductCategory pc = new ProductCategory();
            pc.setProduct(product);
            pc.setCategory(category);
            pc.setCreatedBy(userId);
            pc.setUpdatedBy(userId);
            productCategories.add(pc);
        }
        productCategoryRepository.saveAll(productCategories);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deleteProduct(Long id, Long deletedBy) {
        Product product = productRepository.findById(id)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.delete(deletedBy);
        productRepository.save(product);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void hardDeleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public ProductResponse restoreProduct(Long id, Long restoredBy) {
        Product product = productRepository.findById(id)
                .filter(p -> p.getDeletedAt() != null)
                .orElseThrow(() -> new RuntimeException("Deleted product not found with id: " + id));
        product.restore();
        product.setUpdatedBy(restoredBy);
        return productMapper.toResponse(productRepository.save(product));
    }

    private void updateProductFields(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setDiscountType(request.getDiscountType());
        product.setDiscountAmount(request.getDiscountAmount());
        product.setShortDescription(request.getShortDescription());
        product.setLongDescription(request.getLongDescription());
        product.setWeight(request.getWeight());
        product.setCountryId(request.getCountryId());
    }
}