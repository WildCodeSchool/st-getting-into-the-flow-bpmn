package dev.wcs.tutoring.ssm.statepattern.states;

import dev.wcs.tutoring.ssm.statepattern.Order;

public class ClosedState implements OrderState {

    public static OrderState closedState = new ClosedState();

    private ClosedState() {}

    public static OrderState instance() {
        return closedState;
    }

    @Override
    public OrderState handle(Order order, String action) {
        return this;
    }

}
