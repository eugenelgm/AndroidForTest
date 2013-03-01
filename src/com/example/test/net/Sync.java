package com.example.test.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.test.model.Item;

public class Sync extends BaseResponse<List<Item>> {

	public void setLastUpdatedAt(long time) {
		addParam("lastUpdatedAt", String.valueOf(time));
	}

	@Override
	protected List<Item> parse(JSONObject json) throws JSONException {
		JSONArray itemArray = new JSONArray(json.getString("items"));
		
		List<Item> insertingItem = new ArrayList<Item>();
		for (int i = 0; i<itemArray.length(); i++) {
			Item item = new Item();
			item.parse(itemArray.getJSONObject(i));
			insertingItem.add(item);
		}
		return insertingItem;
	}
}
