package com.boot.mongo.curd.app.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.boot.mongo.curd.app.model.Book;

@JaversSpringDataAuditable
public interface BookRepository extends MongoRepository<Book, String>{

}
