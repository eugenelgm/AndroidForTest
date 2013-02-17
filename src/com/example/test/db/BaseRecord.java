package com.example.test.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.test.GlobalApplication;

public class BaseRecord {
	
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
}
