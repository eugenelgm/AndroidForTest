package com.example.test.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.test.model.Item;

public class ItemDAOMock implements ItemDAO {
	
	private Map<Long, Item> db = new HashMap<Long, Item>();

	@Override
	public long insertOrUpdate(Item t) {
		db.put(t.getId(), t);
		return t.getId();
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

	@Override
	public Item getLastItem() {
		List<Item> list = new ArrayList<Item>(db.values());
		Collections.sort(list, new Comparator<Item>() {
			@Override
			public int compare(Item item1, Item item2) {
				return (int) (item2.getId() - item1.getId());
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

}
