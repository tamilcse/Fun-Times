package com.henrygrocery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.henrysgrocery.model.Basket;
import com.henrysgrocery.model.Discount;
import com.henrysgrocery.model.Item;
import com.henrysgrocery.model.Offer;
import com.henrysgrocery.model.Product;
import com.henrysgrocery.service.BasketService;
import com.henrysgrocery.service.DiscountService;
import com.henrysgrocery.service.OfferService;
import com.henrysgrocery.service.ProductService;

import java.time.LocalDate;

public class BasketServiceTest {

    private BasketService basketService;

    @BeforeEach
    public void setUp() {
        DiscountService discountService = new DiscountService();
        discountService.addDiscount(new Discount(Discount.DiscountType.AMOUNT_OFF,  0, LocalDate.now().minusDays(7), LocalDate.now()));
        discountService.addDiscount(new Discount(Discount.DiscountType.PERCENTAGE_OFF,  0, LocalDate.now().plusDays(3), LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(2), 1)));

        OfferService offerService = new OfferService();
        offerService.addOffer(new Offer("Buy 2 tins of soup and get a loaf of bread half price", LocalDate.now().minusDays(1), LocalDate.now().plusDays(7)));
        offerService.addOffer(new Offer("Apples have a 10% discount", LocalDate.now().plusDays(3), LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(2), 1)));

        ProductService productService = new ProductService();
        productService.addProduct(new Product("soup","tin", 0.65));
        productService.addProduct(new Product("bread","loaf", 0.80));
        productService.addProduct(new Product("apples","single", 0.10));
        productService.addProduct(new Product("milk", "bottle", 1.15));

        basketService = new BasketService(offerService);
    }

    @Test
    public void testCalculateTotalCostWithDiscounts() {
        Basket basket = new Basket();
        basket.addItem(new Item("soup","tin", 3));
        basket.addItem(new Item("bread", "loaf",2));
        basket.addItem(new Item("apples","single", 6));
        basket.addItem(new Item("milk", "bottle",1));

        double expectedCost = 12.0; // (3 * 0.65) + (2 * 0.40) + (6 * 0.09) + 1.15 - 0.5 - 0.6
        double actualCost = basketService.calculateTotalCost(basket);

        Assertions.assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("soup","tin", 1.20);

        basketService.addProduct(product);

        Assertions.assertTrue(basketService.getProductList().containsKey(product));
        Assertions.assertEquals(1, basketService.getProductList().get(product));
    }

    @Test
    public void testAddProductWithNullProduct() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> basketService.addProduct(null));
    }
}

