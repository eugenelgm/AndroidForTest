package com.example.test;

import java.util.List;

import com.example.test.model.Item;
import com.example.test.model.ItemSet;
import com.example.test.model.ItemSet.ItemSetChangeListener;

public class MainController {
	
	private static MainController mainController = new MainController();
	private ItemSet itemSet;

	private MainController() {
		itemSet = new ItemSet();
	}
	
	public static MainController getInstance() {
		return mainController;
	}
	
	public void dispose() {
		itemSet.dispose();
	}
	
	public List<Item> getAll() {
		return itemSet.getAll();
	}
	
	public void add(Item item) {
		itemSet.add(item);
	}
	
	public void setChangedListener(ItemSetChangeListener listener) {
		itemSet.setChangedListener(listener);
	}
	
	public void removeChacnedListener(ItemSetChangeListener listener) {
		itemSet.removeChacnedListener(listener);
	}
}
