package dev.wcs.tutoring.ssm.naive;

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

    private String state;

    public void calculateNextState(String action) {
        switch (action.toLowerCase()) {
            case "process": {
                if (state.equals(OrderState.ORDERED)) {
                    if (getPrice().intValue() > 0) {
                        if (getQuantity().intValue() > 0) {
                            state = OrderState.PROCESSING;
                        } else {
                            throw new IllegalStateException("Quantity must be positive.");
                        }
                    } else {
                        throw new IllegalStateException("Price must be positive.");
                    }
                }
            }
            case "send": {
                if (state.equals(OrderState.PROCESSING)) {
                    if (available) {
                        state = OrderState.DELIVERED;
                        shippingDate = LocalDate.now();
                    } else {
                        throw new IllegalStateException("Cannot be processed, Product not available.");
                    }
                }
            }
            case "close": {
                if (state.equals(OrderState.DELIVERED)) {
                    if (deliveryDate.isBefore(LocalDate.now())) {
                        state = OrderState.CLOSED;
                    } else {
                        throw new IllegalStateException("Deliverydate was not set or is after today.");
                    }
                }
            }
        }
    }

}
