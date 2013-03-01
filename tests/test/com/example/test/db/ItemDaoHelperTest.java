package com.example.test.db;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.test.model.Item;

public class ItemDaoHelperTest {
	
	@Before
	public void setUp() {
		ItemDAOHelper.setItemDao(new ItemDAOMock());
	}
	
	@Test
	public void testGetLastItem() {
		Item item1 = new Item();
		item1.setId(1);
		ItemDAOHelper.insertOrUpdate(item1);
		
		Item item2 = new Item();
		item2.setId(2);
		ItemDAOHelper.insertOrUpdate(item2);
		
		Item item3 = new Item();
		item3.setId(3);
		ItemDAOHelper.insertOrUpdate(item3);
		
		Item theLastItem = ItemDAOHelper.getLastItem();
		assertEquals(3, theLastItem.getId());
	}

}
