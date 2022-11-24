package com.techLead.library.service;



import com.techLead.library.model.LibraryUser;

public interface ILibraryService {

	LibraryUser createUser(LibraryUser user);

	LibraryUser findById(Long id); 
	
	

}
