package com.web.btl.books;

import java.sql.Date;

public class Book {
	private int id = -1;
	private String title;
	private String author;
	private String category;
	private int typeid;
	private int bookPageNumber;
	private int sold = 0;
	private Date releaseDate;
	private String description;
	private String image;
	private int price;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String title, String author) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.author = author;
	}
	
	public Book(int id, String title, String author,int typeid, String category, int bookPageNumber, int sold, Date  releaseDate, String description, String image, int price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.typeid = typeid;
		this.category = category;
		this.bookPageNumber = bookPageNumber;
		this.sold = sold;
		this.releaseDate =releaseDate;
		this.description = description;
		this.image = image;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBookPageNumber() {
		return bookPageNumber;
	}

	public void setBookPageNumber(int bookPageNumber) {
		this.bookPageNumber = bookPageNumber;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category + ", typeid="
				+ typeid + ", bookPageNumber=" + bookPageNumber + ", sold=" + sold + ", releaseDate=" + releaseDate
				+ ", description=" + description + ", image=" + image + "]";
	}
	
}
