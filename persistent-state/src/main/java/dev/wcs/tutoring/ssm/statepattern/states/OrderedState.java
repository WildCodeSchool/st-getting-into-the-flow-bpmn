package dev.wcs.tutoring.ssm.statepattern.states;

import dev.wcs.tutoring.ssm.statepattern.Order;

public class OrderedState implements OrderState {

    public static OrderState orderedState = new OrderedState();

    private OrderedState() {}

    public static OrderState instance() {
        return orderedState;
    }

    @Override
    public OrderState handle(Order order, String action) {
        if (!action.equals("process")) {
            return this;
        }
        if (order.getPrice().intValue() > 0) {
            if (order.getQuantity().intValue() > 0) {
                return ProcessingState.instance();
            } else {
                throw new IllegalStateException("Quantity must be positive.");
            }
        } else {
            throw new IllegalStateException("Price must be positive.");
        }
    }

}