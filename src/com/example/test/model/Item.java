package com.example.test.model;

public class Item {

	private long id;
	private String text;
	
	public Item() {}
	
	public Item(String text) {
		this.text = text;
	}

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
	
}
