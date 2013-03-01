package com.example.test.db;

import java.util.List;

import com.example.test.model.Item;

public class ItemDAOHelper {

	private static ItemDAO itemDao = new ItemDAOImpl();
	
	public static void setItemDao(ItemDAO _itemDao) {
		itemDao = _itemDao;
	}
	
	public static long insertOrUpdate(Item item) {
		return itemDao.insertOrUpdate(item);
	}
	
	public static void insertOrUpdate(List<Item> items) {
		itemDao.insertOrUpdate(items);
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
	
	public static Item getLastItem() {
		return itemDao.getLastItem();
	}
}
