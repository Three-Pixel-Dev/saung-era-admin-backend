package org.threepixeldev.saungeraadmin.features.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.threepixeldev.saungeraadmin.features.product.constants.ProductSwaggerMessages;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Product list response with additional stock and price information")
public class ProductListResponse extends ProductResponse {
    @Schema(description = "Total stock quantity from all product code values", example = "500")
    private Integer stock;

    @Schema(description = "Minimum price from all product code values", example = "29.99")
    private BigDecimal price;
}
