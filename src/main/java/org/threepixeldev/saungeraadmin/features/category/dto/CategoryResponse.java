package org.threepixeldev.saungeraadmin.features.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.threepixeldev.saungeraadmin.features.category.constants.CategorySwaggerMessages;
import org.threepixeldev.saungeraadmin.shared.dto.MasterData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = CategorySwaggerMessages.CATEGORY_RESPONSE_DESCRIPTION)
public class CategoryResponse extends MasterData {
    @Schema(description = CategorySwaggerMessages.CATEGORY_ID_DESCRIPTION, example = "1")
    private Long id;

    @Schema(description = CategorySwaggerMessages.CATEGORY_NAME_DESCRIPTION, example = "Men's Clothing")
    private String name;

    @Schema(description = CategorySwaggerMessages.CATEGORY_DESCRIPTION_DESCRIPTION, example = "This category contains all men's clothing items")
    private String description;

    @Schema(description = "Parent category details including id and name")
    private CategoryResponse parentCategory;
}
