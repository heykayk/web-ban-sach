package com.web.btl.order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.btl.dang_nhap.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	OrderDAO orderDAO = new OrderDAO();
	
	@GetMapping("/don-hang")
	public String donHang() {
		return "don-hang";
	}
	
	@GetMapping("/xac-nhan-don-hang")
	public String xacNhanDonHang() {
		return "OrderAdmin";
	}
	
	
	@GetMapping("/listOrder")
	@ResponseBody
	public List<Order> getAllOrder(HttpSession session){
		Account a = (Account) session.getAttribute("account");
		if(a.getIsadmin() == 1) {
			return orderDAO.getAllListOrderForAdmin();
		}
		return orderDAO.getAllListOrder(a.getId());
	}
	
	@GetMapping("don-hang/orderItem/{id}")
	@ResponseBody
	public List<OrderItem> getAllOrderItem(@PathVariable String id){
		return orderDAO.getAllOrderItem(Integer.parseInt(id));
	}

	@GetMapping("don-hang/order/{id}")
	@ResponseBody
	public Order getOrderById(@PathVariable String id) {
		return orderDAO.getOrderById(Integer.parseInt(id));
	}
	
	@GetMapping("delOrder/{id}")
	@ResponseBody
	public void dellOrder(@PathVariable String id) {
		orderDAO.delOrderById(Integer.parseInt(id));
	}
	
	@GetMapping("update/{id}")
	@ResponseBody
	public void updateOrder(@PathVariable String id) {
		orderDAO.updateOrder(Integer.parseInt(id));
	}
}
