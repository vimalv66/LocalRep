package com.bookStore.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.service.BookService;
import com.bookStore.bookStore.service.MyBookService;

@Controller
public class BookController {
	@Autowired
	private BookService service;
	@Autowired
	private MyBookService mb;
	@GetMapping("/")
	public String home() {
	 return "home";
 }
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book> list=service.getAllBook();
		return new ModelAndView("bookList","book",list);
	}
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/available_books";
	}
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		
		List<MyBookList>list=mb.getAllmyBooks();
		model.addAttribute("book", list);
		return "myBooks";
		
	}
	@GetMapping("/my_list/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookbyID(id);
		mb.saveMybook(new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice()));
		return "redirect:/my_books";
		
	}
	@RequestMapping("/deletMyList/{id}")
	public String deleteMyBookList(@PathVariable("id")int id) {
		mb.deletById(id);
		return "redirect:/my_books";
		
	}
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookbyID(id);
		model.addAttribute("book",b);
		return "bookEdit";
		
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deletemyBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/available_books";
		
	}
	
	
}
