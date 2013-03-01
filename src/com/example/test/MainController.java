package com.example.test;

import java.util.List;

import com.example.test.model.Item;
import com.example.test.model.ItemSet;
import com.example.test.model.ItemSet.ItemSetChangeListener;
import com.example.test.net.BaseResponse.ResponseLinstener;
import com.example.test.net.Sync;

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
	
	public Item get(long key) {
		return itemSet.get(key);
	}
	
	public List<Item> getAll() {
		return itemSet.getAll();
	}
	
	public void add(Item item) {
		itemSet.add(item);
	}
	
	public void addIfNotExist(Item item) {
		if (itemSet.get(item.getId()) == null) {
			add(item);
		}
	}
	
	public void add(List<Item> items) {
		itemSet.add(items);
	}
	
	public void setChangedListener(ItemSetChangeListener listener) {
		itemSet.setChangedListener(listener);
	}
	
	public void removeChacnedListener(ItemSetChangeListener listener) {
		itemSet.removeChacnedListener(listener);
	}

	public void syncWithServer(long lastUpdatedAt) {
		Sync sync = new Sync();
		sync.setLastUpdatedAt(lastUpdatedAt);
		sync.setResponseListener(new ResponseLinstener<List<Item>>() {
			@Override
			public void response(List<Item> newItems) {
				for (Item curItem : newItems) {
					addIfNotExist(curItem);
				}
			}
		});
		sync.execute();
	}
}
