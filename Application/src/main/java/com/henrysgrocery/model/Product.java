package com.henrysgrocery.model;

public class Product {

	 private String name;
	    private String unit;
	    private double cost;

	    public Product(String name, String unit, double cost) {
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

	    public void setCost(double cost) {
	        this.cost = cost;
	    }
	}