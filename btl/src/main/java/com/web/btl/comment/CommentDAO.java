package com.web.btl.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.btl.DAO.DAO;

public class CommentDAO {
	DAO dao = new DAO();

	public List<Comment> getAllComment(int bookid){
		List<Comment> list = new ArrayList<>();
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT comments.id, comments.rate, comments.comment, comments.c_userid, comments.c_bookid, user.username FROM btl_web.comments\r\n"
					+ "join user on comments.c_userid = user.id\r\n"
					+ "where c_bookid = ?\r\n"
					+ "order by id;");
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int rate = rs.getInt("rate");
				String comment = rs.getString("comment");
				int c_userid = rs.getInt("c_userid");
				int c_bookid = rs.getInt("c_bookid");
				String username = rs.getString("username");
				
				list.add(new Comment(id, rate, comment, c_userid, c_bookid, username));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addComment(Comment comm) {
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `btl_web`.`comments` (`rate`, `comment`, `c_userid`, `c_bookid`) VALUES (?, ?, ?, ?);\r\n"
					+ "");
			ps.setInt(1, comm.getRate());
			ps.setString(2, comm.getComment());
			ps.setInt(3, comm.getUserid());
			ps.setInt(4, comm.getBookid());
			
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Comment getTop1Comment(int bookid) {
		
		try {
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT comments.id, comments.rate, comments.comment, comments.c_userid, comments.c_bookid, user.username FROM btl_web.comments\r\n"
					+ "join user on comments.c_userid = user.id\r\n"
					+ "where c_bookid = ?\r\n"
					+ "order by id desc\r\n"
					+ "limit 1;");
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int rate = rs.getInt("rate");
				String comment = rs.getString("comment");
				int c_userid = rs.getInt("c_userid");
				int c_bookid = rs.getInt("c_bookid");
				String username = rs.getString("username");
				
				return new Comment(id, rate, comment, c_userid, c_bookid, username);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
