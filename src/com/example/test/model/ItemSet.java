package com.example.test.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.test.db.ItemDAOHelper;
import com.example.test.util.Logger;

public class ItemSet {
	
	public interface ItemSetChangeListener {
		public void onChanged();
	}
	
	private List<ItemSetChangeListener> listeners = new LinkedList<ItemSetChangeListener>();
	private Map<Long, Item> items = new ConcurrentHashMap<Long, Item>();
	
	public ItemSet() {
		List<Item> _items = ItemDAOHelper.getAll();
		for (Item item : _items) {
			items.put(item.getId(), item);
		}
		Logger.d("list size : %s", items.size());
		notifying();
	}
	
	public void add(Item item) {
		long key = ItemDAOHelper.insertOrUpdate(item);
		item.setId(key);
		items.put(item.getId(), item);
		Logger.d("list size : %s", items.size());
		notifying();
	}
	
	public void add(List<Item> _items) {
		for (Item item : _items) {
			items.put(item.getId(), item);
		}
		ItemDAOHelper.insertOrUpdate(_items);
		Logger.d("list size : %s", items.size());
		notifying();
	}
	
	public Item get(long key) {
		return items.get(key);
	}

	public List<Item> getAll() {
		return new ArrayList<Item>(items.values());
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
