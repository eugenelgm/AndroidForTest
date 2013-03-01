package com.example.test.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.example.test.R;
import com.example.test.db.IdBased;

public class Item implements IdBased {

	private long id;
	private String text;
	
	public Item() {}
	
	public Item(String text) {
		this.text = text;
	}
	
	public void parse(JSONObject json) throws JSONException {
		this.id = json.getLong("id");
		this.text = json.getString("text");
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getLocale(Context context) {
		return context.getString(R.string.locale);
	}
	
}
