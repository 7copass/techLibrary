package com.techLead.library.repositories;

import java.util.Optional;

import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techLead.library.model.LibraryUser;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Long>{
	Optional<LibraryUser> findByName(String username);
	Optional<LibraryUser> findByEmail(String email);
	Boolean existsByEmail(LibraryUser user);
	
	  
}
