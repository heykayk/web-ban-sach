package com.web.btl.cart;

public class Cart {
	private int id;
	private String title;
	private String author;
	private String image;
	private int price;
	private int quantity;
	private int bookid;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	
	public Cart(int id, String title, String author, String image, int quantity, int bookid, int price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.image = image;
		this.quantity = quantity;
		this.bookid = bookid;
		this.price = price;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", title=" + title + ", author=" + author + ", image=" + image + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
}
