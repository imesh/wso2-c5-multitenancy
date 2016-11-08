package org.wso2.msf4j.example.domain;

/**
 * Order definition.
 */
public class Order {

    private final String id;
    private final double price;

    public Order(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
