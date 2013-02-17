package com.example.test.db;

import java.util.List;

import com.example.test.model.Item;

public class ItemDAOHelper {

	private static ItemDAO itemDao = new ItemDAOImpl();
	
	public static void setItemDao(ItemDAO _itemDao) {
		itemDao = _itemDao;
	}
	
	public static void insertOrUpdate(Item item) {
		itemDao.insertOrUpdate(item);
	}

	public static List<Item> getAll() {
		return itemDao.getAll();
	}

	public static void detroyAll() {
		itemDao.destroyAll();
	}
	
	public static Item get(long id) {
		return itemDao.get(id);
	}
}
