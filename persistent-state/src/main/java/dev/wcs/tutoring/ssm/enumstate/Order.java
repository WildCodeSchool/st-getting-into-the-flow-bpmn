package dev.wcs.tutoring.ssm.enumstate;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Order {

    private String id;
    private String customerId;

    private String productId;
    private Integer quantity;
    private BigDecimal price;
    private boolean available;
    private LocalDate shippingDate;
    private LocalDate deliveryDate;

    private OrderState state = OrderState.Ordered;

    public void calculateNextState(String action) {
        state = state.handle(this, action);
    }

}
