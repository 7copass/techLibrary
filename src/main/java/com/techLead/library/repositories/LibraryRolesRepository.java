package com.techLead.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techLead.library.model.LibraryRoles;

public interface LibraryRolesRepository extends JpaRepository<LibraryRoles, Long> {
	
	 
	Optional<LibraryRoles> findById(Long id);
	
	
}
