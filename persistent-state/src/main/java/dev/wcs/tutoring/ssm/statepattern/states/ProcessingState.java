package dev.wcs.tutoring.ssm.statepattern.states;

import dev.wcs.tutoring.ssm.statepattern.Order;

import java.time.LocalDate;

public class ProcessingState implements OrderState {

    public static OrderState processingState = new ProcessingState();

    private ProcessingState() {}

    public static OrderState instance() {
        return processingState;
    }

    @Override
    public OrderState handle(Order order, String action) {
        if (!action.equals("send")) {
            return this;
        }
        if (order.isAvailable()) {
            order.setShippingDate(LocalDate.now());
            return DeliveredState.instance();
        } else {
            throw new IllegalStateException("Cannot be processed, Product not available.");
        }
    }

}
