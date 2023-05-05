package com.henrygrocery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.henrysgrocery.model.Discount;
import com.henrysgrocery.model.Item;
import com.henrysgrocery.service.DiscountService;

public class DiscountServiceTest {

	private DiscountService discountService;
	private Item item;

	@BeforeEach
	public void setUp() {
	    discountService = new DiscountService();
	    item = new Item("soup","tin", 0.65);
	}

	@Test
	public void testCalculateDiscountWithValidDiscount() {
	    Discount discount = new Discount(Discount.DiscountType.PERCENTAGE_OFF, 0.15, LocalDate.now().minusDays(2), LocalDate.now().plusDays(5));
	    discountService.addDiscount(discount);
	    double discountAmount = discountService.calculateDiscount(item);
	    assertEquals(0.15, discountAmount);
	}

	@Test
	public void testCalculateDiscountWithInvalidDiscount() {
	    Discount discount = new Discount(Discount.DiscountType.AMOUNT_OFF, 0.15, LocalDate.now().minusDays(2), LocalDate.now().minusDays(1));
	    discountService.addDiscount(discount);
	    double discountAmount = discountService.calculateDiscount(item);
	    assertEquals(0.15, discountAmount);
	}

	@Test
	public void testCalculateDiscountWithNoDiscount() {
	    double discountAmount = discountService.calculateDiscount(item);
	    assertEquals(0.15, discountAmount);
	}

}
