package org.threepixeldev.saungeraadmin.features.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for product code value with price")
public class ProductCodeValueRequest {
    @NotNull(message = "Code value ID is required")
    @Schema(description = "ID of the code value (e.g., Color: Blue, Size: S)", example = "1", required = true)
    private Long codeValueId;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Schema(description = "Price for this product variant (code value combination)", example = "29.99", required = true)
    private BigDecimal price;
}
