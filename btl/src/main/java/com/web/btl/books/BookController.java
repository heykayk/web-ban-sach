package com.web.btl.books;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	BookDAO bookDAO = new BookDAO();
	
	@GetMapping("/books")
	public String getAllBooks(HttpSession session, Model model) {
		model.addAttribute("books", bookDAO.getAllBook());
		return "books";
	}
	
	@GetMapping("/book/{id}")
	public String getBookById(@PathVariable String id, Model model) {
		Book book = bookDAO.getBookById(Integer.parseInt(id));
		model.addAttribute("book", book);
		model.addAttribute("listType", bookDAO.getAllType());
		return "book-detail";
	}
	
	@GetMapping("/book/deleteB/{id}")
	public String deleteBookById(@PathVariable String id) {
		bookDAO.deleteBook(Integer.parseInt(id));
		return "redirect:/books";
	}
	
	private Date checkDate(String s) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date parsedDate = dateFormat.parse(s);
            Date sqlDate = new Date(parsedDate.getTime());
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@PostMapping("book/savebook/{id}")
	public String saveBook(HttpSession session, 
							Book book,
							@RequestParam("title") String title,
							@RequestParam("author") String author,
							@RequestParam("description") String description,
							@RequestParam("releaseDate") String releaseDate,
							@RequestParam("bookPageNumber") String bookPageNumber,
							@RequestParam("price") String price,
							@RequestParam("_method") String _method,
							@RequestParam("hidden_type") String hidden_type,
							@RequestParam("hidden_url") String hidden_url) {
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setReleaseDate(checkDate(releaseDate));
		book.setBookPageNumber(Integer.parseInt(bookPageNumber));
		book.setTypeid(Integer.parseInt(hidden_type));
		book.setImage(hidden_url);
		book.setPrice(Integer.parseInt(price));
		
		if(_method.equalsIgnoreCase("put")) {
			bookDAO.updateBook(book);
		} else {
			bookDAO.newBook(book);
		}
		return "redirect:/books" ;
	}
	
	@PostMapping("/check-duplicate")
	@ResponseBody
    public ResponseEntity<?> checkDuplicate(@RequestBody Book book) {
        String title = book.getTitle().trim();
        String author = book.getAuthor().trim();
        
        
        boolean isDuplicate = bookDAO.checkDuplicate(title, author);
        System.out.println(isDuplicate);
        
        // Trả về phản hồi JSON với trạng thái trùng lặp
        Map<String, Object> response = new HashMap<>();
        response.put("duplicate", isDuplicate);

        return ResponseEntity.ok(response);
    }
}