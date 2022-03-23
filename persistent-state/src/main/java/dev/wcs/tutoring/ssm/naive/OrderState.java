package dev.wcs.tutoring.ssm.naive;

import lombok.Data;

@Data
public class OrderState {

    public static String ORDERED = "ORDERED";
    public static String PROCESSING = "PROCESSING";
    public static String DELIVERED = "DELIVERED";
    public static String CLOSED = "CLOSED";

}
