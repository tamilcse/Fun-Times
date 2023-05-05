package com.henrygrocery;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.henrysgrocery.model.Basket;
import com.henrysgrocery.model.Item;
import com.henrysgrocery.model.Offer;
import com.henrysgrocery.model.Product;
import com.henrysgrocery.service.OfferService;


public class OfferServiceTest {

    private OfferService offerService;
    private Basket basket;
    private Product soup;
    private Product bread;
    private Product apples;
    private Product milk;
    
    @BeforeEach
    public void setUp() {
        offerService = new OfferService();
        
        soup = new Product("soup","tin", 0.65);
        bread = new Product("bread","loaf", 0.80);
        apples = new Product("apples","single", 0.10);
        milk = new Product("milk", "bottle", 1.15);
        
        basket = new Basket();
        basket.setItems(Arrays.asList(
                new Item("soup","tin", 0.65),
                new Item("bread", "loaf",0.80),
                new Item("apples","single", 0.10),
                new Item("milk", "bottle",1.15)
        ));
    }
    
    @Test
    public void testApplyOffersWithBuy2SoupGetBreadHalfPriceOffer() {
        double expectedTotalCost = (0.65 * 2) + (0.80 / 2) + (1.00 * 5);
        
        double actualTotalCost = offerService.applyOffers(basket);
        
        assertEquals(expectedTotalCost, actualTotalCost, 0.001);
    }
    
    @Test
    public void testApplyOffersWithApples10PercentDiscountOffer() {
        // 10% discount on 5 apples is 0.50
        double expectedTotalCost = (0.65 * 2) + (0.80) + (1.00 * 5) - 0.50;
        
        // Update the offer so that it's valid until today
        offerService.addOffer(new Offer("Apples have a 10% discount", LocalDate.now().minusDays(3),
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(2), 1)));
        
        double actualTotalCost = offerService.applyOffers(basket);
        
        assertEquals(expectedTotalCost, actualTotalCost, 0.001);
    }
    
    @Test
    public void testApplyOffersWithNoValidOffers() {
        double expectedTotalCost = (0.65 * 2) + (0.80) + (1.00 * 5);
        
        // Update the offer so that it's not valid today
        offerService.addOffer(new Offer("Apples have a 10% discount", LocalDate.now().minusDays(10),
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(2), 1)));
        
        double actualTotalCost = offerService.applyOffers(basket);
        
        assertEquals(expectedTotalCost, actualTotalCost);
    }
}
