package com.example.test.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.test.MainController;
import com.example.test.db.ItemDAOHelper;
import com.example.test.db.ItemDAOMock;

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

}
