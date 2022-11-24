package com.techLead.library.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techLead.library.dtos.BookDto;
import com.techLead.library.dtos.MessageDTO;
import com.techLead.library.model.Book;
import com.techLead.library.model.LibraryUser;
import com.techLead.library.repositories.LibraryRolesRepository;
//import com.techLead.library.repositories.LibraryRolesRepository;
import com.techLead.library.service.BookServiceImpl;
import com.techLead.library.service.LibraryServiceImpl;

@RestController
@RequestMapping("/books")
@CrossOrigin (origins = "http://localhost:4200", allowedHeaders = "*")
public class BookController {

	@Autowired
	BookServiceImpl bookServiceImpl;

	@Autowired
	LibraryServiceImpl libraryServiceImpl;

	@Autowired
	LibraryRolesRepository rolesRepository;
	@CrossOrigin (origins = "http://localhost:4200", allowedHeaders = "*")
	@PostMapping("/register-book/{id}")
	public ResponseEntity<Book> createBook(@RequestBody @Valid BookDto book, @PathVariable Long id) {
		Book newBook = new Book();
		BeanUtils.copyProperties(book, newBook);
		LibraryUser user = libraryServiceImpl.findById(id);
		newBook.setRegistrationDate(LocalDateTime.now());
		newBook.setCreatedBy(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookServiceImpl.save(newBook));

	}
	

	@CrossOrigin (origins = "http://localhost:4200", allowedHeaders = "*")
	@GetMapping("/all-books")
	public ResponseEntity<List<Book>> allBooks() {
		return ResponseEntity.status(HttpStatus.OK).body(bookServiceImpl.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Book>> detailBook(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(bookServiceImpl.findById(id));

	} 
  
	@PutMapping("update/{idBook}/{idUser}") 
	public ResponseEntity<?> updateBook(@PathVariable Long idBook, @PathVariable Long idUser, @RequestBody BookDto bookDto) {
		Optional<Book> bookOptional = bookServiceImpl.findById(idBook);
		LibraryUser user = libraryServiceImpl.findById(bookOptional.get().getCreatedBy().getId());
		LibraryUser userParam = libraryServiceImpl.findById(idUser);
		if (!bookOptional.isPresent()) { 
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		} 
		
		if (user.getId() != userParam.getId()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageDTO(
					"This book was not created by you.\r\n" + "Books can only be deleted by users who created them."));
		}
		
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book);
		book.setId(bookOptional.get().getId());
		book.setRegistrationDate(bookOptional.get().getRegistrationDate());
		book.setCreatedBy(userParam);
		return ResponseEntity.status(HttpStatus.OK).body(bookServiceImpl.save(book));
	}
	
	
	
	@PutMapping("update-any/{id}")
	public ResponseEntity<Book> updateAny(@PathVariable Long id, @RequestBody BookDto bookDto) {
		Optional<Book> bookOptional = bookServiceImpl.findById(id);
		if (!bookOptional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book); 
		book.setId(bookOptional.get().getId());
		book.setRegistrationDate(bookOptional.get().getRegistrationDate());
		book.setCreatedBy(bookOptional.get().getCreatedBy());
		return ResponseEntity.status(HttpStatus.OK).body(bookServiceImpl.save(book));
	
	}

	@DeleteMapping("/delete/{idBook}/{idUser}") // apenas acesso de client
	public ResponseEntity<MessageDTO> deleteBook(@PathVariable Long idBook, @PathVariable Long idUser) {
		Optional<Book> bookOptional = bookServiceImpl.findById(idBook);
		LibraryUser user = libraryServiceImpl.findById(bookOptional.get().getCreatedBy().getId());
		LibraryUser userParam = libraryServiceImpl.findById(idUser);

		// verifica se tem o livro
		if (!bookOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO("Book not found"));
		}
		// verifica quem criou
		if (user.getId() != userParam.getId()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageDTO(
					"This book was not created by you.\r\n" + "Books can only be deleted by users who created them."));
		}

		bookServiceImpl.delete(bookOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Book deleted"));

	}

	@DeleteMapping("/delete-one/any/{idBook}") // so quem te acesso é admin
	public ResponseEntity<MessageDTO> deleteAny(@PathVariable Long idBook) {

		Optional<Book> bookOptional = bookServiceImpl.findById(idBook);

		if (!bookOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO("Book not found"));

		}
		bookServiceImpl.delete(bookOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Book deleted"));
	}

}
