package dev.wcs.tutoring.ssm;

import dev.wcs.tutoring.ssm.statepattern.states.ProcessingState;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStatePatterns {

    @Test
    public void shouldUseNaiveStateImpl() {
        dev.wcs.tutoring.ssm.naive.Order order = new dev.wcs.tutoring.ssm.naive.Order();
        order.setPrice(BigDecimal.TEN);
        order.setQuantity(1);
        order.calculateNextState("process");
        assertEquals(dev.wcs.tutoring.ssm.naive.OrderState.PROCESSING, order.getState());
    }

    @Test
    public void shouldUseStatePatternImpl() {
        dev.wcs.tutoring.ssm.statepattern.Order order = new dev.wcs.tutoring.ssm.statepattern.Order();
        order.setPrice(BigDecimal.TEN);
        order.setQuantity(1);
        order.calculateNextState("process");
        assertTrue(order.getState() instanceof ProcessingState);
    }

    @Test
    public void shouldUseEnumStateImpl() {
        dev.wcs.tutoring.ssm.enumstate.Order order = new dev.wcs.tutoring.ssm.enumstate.Order();
        order.setPrice(BigDecimal.TEN);
        order.setQuantity(1);
        order.calculateNextState("process");
        assertEquals(dev.wcs.tutoring.ssm.enumstate.OrderState.Processing, order.getState());
    }

}
