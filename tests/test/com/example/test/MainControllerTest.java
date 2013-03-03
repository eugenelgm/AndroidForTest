package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.test.db.ItemDAOHelper;
import com.example.test.db.ItemDAOMock;
import com.example.test.model.Item;

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
	public void testAddIfNotExist() {
		long insertItemId = 1;
		Item item = new Item();
		item.setId(insertItemId);
		item.setText("aaa");
		MainController.getInstance().add(item);
		
		Item item2 = new Item();
		item2.setId(insertItemId);
		item2.setText("bbb");
		MainController.getInstance().addIfNotExist(item2);
		
		Item result = ItemDAOHelper.get(insertItemId);
		assertEquals("aaa", result.getText());
	}

}
