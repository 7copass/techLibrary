package com.techLead.library.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techLead.library.model.Book;

import com.techLead.library.repositories.BooksRepository;
import com.techLead.library.repositories.LibraryRolesRepository;

@Service
@Transactional
public class BookServiceImpl implements IBooks {

	@Autowired
	BooksRepository booksRepository;
	
	@Autowired 
	LibraryRolesRepository rolesRepository;

	@Override
	public Book save(Book book) {
		return booksRepository.save(book);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return booksRepository.findById(id);

	}

	@Override
	public List<Book> findAll() {
		return booksRepository.findAll();

	}

	@Override
	public void delete(Book book) {
		booksRepository.delete(book);

	}

	

}
