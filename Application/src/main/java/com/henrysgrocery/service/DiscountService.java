package com.henrysgrocery.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.henrysgrocery.model.Discount;
import com.henrysgrocery.model.Discount.DiscountType;
import com.henrysgrocery.model.Item;

public class DiscountService {
	
	private List<Discount> discounts;

    public DiscountService() {
        this.discounts = new ArrayList<>();
    }
	private static final LocalDate TODAY = LocalDate.now();

    public double calculateDiscount(Item item) {
    	Discount discount =new Discount(DiscountType.AMOUNT_OFF, 0.15, LocalDate.of(2023, 5, 4), LocalDate.of(2023, 5, 6));
        double discountAmount = 0.0;
        LocalDate validFrom = discount.getValidFrom();
        LocalDate validTo = discount.getValidTo();
        if (TODAY.isAfter(validFrom.minus(1, ChronoUnit.DAYS))
                && TODAY.isBefore(validTo.plus(1, ChronoUnit.DAYS))) {
            if (discount.getType().equals(Discount.DiscountType.AMOUNT_OFF)) {
                discountAmount = discount.getValue();
            } else if (discount.getType().equals(Discount.DiscountType.PERCENTAGE_OFF)) {
                discountAmount = item.getCost() * discount.getValue() / 100.0;
            }
        }
        return discountAmount;
    }
    
    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }
}
