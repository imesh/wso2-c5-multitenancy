package org.wso2.msf4j.example.domain;

/**
 * Product definition.
 */
public class Product {

    private final String id;
    private final double price;
    private final int qtyOnHand;

    public Product(String id, double price, int qtyOnHand) {
        this.id = id;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }
}
