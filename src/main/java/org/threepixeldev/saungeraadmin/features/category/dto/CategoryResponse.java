package org.threepixeldev.saungeraadmin.features.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.threepixeldev.saungeraadmin.shared.dto.MasterData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Response DTO containing category information")
public class CategoryResponse extends MasterData {
    @Schema(description = "Unique identifier of the category", example = "1")
    private Long id;

    @Schema(description = "Category name", example = "Men's Clothing")
    private String name;

    @Schema(description = "Category description", example = "This category contains all men's clothing items")
    private String description;
}
