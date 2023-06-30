package com.web.btl.comment;

public class Comment {
	private int id;
	private int rate;
	private String comment;
	private int userid;
	private int bookid;
	private String username;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Comment(int id, int rate, String comment, int userid, int bookid, String username) {
		this.id = id;
		this.rate = rate;
		this.comment = comment;
		this.userid = userid;
		this.bookid = bookid;
		this.username = username;
	}
	
	public Comment(int rate, String comment, int bookid) {
		// TODO Auto-generated constructor stub
		this.rate = rate;
		this.comment = comment;
		this.bookid = bookid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", rate=" + rate + ", comment=" + comment + ", userid=" + userid + ", bookid="
				+ bookid + "]";
	}
}
