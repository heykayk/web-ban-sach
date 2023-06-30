package com.web.btl.order;

public class OrderItem {
	private int id;
	private String title;
	private int quantity;
	private int price;
	private int total;
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	 
	public OrderItem(int id, String title, int quantity, int price) {
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
		this.total = this.quantity * this.price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", title=" + title + ", quantity=" + quantity + ", price=" + price + ", total="
				+ total + "]";
	}
}
