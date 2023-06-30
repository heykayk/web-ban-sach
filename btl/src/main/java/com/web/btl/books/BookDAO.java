package com.web.btl.books;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.web.btl.DAO.DAO;

public class BookDAO {
	DAO dao = new DAO();
	
	public List<Book> getAllBook(){
		List<Book> list = new ArrayList<>();
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT books.id, books.title, books.author,type.id as typeid, type.category, books.bookPageNumber, books.sold, books.releaseDate, books.description, books.image, books.price\r\n"
					+ "FROM btl_web.books\r\n"
					+ "Join btl_web.type on books.category= type.id;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int typeid = rs.getInt("typeid");
				String category = rs.getString("category");
				int bookPageNumber = rs.getInt("bookPageNumber");
				int sold = rs.getInt("sold");
				Date releaseDate = rs.getDate("releaseDate");
				String description = rs.getString("description");
				String image = rs.getString("image");
				int price = rs.getInt("price");
				
				list.add(new Book(id, title, author, typeid, category, bookPageNumber, sold, releaseDate, description, image, price));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Book getBookById(int id) {
		Book book = new Book();
		
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT books.id, books.title, books.author,type.id as typeid, type.category, books.bookPageNumber, books.sold, books.releaseDate, books.description, books.image, books.price\r\n"
					+ "FROM btl_web.books\r\n"
					+ "Join btl_web.type on books.category= type.id\r\n"
					+ "where books.id = ?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author")); 
				book.setTypeid(rs.getInt("typeid"));
				book.setCategory(rs.getString("category")); 
				book.setBookPageNumber(rs.getInt("bookPageNumber")); 
				book.setSold(rs.getInt("sold")); 
				book.setReleaseDate(rs.getDate("releaseDate")); 
				book.setDescription(rs.getString("description")); 
				book.setImage(rs.getString("image")); 
				book.setPrice(rs.getInt("price"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	public List<Type> getAllType(){
		List<Type> list = new ArrayList<>();
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM btl_web.type;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				list.add(new Type(id, category));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateBook(Book book) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE `btl_web`.`books` SET `title` = ?, `author` = ?, `category` = ?, `bookPageNumber` = ?, `releaseDate` = ?, `description` = ?, `image` = ?, `price` = ? WHERE (`id` = ?);\r\n"
					+ "");
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getTypeid());
			ps.setInt(4, book.getBookPageNumber());
			ps.setDate(5, book.getReleaseDate());
			ps.setString(6, book.getDescription());
			ps.setString(7, book.getImage());
			ps.setInt(8, book.getPrice());
			ps.setInt(9,  book.getId());
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void newBook(Book book) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `btl_web`.`books` (`title`, `author`, `category`, `bookPageNumber`, `sold`, `releaseDate`, `description`, `image`, `price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);\r\n"
					+ "");
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getTypeid());
			ps.setInt(4, book.getBookPageNumber());
			ps.setInt(5, book.getSold());
			ps.setDate(6, book.getReleaseDate());
			ps.setString(7, book.getDescription());
			ps.setString(8, book.getImage());
			ps.setInt(9, book.getPrice());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int id) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `btl_web`.`books` WHERE (`id` = ?);");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean checkDuplicate(String title, String author) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM btl_web.books\r\n"
					+ "where title = ? and author = ?;");
			ps.setString(1, title);
			ps.setString(2, author);
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
