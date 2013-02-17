package com.example.test.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.test.model.Item;

public class ItemDAOMock implements ItemDAO {
	
	private Map<Long, Item> db = new HashMap<Long, Item>();

	@Override
	public void insertOrUpdate(Item t) {
		db.put(t.getId(), t);
	}

	@Override
	public void update(Item t) {
		db.put(t.getId(), t);
	}

	@Override
	public void insertOrUpdate(List<Item> ts) {
		for (Item t : ts) {
			insertOrUpdate(t);
		}
	}

	@Override
	public int destroy(long primaryKey) {
		Item removed = db.remove(primaryKey);
		return removed != null ? 1 : 0;
	}

	@Override
	public int destroy(Set<Long> primaryKeys) {
		int result = 0;
		for (Long key : primaryKeys) {
			result += destroy(key);
		}
		return result;
	}

	@Override
	public void destroyAll() {
		db.clear();
	}

	@Override
	public Item get(long primaryKey) {
		return db.get(primaryKey);
	}

	@Override
	public List<Item> getAll() {
		return new ArrayList<Item>(db.values());
	}

	@Override
	public void truncate() {
		db.clear();
		db = null;
	}

}
