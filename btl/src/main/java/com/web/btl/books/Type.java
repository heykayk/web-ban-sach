package com.web.btl.books;

public class Type {
	private int id;
	private String category;
	
	public Type() {
		// TODO Auto-generated constructor stub
	}
	
	public Type(int id, String category) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
