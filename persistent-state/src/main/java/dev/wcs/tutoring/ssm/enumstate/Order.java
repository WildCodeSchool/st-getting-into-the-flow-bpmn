package dev.wcs.tutoring.ssm.enumstate;

import java.math.BigDecimal;
import java.time.LocalDate;

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


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

}
