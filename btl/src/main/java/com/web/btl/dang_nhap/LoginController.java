package com.web.btl.dang_nhap;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.btl.books.Book;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	LoginDAO loginDAO = new LoginDAO();
	
	@GetMapping("/dang-nhap")
	public String dangNhap() {
		return "login";
	}
	
	@PostMapping("/login")
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
		Account a = loginDAO.checkAcocunt(username, password);
		
		if(a == null) {
			return "redirect:/dang-nhap";
		} else {
			session.setAttribute("account", a);
			return "redirect:/books";
		}
	}
	
	@GetMapping("/logout")
	public String checkLogout(HttpSession session) {
		Account a = (Account)session.getAttribute("account");
		
		if(a != null) {
			session.removeAttribute("account");
			return "redirect:/books";
		} else {
			return "redirect:/books";
		}
		
	}
	
	@GetMapping("/dang-ki")
	public String dangKi(HttpSession session) {
		Account a = (Account)session.getAttribute("account");
		
		if(a != null) {
			return null;
		} else {
			return "singup";
		}
	}
	
	@PostMapping("/singup")
	public String checkSingUp(Model model, HttpSession session,
			@RequestParam("fullname") String fullname,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Account a = loginDAO.checkAccountByUsername(username);
		Account newACC = new Account(fullname, username, password);
		if(a != null) {
			return "redirect:/dang-ki";
		} else {
			loginDAO.createNewAccount(newACC);
			session.setAttribute("account", loginDAO.checkAcocunt(username, password));
			return "redirect:/books";
		}
	}
	
	@PostMapping("/check-singup")
	@ResponseBody
    public ResponseEntity<?> checkDuplicate(@RequestBody Account acc) {
        String username = acc.getUsername().trim();
        boolean isDuplicate = loginDAO.checkDupli(username);
        System.out.println(isDuplicate);
        Map<String, Object> response = new HashMap<>();
        response.put("duplicate", isDuplicate);

        return ResponseEntity.ok(response);
    }
}
