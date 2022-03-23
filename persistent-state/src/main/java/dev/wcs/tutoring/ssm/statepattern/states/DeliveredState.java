package dev.wcs.tutoring.ssm.statepattern.states;

import dev.wcs.tutoring.ssm.statepattern.Order;

import java.time.LocalDate;

public class DeliveredState implements OrderState {

    public static OrderState deliveredState = new DeliveredState();

    private DeliveredState() {}

    public static OrderState instance() {
        return deliveredState;
    }

    @Override
    public OrderState handle(Order order, String action) {
        if (!action.equals("close")) {
            return this;
        }
        if (order.getDeliveryDate().isBefore(LocalDate.now())) {
            return ClosedState.instance();
        } else {
            throw new IllegalStateException("Deliverydate was not set or is after today.");
        }
    }

}
