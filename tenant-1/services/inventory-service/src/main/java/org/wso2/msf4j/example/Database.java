package org.wso2.msf4j.example;

import org.wso2.msf4j.example.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imesh on 10/12/16.
 */
public class Database {

    private static Database instance;
    private List<Product> products;

    private Database() {
        products = new ArrayList<>();
        products.add(new Product("Product 1", 110, 10000));
        products.add(new Product("Product 2", 120, 20000));
        products.add(new Product("Product 3", 130, 30000));
        products.add(new Product("Product 4", 140, 40000));
        products.add(new Product("Product 5", 150, 50000));
        products.add(new Product("Product 6", 160, 60000));
        products.add(new Product("Product 7", 170, 70000));
        products.add(new Product("Product 8", 180, 80000));
        products.add(new Product("Product 9", 190, 90000));
        products.add(new Product("Product 10", 100, 100000));
        products.add(new Product("Product 11", 110, 120000));
    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }
}
