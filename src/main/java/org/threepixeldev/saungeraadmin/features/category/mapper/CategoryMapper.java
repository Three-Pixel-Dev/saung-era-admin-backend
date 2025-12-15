package org.threepixeldev.saungeraadmin.features.category.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryRequest;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryResponse;
import org.threepixeldev.saungeraadmin.shared.data.model.Category;
import org.threepixeldev.saungeraadmin.shared.data.model.User;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.UserJpaRepository;
import org.threepixeldev.saungeraadmin.shared.mapper.UserMapper;

@Component
@AllArgsConstructor
public class CategoryMapper {

    private final UserJpaRepository userRepository;

    private final UserMapper userMapper;

    public CategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }

        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        response.setDeletedAt(category.getDeletedAt());
        
        // Map createdBy
        if (category.getCreatedBy() != null) {
            User createdByUser = userRepository.findById(category.getCreatedBy()).orElse(null);
            response.setCreatedBy(userMapper.toUserResponse(createdByUser));
        }
        
        // Map updatedBy
        if (category.getUpdatedBy() != null) {
            User updatedByUser = userRepository.findById(category.getUpdatedBy()).orElse(null);
            response.setUpdatedBy(userMapper.toUserResponse(updatedByUser));
        }
        
        // Map deletedBy
        if (category.getDeletedBy() != null) {
            User deletedByUser = userRepository.findById(category.getDeletedBy()).orElse(null);
            response.setDeletedBy(userMapper.toUserResponse(deletedByUser));
        }
        
        return response;
    }

    public Category toEntity(CategoryRequest request) {
        if (request == null) {
            return null;
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return category;
    }
}
