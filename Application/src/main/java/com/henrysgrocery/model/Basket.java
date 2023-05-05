package com.henrysgrocery.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
	private List<Item> items = new ArrayList<>();

	public void addItem(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}
	
	public List<Item> setItems(List<Item> items) {
		return items;
	}
}
