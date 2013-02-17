package com.example.test.model;

import java.util.LinkedList;
import java.util.List;

import com.example.test.db.ItemDAOHelper;
import com.example.test.util.Logger;

public class ItemSet {
	
	public interface ItemSetChangeListener {
		public void onChanged();
	}
	
	private List<ItemSetChangeListener> listeners = new LinkedList<ItemSetChangeListener>();
	private List<Item> items = new LinkedList<Item>();
	
	public ItemSet() {
		items.addAll(ItemDAOHelper.getAll());
		Logger.d("list size : %s", items.size());
		notifying();
	}
	
	public void add(Item item) {
		items.add(item);
		ItemDAOHelper.insertOrUpdate(item);
		Logger.d("list size : %s", items.size());
		notifying();
	}

	public List<Item> getAll() {
		return items;
	}
	
	private void notifying() {
		for (ItemSetChangeListener listener : listeners) {
			listener.onChanged();
		}
	}
	
	public void setChangedListener(ItemSetChangeListener listener) {
		listeners.add(listener);
	}
	
	public void removeChacnedListener(ItemSetChangeListener listener) {
		listeners.remove(listener);
	}

	public void dispose() {
		items.clear();
		ItemDAOHelper.detroyAll();
	}
}
