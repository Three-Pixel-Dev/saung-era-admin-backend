package org.threepixeldev.saungeraadmin.features.order.constants;

/**
 * Order Swagger message constants for use in Swagger annotations.
 * These constants use property placeholders (e.g., ${key}) to reference
 * values defined in messages.properties. This ensures the messages are
 * managed centrally in the properties file and resolved by Spring.
 */
public class OrderSwaggerMessages {

    // Order Controller Tag
    public static final String TAG_NAME = "${api.order.controller.tag}";
    public static final String TAG_DESCRIPTION = "${api.order.controller.tag.description}";

    public static final String ORDER_RESPONSE_DESCRIPTION = "${api.order.response.description}";
    public static final String ORDER_ID_DESCRIPTION = "${api.order.id.description}";
    public static final String ORDER_USER_ID_DESCRIPTION = "${api.order.user.id.description}";
    public static final String ORDER_USER_NAME_DESCRIPTION = "${api.order.user.name.description}";
    public static final String ORDER_USER_EMAIL_DESCRIPTION = "${api.order.user.email.description}";
    public static final String ORDER_TOTAL_PRICE_DESCRIPTION = "${api.order.total.price.description}";
    public static final String ORDER_ORDERED_DATE_DESCRIPTION = "${api.order.ordered.date.description}";
    public static final String ORDER_PROMOTION_CODE_DESCRIPTION = "${api.order.promotion.code.description}";
    public static final String ORDER_PROMOTION_ID_DESCRIPTION = "${api.order.promotion.id.description}";
    public static final String ORDER_ITEMS_DESCRIPTION = "${api.order.items.description}";

    // Order Item
    public static final String ORDER_ITEM_RESPONSE_DESCRIPTION = "${api.order.item.response.description}";
    public static final String ORDER_ITEM_ID_DESCRIPTION = "${api.order.item.id.description}";
    public static final String ORDER_ITEM_QUANTITY_DESCRIPTION = "${api.order.item.quantity.description}";
    public static final String ORDER_ITEM_PRICE_DESCRIPTION = "${api.order.item.price.description}";
}
