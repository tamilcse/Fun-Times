package com.henrysgrocery.model;

import java.time.LocalDate;

public class Offer {
	private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    private double percentage;


    public Offer(String name, LocalDate validFrom, LocalDate validTo) {
        this.name = name;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public String getName() {
        return name;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }
    
    public double getPercentage() {
        return percentage;
    }

}
