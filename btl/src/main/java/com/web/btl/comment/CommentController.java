package com.web.btl.comment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.btl.dang_nhap.Account;

import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	CommentDAO commentDAO = new CommentDAO();
	
	@GetMapping("/comments/{id}")
	@ResponseBody
	public List<Comment> getAllComment(@PathVariable String id){
		return commentDAO.getAllComment(Integer.parseInt(id));
	}
	
	@PostMapping("/comment")
	@ResponseBody
	public Comment addComment(@RequestBody Comment comm, HttpSession session) {
		Account a = (Account) session.getAttribute("account");
		comm.setUserid(a.getId());
		commentDAO.addComment(comm);
		return commentDAO.getTop1Comment(comm.getBookid());
	}
}
