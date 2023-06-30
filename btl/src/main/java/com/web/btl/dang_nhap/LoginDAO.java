package com.web.btl.dang_nhap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ResponseBody;

import com.web.btl.DAO.DAO;

public class LoginDAO {
	DAO dao = new DAO();
	
	public Account checkAcocunt(String username, String password) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM btl_web.user where username = ? and password = ?;");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String user = rs.getString("username");
				String pass = rs.getString("password");
				int isadmin = rs.getInt("isadmin");
				return new Account(id, fullname, username, password, isadmin);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public Account checkAccountByUsername(String username) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM btl_web.user where username = ?;");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String user = rs.getString("username");
				String pass = rs.getString("password");
				int isadmin = rs.getInt("isadmin");
				return new Account(id, fullname, user, pass, isadmin);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public void createNewAccount(Account a) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `btl_web`.`user` (`fullname`, `username`, `password`, `isuser`, `isadmin`) VALUES (?, ?, ?, ?, ?);\r\n"
					+ ";");
			ps.setString(1, a.getFullname());
			ps.setString(2, a.getUsername());
			ps.setString(3, a.getPassword());
			ps.setInt(4, a.getIsuser());
			ps.setInt(5, a.getIsadmin());
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean checkDupli(String username) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM btl_web.user where username = ?;");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
