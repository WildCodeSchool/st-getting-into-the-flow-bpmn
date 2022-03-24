package dev.wcs.tutoring.ssm.statepattern;

import dev.wcs.tutoring.ssm.statepattern.states.OrderState;
import dev.wcs.tutoring.ssm.statepattern.states.OrderedState;
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

    private OrderState state = OrderedState.instance();

    public void calculateNextState(String action) {
        state = state.handle(this, action);
    }

}
