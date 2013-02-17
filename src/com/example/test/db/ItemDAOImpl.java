package com.example.test.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.example.test.model.Item;

class ItemDAOImpl extends BaseRecord implements ItemDAO {
	
	ItemDAOImpl() {
	}
	
	public ContentValues getContentValues(Item item) {
		ContentValues values = new ContentValues();
		values.put(COL_text, item.getText());
		return values;
	}
	
	public Item populateObject(Cursor cursor) {
		Item item = new Item();
		item.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
		item.setText(cursor.getString(cursor.getColumnIndex(COL_text)));
		return item;
	}
	
	public String getWhereClause(Item item) {
		return COL_ID + "=" + item.getId();
	}

	@Override
	public void insertOrUpdate(Item t) {
		try {
			getDb().insertOrThrow(TABLE_NAME, null, getContentValues(t));
		} catch(SQLException e) {
			getDb().update(TABLE_NAME, getContentValues(t), getWhereClause(t), null);
		}
	}

	@Override
	public void update(Item t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOrUpdate(List<Item> ts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int destroy(long primaryKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int destroy(Set<Long> primaryKeys) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void destroyAll() {
		// TODO Auto-generated method stub
		
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

}
