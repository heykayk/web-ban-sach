package com.web.btl.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.btl.DAO.DAO;

public class OrderDAO {
	DAO dao = new DAO();
	
	public List<Order> getAllListOrderForAdmin(){
		List<Order> list = new ArrayList<>();
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT order.id, order.orderDate, order.total, order.o_userid, user.fullname, order.confirm\r\n"
					+ "FROM btl_web.order \r\n"
					+ "join btl_web.user on order.o_userid = user.id\r\n"
					+ "where order.confirm = 0\r\n"
					+ "order by order.id desc;");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				Date orderDate = rs.getDate("orderDate");
				int total = rs.getInt("total");
				int o_userid = rs.getInt("o_userid");
				String fullname = rs.getString("fullname");
				int confirm = rs.getInt("confirm");
				
				list.add(new Order(id, orderDate, total, o_userid, fullname, confirm));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<Order> getAllListOrder(int userid){
		List<Order> list = new ArrayList<>();
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT order.id, order.orderDate, order.total, order.o_userid, user.fullname, order.confirm\r\n"
					+ "FROM btl_web.order \r\n"
					+ "join btl_web.user on order.o_userid = user.id\r\n"
					+ "where o_userid = ?\r\n"
					+ "order by order.id desc;");
			
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				Date orderDate = rs.getDate("orderDate");
				int total = rs.getInt("total");
				int o_userid = rs.getInt("o_userid");
				String fullname = rs.getString("fullname");
				int confirm = rs.getInt("confirm");
				
				list.add(new Order(id, orderDate, total, o_userid, fullname, confirm));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<OrderItem> getAllOrderItem(int orderid) {
		List<OrderItem> list = new ArrayList<>();
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT order_item.id, books.title, order_item.orderQuantity, books.price\r\n"
					+ "FROM btl_web.order_item\r\n"
					+ "join btl_web.books on order_item.oi_bookid = books.id\r\n"
					+ "where order_item.oi_orderid = ?;");
			ps.setInt(1, orderid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int orderQuantity = rs.getInt("orderQuantity");
				int price = rs.getInt("price");
				
				list.add(new OrderItem(id, title, orderQuantity, price));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Order getOrderById(int orderid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT order.id, order.orderDate, order.total, order.o_userid, user.fullname, order.confirm\r\n"
					+ "FROM btl_web.order \r\n"
					+ "join btl_web.user on order.o_userid = user.id\r\n"
					+ "where order.id = ?;");
			
			ps.setInt(1, orderid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				Date orderDate = rs.getDate("orderDate");
				int total = rs.getInt("total");
				int o_userid = rs.getInt("o_userid");
				String fullname = rs.getString("fullname");
				int confirm = rs.getInt("confirm");
				
				return new Order(id, orderDate, total, o_userid, fullname, confirm);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void delOrderItem(int orderid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from btl_web.order_item where order_item.oi_orderid = ?;");
			ps.setInt(1, orderid);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delOrderById(int orderid) {
		delOrderItem(orderid);
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from btl_web.order where id = ?");
			ps.setInt(1, orderid);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateOrder(int orderid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE `btl_web`.`order` SET `confirm` = '1' WHERE (`id` = ?);");
			ps.setInt(1, orderid);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
