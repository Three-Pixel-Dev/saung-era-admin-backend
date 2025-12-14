package org.threepixeldev.saungeraadmin.features.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryRequest;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryResponse;
import org.threepixeldev.saungeraadmin.features.category.mapper.CategoryMapper;
import org.threepixeldev.saungeraadmin.shared.data.model.Category;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CategoryJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private static final String CACHE_NAME = "categories";
    private static final String CACHE_KEY_ALL = "'all'";
    private static final String CACHE_KEY_BY_ID = "'category:' + #id";

    private final CategoryJpaRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryJpaRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Cacheable(value = CACHE_NAME, key = CACHE_KEY_ALL, unless = "#result.isEmpty()")
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllNotDeleted().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable(value = CACHE_NAME, key = CACHE_KEY_BY_ID, unless = "#result == null")
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toResponse(category);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public CategoryResponse createCategory(CategoryRequest request, Long createdBy) {
        if (categoryRepository.findByNameNotDeleted(request.getName()).isPresent()) {
            throw new RuntimeException("Category with name '" + request.getName() + "' already exists");
        }

        Category category = categoryMapper.toEntity(request);
        category.setCreatedBy(createdBy);
        category.setUpdatedBy(createdBy);
        
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public CategoryResponse updateCategory(Long id, CategoryRequest request, Long updatedBy) {
        Category category = categoryRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        categoryRepository.findByNameNotDeleted(request.getName())
                .ifPresent(existingCategory -> {
                    if (!existingCategory.getId().equals(id)) {
                        throw new RuntimeException("Category with name '" + request.getName() + "' already exists");
                    }
                });

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setUpdatedBy(updatedBy);

        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(updatedCategory);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deleteCategory(Long id, Long deletedBy) {
        Category category = categoryRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        category.delete(deletedBy);
        categoryRepository.save(category);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void hardDeleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public CategoryResponse restoreCategory(Long id, Long restoredBy) {
        Category category = categoryRepository.findByIdDeleted(id)
                .orElseThrow(() -> new RuntimeException("Deleted category not found with id: " + id));

        categoryRepository.findByNameNotDeleted(category.getName())
                .ifPresent(existingCategory -> {
                    throw new RuntimeException("Cannot restore category: A category with name '" + category.getName() + "' already exists");
                });
        
        category.restore();
        category.setUpdatedBy(restoredBy);
        
        Category restoredCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(restoredCategory);
    }
}
