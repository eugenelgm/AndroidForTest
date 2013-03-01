package com.example.test.net;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.example.test.model.Item;
import com.example.test.net.BaseResponse.ResponseLinstener;
import com.example.test.util.FileUtils;
import com.example.test.util.Logger;

public class SyncTest {
	
	@Test
	public void testSyncWithNoItem() throws IOException, JSONException {
		String source = FileUtils.getJsonFromFile(getClass(), "json/SyncJson1.json");
		Logger.d("source : %s", source);
		
		Sync sync = new Sync();
		sync.testForResult(new JSONObject(source));
		sync.setResponseListener(new ResponseLinstener<List<Item>>() {
			
			@Override
			public void response(List<Item> t) {
				assertEquals(0, t.size());
			}
		});
		sync.execute();
	}
	
	@Test
	public void testSyncWith2Items() throws IOException, JSONException {
		String source = FileUtils.getJsonFromFile(getClass(), "json/SyncJson2.json");
		Logger.d("source : %s", source);
		
		Sync sync = new Sync();
		sync.testForResult(new JSONObject(source));
		sync.setResponseListener(new ResponseLinstener<List<Item>>() {

			@Override
			public void response(List<Item> t) {
				assertEquals(2, t.size());
			}
		});
		sync.execute();
	}

}
