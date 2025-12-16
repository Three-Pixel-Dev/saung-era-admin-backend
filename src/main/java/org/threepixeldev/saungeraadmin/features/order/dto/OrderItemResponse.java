package org.threepixeldev.saungeraadmin.features.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryResponse;
import org.threepixeldev.saungeraadmin.features.order.constants.OrderSwaggerMessages;
import org.threepixeldev.saungeraadmin.features.product.constants.ProductSwaggerMessages;
import org.threepixeldev.saungeraadmin.shared.dto.MasterData;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = OrderSwaggerMessages.ORDER_ITEM_RESPONSE_DESCRIPTION)
public class OrderItemResponse extends MasterData {
    @Schema(description = OrderSwaggerMessages.ORDER_ITEM_ID_DESCRIPTION, example = "1")
    private Long id;

    @Schema(description = ProductSwaggerMessages.PRODUCT_NAME_DESCRIPTION, example = "Men's T-Shirt")
    private String name;

    @Schema(description = ProductSwaggerMessages.PRODUCT_DESCRIPTION_DESCRIPTION, example = "Comfortable cotton t-shirt")
    private String description;

    @Schema(description = ProductSwaggerMessages.PRODUCT_QUANTITY_DESCRIPTION, example = "100")
    private Integer quantity;

    @Schema(description = ProductSwaggerMessages.PRODUCT_PRICE_DESCRIPTION, example = "29.99")
    private BigDecimal price;

    @Schema(description = ProductSwaggerMessages.PRODUCT_DISCOUNT_TYPE_DESCRIPTION, example = "PERCENTAGE")
    private String discountType;

    @Schema(description = ProductSwaggerMessages.PRODUCT_DISCOUNT_AMOUNT_DESCRIPTION, example = "10.00")
    private BigDecimal discountAmount;

    @Schema(description = ProductSwaggerMessages.PRODUCT_SHORT_DESCRIPTION_DESCRIPTION, example = "Premium cotton t-shirt")
    private String shortDescription;

    @Schema(description = ProductSwaggerMessages.PRODUCT_LONG_DESCRIPTION_DESCRIPTION, example = "Made from 100% organic cotton, this t-shirt offers comfort and style.")
    private String longDescription;

    @Schema(description = ProductSwaggerMessages.PRODUCT_WEIGHT_DESCRIPTION, example = "0.2")
    private BigDecimal weight;

    @Schema(description = ProductSwaggerMessages.PRODUCT_COUNTRY_ID_DESCRIPTION, example = "1")
    private Long countryId;

    @Schema(description = ProductSwaggerMessages.PRODUCT_CATEGORIES_DESCRIPTION)
    private List<CategoryResponse> categories;

    @Schema(description = OrderSwaggerMessages.ORDER_ITEM_QUANTITY_DESCRIPTION, example = "2")
    private Integer orderItemQuantity;

    @Schema(description = OrderSwaggerMessages.ORDER_ITEM_PRICE_DESCRIPTION, example = "29.99")
    private BigDecimal orderItemPrice;
}
