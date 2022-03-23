package dev.wcs.tutoring.ssm.statepattern.states;

import dev.wcs.tutoring.ssm.statepattern.Order;

public interface OrderState {

    OrderState handle(Order order, String action);

}
