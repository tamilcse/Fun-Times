package com.henrysgrocery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.henrysgrocery.constant.DiscountType;
import com.henrysgrocery.model.Basket;
import com.henrysgrocery.model.Discount;
import com.henrysgrocery.model.Item;
import com.henrysgrocery.model.Offer;
import com.henrysgrocery.model.Product;
import com.henrysgrocery.service.BasketService;
import com.henrysgrocery.service.DiscountService;
import com.henrysgrocery.service.OfferService;
import com.henrysgrocery.service.ProductService;

/**
 * Hello world!
 *
 */
public class App {
	private static final Discount Discount = null;

	public static void main(String[] args) {// Create the necessary services
		
        DiscountService discountService = new DiscountService();
        OfferService offerService = new OfferService();
        BasketService basketService = new BasketService(offerService);

        // Set up the products available at Henry's Grocery
        Product soup = new Product("soup", "tin", 0.65);
        Product bread = new Product("bread", "loaf", 0.80);
        Product milk = new Product("milk", "bottle", 1.30);
        Product apples = new Product("apples", "single", 0.10);

        // Add the products to the basket service
        basketService.addProduct(soup);
        basketService.addProduct(bread);
        basketService.addProduct(milk);
        basketService.addProduct(apples);

        // Set up the discounts available at Henry's Grocery
        discountService.addDiscount(Discount.createDiscountForBuyTwoSoupGetHalfPriceBread(LocalDate.now().minusDays(1), LocalDate.now().plusDays(7)));
//        discountService.addDiscount(Discount.createDiscountForTenPercentOffApples(LocalDate.now().plusDays(3), LocalDate.of(LocalDate.now().getYear(), LocalDate.now().plusMonths(1).getMonthValue(), 1)));

        // Set up the offers available at Henry's Grocery
//        offerService.addOffer(Offer.createOfferForBuyTwoSoupGetHalfPriceBread(LocalDate.now().minusDays(1), LocalDate.now().plusDays(7)));
//        offerService.addOffer(Offer.createOfferForTenPercentOffApples(LocalDate.now().plusDays(3), LocalDate.of(LocalDate.now().getYear(), LocalDate.now().plusMonths(1).getMonthValue(), 1)));

        // Prompt the user for their basket
        Scanner scanner = new Scanner(System.in);
        List<String> basketList = new ArrayList<>();
        String item;
        do {
            System.out.print("Enter an item in your basket (or 'done' to finish): ");
            item = scanner.nextLine();
            if (!item.equals("done")) {
                basketList.add(item);
            }
        } while (!item.equals("done"));

        // Calculate the total cost of the basket, applying discounts and offers as necessary
        Basket basket=new Basket();
//        basket.setItems(basketList);
        double totalCost = basketService.calculateTotalCost(basket);
        double totalDiscount = discountService.calculateDiscount((Item) basketList);
//double totalOffer = offerService.calculateOfferForBasket(basket);

        // Print the results
        System.out.printf("Total cost: %.2f\n", totalCost);
       System.out.printf("Total discount: %.2f\n", totalDiscount);
     //System.out.printf("Total offer: %.2f\n", totalOffer);
  //  System.out.printf("Grand total: %.2f\n", totalCost - totalDiscount - totalOffer);
    }
}
