package org.threepixeldev.saungeraadmin.features.product.constants;

/**
 * Product Swagger message constants for use in Swagger annotations.
 * These constants use property placeholders (e.g., ${key}) to reference
 * values defined in messages.properties. This ensures the messages are
 * managed centrally in the properties file and resolved by Spring.
 */
public class ProductSwaggerMessages {

    // Product Controller Tag
    public static final String TAG_NAME = "${api.product.controller.tag}";
    public static final String TAG_DESCRIPTION = "${api.product.controller.tag.description}";

    public static final String PRODUCT_REQUEST_DESCRIPTION = "${api.product.description}";
    public static final String PRODUCT_RESPONSE_DESCRIPTION = "${api.product.response.description}";

    public static final String PRODUCT_ID_DESCRIPTION = "${api.product.id.description}";
    public static final String PRODUCT_NAME_DESCRIPTION = "${api.product.name.description}";
    public static final String PRODUCT_DESCRIPTION_DESCRIPTION = "${api.product.description.description}";
    public static final String PRODUCT_QUANTITY_DESCRIPTION = "${api.product.quantity.description}";
    public static final String PRODUCT_PRICE_DESCRIPTION = "${api.product.price.description}";
    public static final String PRODUCT_DISCOUNT_TYPE_DESCRIPTION = "${api.product.discount.type.description}";
    public static final String PRODUCT_DISCOUNT_AMOUNT_DESCRIPTION = "${api.product.discount.amount.description}";
    public static final String PRODUCT_SHORT_DESCRIPTION_DESCRIPTION = "${api.product.short.description.description}";
    public static final String PRODUCT_LONG_DESCRIPTION_DESCRIPTION = "${api.product.long.description.description}";
    public static final String PRODUCT_WEIGHT_DESCRIPTION = "${api.product.weight.description}";
    public static final String PRODUCT_COUNTRY_ID_DESCRIPTION = "${api.product.country.id.description}";
    public static final String PRODUCT_CATEGORIES_DESCRIPTION = "${api.product.categories.description}";
    public static final String PRODUCT_CATEGORY_IDS_DESCRIPTION = "${api.product.category.ids.description}";
}
