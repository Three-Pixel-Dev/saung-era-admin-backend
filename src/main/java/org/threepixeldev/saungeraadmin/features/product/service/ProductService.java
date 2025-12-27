package org.threepixeldev.saungeraadmin.features.product.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus; // Import Added
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException; // Import Added
import org.threepixeldev.saungeraadmin.features.product.dto.ProductRequest;
import org.threepixeldev.saungeraadmin.features.product.dto.ProductResponse;
import org.threepixeldev.saungeraadmin.features.product.mapper.ProductMapper;
import org.threepixeldev.saungeraadmin.shared.data.model.Category;
import org.threepixeldev.saungeraadmin.shared.data.model.Product;
import org.threepixeldev.saungeraadmin.shared.data.model.ProductCategory;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CategoryJpaRepository;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.ProductCategoryJpaRepository;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.ProductJpaRepository;
import org.threepixeldev.saungeraadmin.shared.dto.PagedResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    // ... (Constants and Variables remain same)
    private static final String CACHE_NAME = "products";
    private static final String CACHE_KEY_BY_ID = "'product:' + #id";

    private final ProductJpaRepository productRepository;
    private final CategoryJpaRepository categoryRepository;
    private final ProductCategoryJpaRepository productCategoryRepository;
    private final ProductMapper productMapper;

    @PersistenceContext
    private EntityManager entityManager;

    // ... (getAllProducts, getProductById methods remain same) ...

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public ProductResponse createProduct(ProductRequest request, Long createdBy) {

        if (productRepository.existsBySkuAndDeletedAtIsNull(request.getSku())) {
            throw new RuntimeException( "SKU already exists");
        }

        Product product = new Product();
        updateProductFields(product, request);
        product.setCreatedBy(createdBy);
        product.setUpdatedBy(createdBy);

        Product savedProduct = productRepository.save(product);

        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            saveProductCategories(savedProduct, request.getCategoryIds(), createdBy);
        }

        return productMapper.toResponse(savedProduct);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public ProductResponse updateProduct(Long id, ProductRequest request, Long updatedBy) {
        Product product = productRepository.findById(id)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        // Validation: 500 Error အစား 400 Bad Request ပြန်ပေးပါမည်
        if (productRepository.existsBySkuAndIdNotAndDeletedAtIsNull(request.getSku(), id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SKU already exists");
        }

        updateProductFields(product, request);
        product.setUpdatedBy(updatedBy);

        Product savedProduct = productRepository.save(product);

        if (request.getCategoryIds() != null) {
            productCategoryRepository.deleteByProductId(savedProduct.getId());
            productCategoryRepository.flush();
            saveProductCategories(savedProduct, request.getCategoryIds(), updatedBy);
            entityManager.flush();
            entityManager.refresh(savedProduct);
        }

        return productMapper.toResponse(savedProduct);
    }

    // ... (saveProductCategories, deleteProduct, hardDeleteProduct, restoreProduct remain same) ...
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
        product.setCountryId(request.getCountryId() != null ? request.getCountryId() : 1L);
        product.setSku(request.getSku());
        product.setIsTaxable(request.getIsTaxable());
        product.setAllowBackorder(request.getAllowBackorder());
        product.setStatus(request.getStatus());
        product.setTags(request.getTags());
    }

    // ... (Duplicate other methods if needed or keep existing ones)
    @Cacheable(value = CACHE_NAME, key = "{#keyword, #status, #categoryId, #pageable.pageNumber, #pageable.pageSize}", unless = "#result.content.isEmpty()")
    @Transactional(readOnly = true)
    public PagedResponse<ProductResponse> getAllProducts(String keyword, String status, Long categoryId, Pageable pageable) {
        Page<Product> productPage = productRepository.searchProducts(keyword, status, categoryId, pageable);
        List<ProductResponse> content = productPage.getContent().stream()
                .map(productMapper::toResponse)
                .toList();

        return PagedResponse.<ProductResponse>builder()
                .content(content)
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .pageNumber(productPage.getNumber())
                .pageSize(productPage.getSize())
                .build();
    }

    @Cacheable(value = CACHE_NAME, key = CACHE_KEY_BY_ID, unless = "#result == null")
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toResponse(product);
    }
}