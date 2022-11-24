package com.techLead.library.service;

import java.util.List;
import java.util.Optional;

import com.techLead.library.model.Book;

public interface IBooks {

	Book save(Book book);

	Optional<Book> findById(Long idBook);

	List<Book> findAll();

	void delete(Book book);
	
 
	
}
