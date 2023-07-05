package com.bookStore.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.repository.MyBookRepository;

@Service
public class MyBookService {
	@Autowired
	private MyBookRepository mybook;
	
	public void saveMybook(MyBookList book) {
		mybook.save(book);
	}
	
	public List<MyBookList>getAllmyBooks(){
		return mybook.findAll();
		
	}
	public void deletById(int id) {
		mybook.deleteById(id);
	}
	

}
