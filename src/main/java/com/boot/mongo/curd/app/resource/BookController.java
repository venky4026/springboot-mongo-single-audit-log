package com.boot.mongo.curd.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.mongo.curd.app.model.Book;
import com.boot.mongo.curd.app.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookInfo(@PathVariable String id){
		return new ResponseEntity<>(bookService.getByBookId(id),HttpStatus.OK);
	}
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getAllBooks(){
		return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
	}
	@PostMapping("/book")
	public ResponseEntity<String> saveBook(@RequestBody Book book){
		bookService.saveBook(book);
		return new ResponseEntity<>("successfully saved with id :"+book.getId(),HttpStatus.OK);
		
	}
	@PutMapping("/book")
	public ResponseEntity<String> updateBook(@RequestBody Book book){
		bookService.updateBook(book);
		return new ResponseEntity<>("successfully updated with id :"+book.getId(),HttpStatus.OK);
	}
	@DeleteMapping("/book")
	public ResponseEntity<String> deleteBook(@RequestBody Book book){
		bookService.deleteBook(book);
		return new ResponseEntity<>("successfully deleted with id :"+book.getId(),HttpStatus.OK);
	}
	
	
}
