package com.boot.mongo.curd.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.mongo.curd.app.model.Book;
import com.boot.mongo.curd.app.repository.BookRepository;
@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll().stream().map(u->{
			Book book=new Book();
			book.setId(u.getId());
			book.setBookName(u.getBookName());
			book.setAuthorName(u.getAuthorName());
			return book;
		}).collect(Collectors.toList());
	}
	public Book getByBookId(String id) {
		return bookRepository.findById(id).map(u->{
			Book b=new Book();
			b.setId(u.getId());
			b.setBookName(u.getBookName());
			b.setAuthorName(u.getAuthorName());
			return b;
		}).orElse(null);
	}
	public void saveBook(Book book) {
		Book bk=new Book();
		bk.setId(book.getId());
		bk.setBookName(book.getBookName());
		bk.setAuthorName(book.getAuthorName());
		bookRepository.save(book);
	}
	public void updateBook(Book book) {
		Book updateBook=new Book();
		updateBook.setId(book.getId());
		updateBook.setBookName(book.getBookName());
		updateBook.setAuthorName(book.getAuthorName());
	    bookRepository.save(updateBook);
	}
	public void deleteBook(Book book) {
		Book deleteBook=new Book();
		deleteBook.setId(book.getId());
		bookRepository.delete(deleteBook);
	}

}
