package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.test.db.ItemDAOHelper;
import com.example.test.db.ItemDAOMock;
import com.example.test.model.Item;
import com.example.test.util.FileUtils;
import com.example.test.util.Logger;

public class MainControllerTest {
	
	@Before
	public void setUp() {
		ItemDAOHelper.setItemDao(new ItemDAOMock());
	}
	
	@After
	public void tearDown() {
		MainController.getInstance().dispose();
	}
	
	@Test
	public void testAdd() {
		long insertItemId = 1;
		Item item = new Item();
		item.setId(insertItemId);
		item.setText("aaa");
		
		MainController.getInstance().add(item);
		
		Item insertedItem = ItemDAOHelper.get(insertItemId);
		assertNotNull(insertedItem);
		assertEquals(insertItemId, insertedItem.getId());
	}
	
	@Test
	public void testJSONAdd() {
		try {
			String source = FileUtils.getJsonFromFile(getClass(), "json/TestJsonAdd.json");
			Logger.d("source : %s", source);
			MainController.getInstance().syncWithServer(100L);
			
			assertEquals(2, ItemDAOHelper.getAll().size());
		} catch(Exception e) {
			Logger.e(e, "", "");
		}
	}
	
	/**
	 * 중복된 ID의 Item은 무시되야함
	 */
	@Test
	public void testJSONAdd2() {
		Item item = new Item();
		item.setId(1);
		item.setText("aaaa");
		MainController.getInstance().add(item);
		
		try {
			String source = FileUtils.getJsonFromFile(getClass(), "TestJsonAdd.json");
			Logger.d("source : %s", source);
			MainController.getInstance().syncWithServer(100L);
			
			assertEquals(2, ItemDAOHelper.getAll().size());
			
			Item existedItem = MainController.getInstance().get(item.getId());
			assertEquals(item.getText(), existedItem.getText());
		} catch(Exception e) {
			Logger.e(e, "", "");
		}
	}

}
