package dev.wcs.tutoring.ssm.persistentstate;

public class Order {

    int id;
    String state;

    public Order(int id, String state) {
        this.id = id;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", state=" + state + "]";
    }

}
