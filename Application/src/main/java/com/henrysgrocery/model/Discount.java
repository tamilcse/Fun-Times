package com.henrysgrocery.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class Discount {

	public enum DiscountType {
		PERCENTAGE_OFF,
		AMOUNT_OFF,
		BUY_TWO_GET_HALF_PRICE
	}
	HashMap<Product, Integer> productQuantities;
	private DiscountType type;
	private double value;
	private LocalDate validFrom;
	private LocalDate validTo;

	public Discount(DiscountType type, double value,LocalDate validFrom2, LocalDate validTo2) {
		this.type = type;
		this.value = value;
		this.validFrom = validFrom2;
		this.validTo = validTo2;
	}

	public DiscountType getType() {
		return this.type;
	}

	public double getValue() {
		return this.value;
	}

	public LocalDate getValidFrom() {
		return this.validFrom;
	}

	public LocalDate getValidTo() {
		return this.validTo;
	}

	private Item item;
	private double percentage;

	public Discount(Item item, double percentage) {
		this.item = item;
		this.percentage = percentage;
	}

	public Item getItem() {
		return item;
	}

	public double getPercentage() {
		return percentage;
	}

	public static Discount createDiscountForBuyTwoSoupGetHalfPriceBread(LocalDate localDate, LocalDate localDate2) {
	    Product soup = new Product("Soup", "tin", 0.65);
	    Product bread = new Product("Bread", "loaf", 0.80);
	     LocalDate validFromDate = LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toLocalDate();
	     LocalDate validToDate = LocalDate.now().plusDays(6).atStartOfDay(ZoneId.systemDefault()).toLocalDate();
	    Discount discount = new Discount(Discount.DiscountType.BUY_TWO_GET_HALF_PRICE, 0, validFromDate, validToDate);
	    discount.addProduct(soup, 2);
	    discount.addProduct(bread, 1);
	    return discount;
	}

	public void addProduct(Product product, int quantity) {
	    productQuantities.put(product, quantity);
	}

}


