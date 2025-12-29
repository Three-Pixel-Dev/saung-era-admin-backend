package org.threepixeldev.saungeraadmin.features.product.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.threepixeldev.saungeraadmin.features.category.mapper.CategoryMapper;
import org.threepixeldev.saungeraadmin.features.product.dto.ProductCodeValueRequest;
import org.threepixeldev.saungeraadmin.features.product.dto.ProductResponse;
import org.threepixeldev.saungeraadmin.shared.data.model.Product;
import org.threepixeldev.saungeraadmin.shared.data.model.User;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.UserJpaRepository;
import org.threepixeldev.saungeraadmin.shared.mapper.UserMapper;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final UserJpaRepository userRepository;
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    public ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setDiscountType(product.getDiscountType());
        response.setDiscountAmount(product.getDiscountAmount());
        response.setShortDescription(product.getShortDescription());
        response.setLongDescription(product.getLongDescription());
        response.setWeight(product.getWeight());
        response.setCountryId(product.getCountryId());

        response.setStatus(product.getStatus());
        response.setIsTaxable(product.getIsTaxable());
        response.setAllowBackorder(product.getAllowBackorder());
        response.setTags(product.getTags());

        // Map categories via ProductCategory entity
        if (product.getProductCategories() != null) {
            response.setCategories(product.getProductCategories().stream()
                    .map(pc -> categoryMapper.toResponse(pc.getCategory()))
                    .collect(Collectors.toList()));
        } else {
            response.setCategories(Collections.emptyList());
        }
        if (product.getProductCodeValues() != null) {
            response.setProductCodeValues(product.getProductCodeValues().stream()
                    .map(pcv -> {
                        ProductCodeValueRequest dto = new ProductCodeValueRequest();
                        dto.setColorId(pcv.getColorId());
                        dto.setSizeId(pcv.getSizeId());
                        dto.setPrice(pcv.getPrice());
                        dto.setQuantity(pcv.getQuantity());
                        return dto;
                    })
                    .collect(Collectors.toList()));
        } else {
            response.setProductCodeValues(Collections.emptyList());
        }

        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        response.setDeletedAt(product.getDeletedAt());

        if (product.getCreatedBy() != null) {
            User createdByUser = userRepository.findById(product.getCreatedBy()).orElse(null);
            response.setCreatedBy(userMapper.toUserResponse(createdByUser));
        }
        if (product.getUpdatedBy() != null) {
            User updatedByUser = userRepository.findById(product.getUpdatedBy()).orElse(null);
            response.setUpdatedBy(userMapper.toUserResponse(updatedByUser));
        }
        if (product.getDeletedBy() != null) {
            User deletedByUser = userRepository.findById(product.getDeletedBy()).orElse(null);
            response.setDeletedBy(userMapper.toUserResponse(deletedByUser));
        }

        return response;
    }
}