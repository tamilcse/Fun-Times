package com.henrysgrocery.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.henrysgrocery.model.Basket;
import com.henrysgrocery.model.Discount;
import com.henrysgrocery.model.Item;
import com.henrysgrocery.model.Offer;
import com.henrysgrocery.model.Product;

public class OfferService {
    private static final LocalDate TODAY = LocalDate.now();

    private List<Offer> offers = new ArrayList<>();

    public OfferService() {
        offers.add(new Offer("Buy 2 tins of soup and get a loaf of bread half price",
                TODAY.minus(1, ChronoUnit.DAYS), TODAY.plus(7, ChronoUnit.DAYS)));
        offers.add(new Offer("Apples have a 10% discount", TODAY.plus(3, ChronoUnit.DAYS),
                LocalDate.of(TODAY.getYear(), TODAY.getMonth().plus(2), 1)));
    }

    public double applyOffers(Basket basket) {
        double totalCost = basket.getItems().stream().mapToDouble(Item::getCost).sum();

        for (Offer offer : offers) {
            if (offer.getValidFrom().isBefore(TODAY) && offer.getValidTo().isAfter(TODAY)) {
                if (offer.getName().startsWith("Buy 2 tins of soup and get a loaf of bread half price")) {
                    long soupCount = basket.getItems().stream()
                            .filter(item -> item.getName().equals("soup")).count();
                    if (soupCount >= 2) {
                        Item bread = basket.getItems().stream()
                                .filter(item -> item.getName().equals("bread")).findFirst().orElse(null);
                        if (bread != null) {
                            totalCost -= bread.getCost() / 2;
                        }
                    }
                } else if (offer.getName().startsWith("Apples have a 10% discount")) {
                    List<Item> apples = basket.getItems().stream()
                            .filter(item -> item.getName().equals("apples")).collect(Collectors.toList());
                    for (Item apple : apples) {
                        double discountAmount = apple.getCost() * offer.getPercentage() / 100;
                        totalCost -= discountAmount;
                    }
                }
            }
        }

        return totalCost;
    }
   

    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}
