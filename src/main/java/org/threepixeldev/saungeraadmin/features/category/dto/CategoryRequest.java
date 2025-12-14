package org.threepixeldev.saungeraadmin.features.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating or updating a category")
public class CategoryRequest {
    @NotBlank(message = "Category name is required")
    @Schema(description = "Category name", example = "Men's Clothing", required = true, maxLength = 255)
    private String name;

    @Schema(description = "Category description", example = "This category contains all men's clothing items", maxLength = 1000)
    private String description;
}
