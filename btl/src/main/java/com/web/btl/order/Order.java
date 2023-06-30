package com.web.btl.order;

import java.sql.Date;

public class Order {
	private int id = 0;
	private Date orderDate;
	private int total;
	private String fullname;
	private int o_userid;
	private int confirm = 0;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(int id, Date orderDate, int total, int o_userid, String fullname, int confirm) {
		this.id = id;
		this.orderDate = orderDate;
		this.total = total;
		this.o_userid = o_userid;
		this.fullname = fullname;
		this.confirm = confirm;
	}
	
	public Order( Date orderDate, int total, int o_userid) {
		this.orderDate = orderDate;
		this.total = total;
		this.o_userid = o_userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getO_userid() {
		return o_userid;
	}

	public void setO_userid(int o_userid) {
		this.o_userid = o_userid;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", total=" + total + ", o_userid=" + o_userid
				+ ", confirm=" + confirm + "]";
	}
}
