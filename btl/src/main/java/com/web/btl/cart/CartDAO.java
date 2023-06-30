package com.web.btl.cart;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.web.btl.DAO.DAO;
import com.web.btl.order.Order;

public class CartDAO {
	DAO dao = new DAO();
	
	public List<Cart> getAllCart(int userid) {
		List<Cart> list = new ArrayList<>();
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT cart.id, books.title, books.author, books.image, cart.quantity , books.id as bookid, books.price\r\n"
					+ "FROM btl_web.cart\r\n"
					+ "join btl_web.books on cart.cart_bookid = books.id\r\n"
					+ "where cart_userid = ?\r\n"
					+ "order by cart.id desc;");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			System.out.println("den day roi");
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String image = rs.getString("image");
				int quantity = rs.getInt("quantity");
				int bookid = rs.getInt("bookid");
				int price = rs.getInt("price");
				
				list.add(new Cart(id, title, author, image, quantity, bookid, price));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int checkCart(int userid, int bookid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT cart.id FROM btl_web.cart where cart.cart_userid = ? and cart.cart_bookid = ?;");
			ps.setInt(1, userid);
			ps.setInt(2, bookid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void updateAddCart(int cartid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE `btl_web`.`cart` SET `quantity` = `quantity` + 1 WHERE (`id` = ?);");
			ps.setInt(1, cartid);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int checkSubCart(int cartid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT cart.quantity FROM btl_web.cart where id = ?;");
			ps.setInt(1, cartid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("quantity");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void delCart(int cartid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `btl_web`.`cart` WHERE (`id` = ?);");
			ps.setInt(1, cartid);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSubCart(int cartid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE `btl_web`.`cart` SET `quantity` = `quantity` - 1 WHERE (`id` = ?);");
			ps.setInt(1, cartid);
			ps.executeUpdate();
			if(checkSubCart(cartid) <= 0) {
				delCart(cartid);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void newCart(int userid, int bookid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `btl_web`.`cart` (`quantity`, `cart_userid`, `cart_bookid`) VALUES (?, ?, ?);\r\n"
					+ "");
			ps.setInt(1, 1);
			ps.setInt(2, userid);
			ps.setInt(3, bookid);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int sumPrice(List<Cart> list) {
		int sum = 0;
		for(Cart i:list) {
			sum += i.getQuantity() * i.getPrice();
		}
		return sum;
	}
	
	private Date getDay() {
		LocalDate currentDate = LocalDate.now();

        // Chuyển đổi thành ngày SQL
        Date sqlDate = Date.valueOf(currentDate);
        return sqlDate;
	}
	
	public void newOrder(int userid) {
		List<Cart> list = getAllCart(userid);
		Order order = new Order(getDay(), sumPrice(list), userid);
		System.out.println(order);
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `btl_web`.`order` (`orderDate`, `total`, `o_userid`, `confirm`) VALUES (?, ?, ?, ?);\r\n"
					+ "");
			ps.setDate(1, order.getOrderDate());
			ps.setInt(2, order.getTotal());
			ps.setInt(3, order.getO_userid());
			ps.setInt(4, order.getConfirm());
			ps.executeUpdate();
			newOrderItem(userid, list);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int getIdOrder(int userid) {
		int id = 0;
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT order.id FROM btl_web.order\r\n"
					+ "where order.o_userid = ?\r\n"
					+ "order by id desc\r\n"
					+ "limit 1;");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public void delCartByUserid(int userid) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from btl_web.cart where cart.cart_userid = ?;");
			ps.setInt(1, userid);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void newOrderItem(int userid, List<Cart> list) {
		int orderid = getIdOrder(userid);
		System.out.println(orderid);
		try {
			for(Cart i:list) {
				Connection conn = dao.getConnection();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO `btl_web`.`order_item` (`orderQuantity`, `oi_orderid`, `oi_bookid`) VALUES (?, ?, ?);\r\n"
						+ "");
				ps.setInt(1, i.getQuantity());
				ps.setInt(2, orderid);
				ps.setInt(3, i.getBookid());
				ps.executeUpdate();
			}
			delCartByUserid(userid);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
