package dev.wcs.tutoring.ssm.enumstate;

import java.time.LocalDate;

public enum OrderState {

    Ordered {
        @Override
        public OrderState handle(Order order, String action) {
            if (!action.equals("process")) {
                return this;
            }
            if (order.getPrice().intValue() > 0) {
                if (order.getQuantity().intValue() > 0) {
                    return Processing;
                } else {
                    throw new IllegalStateException("Quantity must be positive.");
                }
            } else {
                throw new IllegalStateException("Price must be positive.");
            }
        }
    },

    Processing {
        @Override
        public OrderState handle(Order order, String action) {
            if (!action.equals("send")) {
                return this;
            }
            if (order.isAvailable()) {
                order.setShippingDate(LocalDate.now());
                return Delivered;
            } else {
                throw new IllegalStateException("Cannot be processed, Product not available.");
            }
        }
    },

    Delivered {
        @Override
        public OrderState handle(Order order, String action) {
            if (!action.equals("close")) {
                return this;
            }
           if (order.getDeliveryDate().isBefore(LocalDate.now())) {
                return OrderState.Closed;
            } else {
                throw new IllegalStateException("Deliverydate was not set or is after today.");
            }
        }
    },

    Closed {
        @Override
        public OrderState handle(Order order, String action) {
            return this;
        }
    };

    public abstract OrderState handle(Order order, String action);

}
