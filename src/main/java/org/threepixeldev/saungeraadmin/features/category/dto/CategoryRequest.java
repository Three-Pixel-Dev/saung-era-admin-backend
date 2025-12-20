package org.threepixeldev.saungeraadmin.features.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threepixeldev.saungeraadmin.features.category.constants.CategorySwaggerMessages;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = CategorySwaggerMessages.CATEGORY_REQUEST_DESCRIPTION)
public class CategoryRequest {
    @NotBlank(message = "{validation.category.name.required}")
    @Schema(description = CategorySwaggerMessages.CATEGORY_NAME_DESCRIPTION, example = "Men's Clothing", maxLength = 255)
    private String name;

    @Schema(description = CategorySwaggerMessages.CATEGORY_DESCRIPTION_DESCRIPTION, example = "This category contains all men's clothing items", maxLength = 1000)
    private String description;

    @Schema(description = CategorySwaggerMessages.CATEGORY_PARENT_ID_DESCRIPTION, example = "1")
    private Long parentId;
}
