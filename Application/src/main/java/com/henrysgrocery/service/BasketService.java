package com.henrysgrocery.service;

import java.util.HashMap;
import java.util.List;

import com.henrysgrocery.model.Basket;
import com.henrysgrocery.model.Item;
import com.henrysgrocery.model.Product;

public class BasketService {

	private OfferService offerService;
	
	private HashMap<Product, Integer> productList=new HashMap<>();

    public BasketService(OfferService offerService) {
        this.offerService = offerService;
    }

    public double calculateTotalCost(Basket basket) {
        double totalCost = 0.0;
        List<Item> items = basket.getItems();
        for (Item item : items) {
            totalCost += item.getCost();
        }
        totalCost = offerService.applyOffers(basket);
        return totalCost;
    }
    
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!productList.containsKey(product)) {
            productList.put(product, 1);
        } else {
            productList.put(product, productList.get(product) + 1);
        }
    }
    
    public HashMap<Product, Integer> getProductList() {
        return productList;
    }
}
