package org.threepixeldev.saungeraadmin.features.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.threepixeldev.saungeraadmin.features.order.constants.OrderSwaggerMessages;
import org.threepixeldev.saungeraadmin.shared.dto.MasterData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = OrderSwaggerMessages.ORDER_RESPONSE_DESCRIPTION)
public class OrderResponse extends MasterData {
    @Schema(description = OrderSwaggerMessages.ORDER_ID_DESCRIPTION, example = "1")
    private Long id;

    @Schema(description = OrderSwaggerMessages.ORDER_USER_ID_DESCRIPTION, example = "1")
    private Long userId;

    @Schema(description = OrderSwaggerMessages.ORDER_USER_NAME_DESCRIPTION, example = "John Doe")
    private String userName;

    @Schema(description = OrderSwaggerMessages.ORDER_USER_EMAIL_DESCRIPTION, example = "john.doe@example.com")
    private String userEmail;

    @Schema(description = OrderSwaggerMessages.ORDER_TOTAL_PRICE_DESCRIPTION, example = "149.99")
    private BigDecimal totalPrice;

    @Schema(description = OrderSwaggerMessages.ORDER_ORDERED_DATE_DESCRIPTION, example = "2025-12-14T15:30:00")
    private LocalDateTime orderedDate;

    @Schema(description = OrderSwaggerMessages.ORDER_PROMOTION_CODE_DESCRIPTION, example = "SAVE10")
    private String promotionCode;

    @Schema(description = OrderSwaggerMessages.ORDER_PROMOTION_ID_DESCRIPTION, example = "1")
    private Long promotionId;

    @Schema(description = OrderSwaggerMessages.ORDER_ITEMS_DESCRIPTION)
    private List<OrderItemResponse> orderItems;
}
