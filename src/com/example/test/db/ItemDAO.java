package com.example.test.db;

import com.example.test.model.Item;

public interface ItemDAO extends BaseDAO<Item> {

	public static final String TABLE_NAME = "item";
	public static final String COL_ID = "_id";
	public static final String COL_text = "text1";
	
	public Item getLastItem();
}
