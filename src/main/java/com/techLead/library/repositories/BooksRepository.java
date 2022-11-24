package com.techLead.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techLead.library.model.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long>{



}
