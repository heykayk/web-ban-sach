package com.web.btl.cart;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.btl.dang_nhap.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	CartDAO cartDAO = new CartDAO();
	
	@GetMapping("/cart")
	public String listCart() {
		return "cart";
	}
	
	
	
	@GetMapping("/listcart")
	@ResponseBody
	public List<Cart> getAllCart(HttpSession session){
		Account a = (Account) session.getAttribute("account");
		List<Cart> list = cartDAO.getAllCart(a.getId());
		return list;
	}
	
	@GetMapping("/addCart/{id}")
	@ResponseBody
	public void addCart(HttpSession session, @PathVariable String id) {
		Account a = (Account) session.getAttribute("account");
		
		int vtri = cartDAO.checkCart(a.getId(), Integer.parseInt(id));
		
		if(vtri > 0) {
			cartDAO.updateAddCart(vtri);
		} else {
			cartDAO.newCart(a.getId(), Integer.parseInt(id));
		}
	}
	
	@GetMapping("/subCart/{id}")
	@ResponseBody
	public void subCart(HttpSession session, @PathVariable String id) {
		Account a = (Account) session.getAttribute("account");
		
		int vtri = cartDAO.checkCart(a.getId(), Integer.parseInt(id));
		cartDAO.updateSubCart(vtri);
	}
	
	@GetMapping("/delCart/{id}")
	@ResponseBody
	public void delCart(HttpSession session, @PathVariable String id) {
		Account a = (Account) session.getAttribute("account");
		
		int vtri = cartDAO.checkCart(a.getId(), Integer.parseInt(id));
		cartDAO.delCart(vtri);
	}
	
	@GetMapping("/payOrder")
	@ResponseBody
	public void payOrder(HttpSession session) {
		Account a = (Account) session.getAttribute("account");
		
		cartDAO.newOrder(a.getId());
	}
}
