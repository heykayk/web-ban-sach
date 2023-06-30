package com.web.btl.dang_nhap;

public class Account {
	private int id = -1;
	private String fullname;
	private String username;
	private String password;
	private int isuser = 1;
	private int isadmin = 0;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(String username) {
		this.username = username;
	}
	
	public Account(int id, String fullname, String username, String password, int isadmin) {
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		if(isadmin == 1) {
			this.isadmin = isadmin;
			this.isadmin = 0;
		}
	}
	
	public Account(String fullname, String username, String password) {
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsuser() {
		return isuser;
	}

	public int getIsadmin() {
		return isadmin;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", fullname=" + fullname + ", username=" + username + ", password=" + password
				+ ", isuser=" + isuser + ", isadmin=" + isadmin + "]";
	}
}
