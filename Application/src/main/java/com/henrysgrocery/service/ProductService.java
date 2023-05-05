package com.henrysgrocery.service;

import java.util.ArrayList;
import java.util.List;

import com.henrysgrocery.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private List<Product> productList;

    public ProductService() {
        productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public Product getProductByName(String productName) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
}

