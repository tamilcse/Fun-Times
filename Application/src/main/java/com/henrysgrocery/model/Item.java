package com.henrysgrocery.model;

public class Item {
	private String name;
    private String unit;
    private double cost;

    public Item(String name, String unit, double cost) {
        this.name = name;
        this.unit = unit;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getCost() {
        return cost;
    }

}
