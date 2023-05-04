package com.henrygrocery;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.henrysgrocery.model.Basket;
import com.henrysgrocery.model.Item;
import com.henrysgrocery.model.Offer;
import com.henrysgrocery.model.Product;
import com.henrysgrocery.service.BasketService;
import com.henrysgrocery.service.OfferService;

public class BasketServiceTest {

    private BasketService basketService;
    private OfferService offerService;
    private HashMap<Product, Integer> productList;

    @BeforeEach
    public void setUp() {
        offerService = new OfferService();
        productList = new HashMap<>();
        basketService = new BasketService(offerService);
    }

    @Test
    public void testCalculateTotalCost() {
        // Create a basket with items
        Basket basket = new Basket();
        basket.addItem(new Item("soup", "tin", 0.65));
        basket.addItem(new Item("bread", "loaf", 0.80));

        // Add an offer to the offer service
        Offer offer = new Offer("soup", LocalDate.now(), LocalDate.of(2024,2,2));
        Offer offerOne = new Offer("bread", LocalDate.now(), LocalDate.of(2024,2,2));
        offerService.addOffer(offer);
        offerService.addOffer(offerOne);

        // Calculate the total cost of the basket with the offer applied
        double totalCost = basketService.calculateTotalCost(basket);

        // Assert that the total cost is correct
        Assertions.assertEquals(1.4500000000000002, totalCost);
    }

    @Test
    public void testAddProduct() {
        // Add a product to the product list
        Product product = new Product("soup", "tin", 0.65);
        basketService.addProduct(product);

        // Assert that the product is added to the list
        Assertions.assertFalse(productList.containsKey(product));
        Assertions.assertEquals(1, productList.get(product).intValue());

        // Add the same product again
        basketService.addProduct(product);

        // Assert that the quantity of the product is increased
        Assertions.assertEquals(2, productList.get(product).intValue());
    }
}