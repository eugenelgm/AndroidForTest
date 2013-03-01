package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.content.Context;

import com.example.test.model.Item;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class MainActivityTest {
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testItem() {
		Context context = PowerMockito.mock(Context.class);
		
		when(context.getString(R.string.locale)).thenReturn("Test");
		assertEquals("Test", context.getString(R.string.locale));
		
		Item item = new Item();
		item.setId(100);
		item.setText("Test");
		assertEquals("Test", item.getLocale(context));
		
	}

}
