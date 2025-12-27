package org.threepixeldev.saungeraadmin.features.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threepixeldev.saungeraadmin.features.product.constants.ProductSwaggerMessages;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = ProductSwaggerMessages.PRODUCT_REQUEST_DESCRIPTION)
public class ProductRequest {
    @NotBlank(message = "{validation.product.name.required}")
    @Schema(description = ProductSwaggerMessages.PRODUCT_NAME_DESCRIPTION, example = "Men's T-Shirt", maxLength = 255)
    private String name;

    @Schema(description = ProductSwaggerMessages.PRODUCT_DESCRIPTION_DESCRIPTION, example = "Comfortable cotton t-shirt", maxLength = 1000)
    private String description;

    @NotNull(message = "{validation.product.quantity.required}")
    @Positive(message = "{validation.product.quantity.positive}")
    @Schema(description = ProductSwaggerMessages.PRODUCT_QUANTITY_DESCRIPTION, example = "100")
    private Integer quantity;

    @NotNull(message = "{validation.product.price.required}")
    @Positive(message = "{validation.product.price.positive}")
    @Schema(description = ProductSwaggerMessages.PRODUCT_PRICE_DESCRIPTION, example = "29.99")
    private BigDecimal price;

    @Schema(description = ProductSwaggerMessages.PRODUCT_STATUS_DESCRIPTION, example = "Active")
    private String status;

    @Schema(description = ProductSwaggerMessages.PRODUCT_TAGS_DESCRIPTION, example = "Modern,Interior")
    private String tags;

    @NotBlank(message = "SKU is required")
    @Schema(description = ProductSwaggerMessages.PRODUCT_SKU_DESCRIPTION, example = "CHR-001")
    private String sku;

    @Schema(description = ProductSwaggerMessages.PRODUCT_IS_TAXABLE_DESCRIPTION, example = "true")
    private Boolean isTaxable;

    @Schema(description = ProductSwaggerMessages.PRODUCT_ALLOW_BACKORDER_DESCRIPTION, example = "false")
    private Boolean allowBackorder;

    @Schema(description = ProductSwaggerMessages.PRODUCT_DISCOUNT_TYPE_DESCRIPTION, example = "PERCENTAGE")
    private String discountType;

    @PositiveOrZero(message = "{validation.product.discount.positive}")
    @Schema(description = ProductSwaggerMessages.PRODUCT_DISCOUNT_AMOUNT_DESCRIPTION, example = "10.00")
    private BigDecimal discountAmount;

    @Schema(description = ProductSwaggerMessages.PRODUCT_SHORT_DESCRIPTION_DESCRIPTION, example = "Premium cotton t-shirt", maxLength = 500)
    private String shortDescription;

    @Schema(description = ProductSwaggerMessages.PRODUCT_LONG_DESCRIPTION_DESCRIPTION, example = "Made from 100% organic cotton, this t-shirt offers comfort and style.")
    private String longDescription;

    @PositiveOrZero(message = "{validation.product.weight.positive}")
    @Schema(description = ProductSwaggerMessages.PRODUCT_WEIGHT_DESCRIPTION, example = "0.2")
    private BigDecimal weight;

    @Schema(description = ProductSwaggerMessages.PRODUCT_COUNTRY_ID_DESCRIPTION, example = "1")
    private Long countryId;
    // @NotEmpty removed to handle deactivation of products with no categories
    @Schema(description = ProductSwaggerMessages.PRODUCT_CATEGORY_IDS_DESCRIPTION, example = "[1, 2]")
    private List<Long> categoryIds;

    @Schema(description = "List of product code values with prices (e.g., Color: Blue, Size: S)", example = "[{\"codeValueId\": 1, \"price\": 29.99}, {\"codeValueId\": 2, \"price\": 31.99}]")
    private List<ProductCodeValueRequest> productCodeValues;
}
