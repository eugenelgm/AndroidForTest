package com.example.test.db;

import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.GlobalApplication;
import com.example.test.model.Item;
import com.example.test.util.Logger;

public abstract class BaseRecord<T extends IdBased> {
	
	private static SQLiteDatabase db;
	
	public BaseRecord() {
	}
	
	public SQLiteDatabase getDb() {
		if (db == null) {
			db = GlobalApplication.getInstance().openOrCreateDatabase("test.db", 8, null);
			db.execSQL("CREATE TABLE IF NOT EXISTS "+
					ItemDAO.TABLE_NAME+
					" ("+ItemDAO.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
					" "+ItemDAO.COL_text+" TEXT);" );
		}
		return db;
	}
	
	public abstract String getTableName();
	
	public abstract String getWhereClause(T t);
	
	public abstract Item populateObject(Cursor cursor);
	
	public abstract ContentValues getContentValues(T t);
	
	public long insertOrUpdate(T t) {
		try {
			return getDb().insertOrThrow(getTableName(), null, getContentValues(t));
		} catch(SQLException e) {
			getDb().update(getTableName(), getContentValues(t), getWhereClause(t), null);
			return t.getId();
		}
	}
	
	public void insertOrUpdate(List<T> ts) {
		try {
			getDb().beginTransaction();
			for (T t : ts) {
				insertOrUpdate(t);
			}
			getDb().setTransactionSuccessful();
		} catch(SQLException se) {
			Logger.e(se, "", "");
		} finally {
			getDb().endTransaction();
		}
	}
	
	public void update(T t) {
		getDb().update(getTableName(), getContentValues(t), getWhereClause(t), null);
	}
	
	public int destroy(long primaryKey) {
		return 0;
	}
	
	public int destroy(Set<Long> primaryKeys) {
		return 0;
	}
	
	public void destroyAll() {
		
	}
}
