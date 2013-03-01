package com.example.test.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.test.model.Item;

class ItemDAOImpl extends BaseRecord<Item> implements ItemDAO {
	
	ItemDAOImpl() {
	}
	
	@Override
	public ContentValues getContentValues(Item item) {
		ContentValues values = new ContentValues();
		values.put(COL_text, item.getText());
		return values;
	}
	
	@Override
	public Item populateObject(Cursor cursor) {
		Item item = new Item();
		item.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
		item.setText(cursor.getString(cursor.getColumnIndex(COL_text)));
		return item;
	}
	
	@Override
	public String getWhereClause(Item item) {
		return COL_ID + "=" + item.getId();
	}

	@Override
	public Item get(long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getAll() {
		List<Item> items = new ArrayList<Item>();
		Cursor cursor = getDb().query(TABLE_NAME, null,null,null,null,null,null);
		try {
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					items.add(populateObject(cursor));
					cursor.moveToNext();
				}
			}
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
				cursor = null;
			}
		}
		return items;
	}

	@Override
	public void truncate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getLastItem() {
		Item item = null;
		Cursor cursor = getDb().query(TABLE_NAME, null, null,null,null,null,COL_ID +" DESC LIMIT 1");
		try {
			if (cursor != null && cursor.getCount() > 0) {
				item = populateObject(cursor);
			}
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
				cursor = null;
			}
		}
		return item;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

}
