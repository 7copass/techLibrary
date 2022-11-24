package com.techLead.library.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techLead.library.model.LibraryUser;
import com.techLead.library.repositories.UserRepository;
import com.techLead.library.service.exceptions.ObjectNotFounException;

@Service
@Transactional
public class LibraryServiceImpl implements ILibraryService {

	@Autowired
	UserRepository userRepository;

	@Override
	public LibraryUser createUser(LibraryUser user) {
		return userRepository.save(user);
	}

	@Override
	public LibraryUser findById(Long id) {
		Optional<LibraryUser> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFounException(
				"Object not found! Id: " + id + ", Type: " + LibraryUser.class.getName())); 
	}

	public List<LibraryUser> findAll() {

		return userRepository.findAll();
	}

	public Optional<LibraryUser> login(Optional<LibraryUser> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<LibraryUser> userdb = userRepository.findByEmail(user.get().getEmail());

		if (userdb.isPresent()) {
			if (encoder.matches(user.get().getPassword(), userdb.get().getPassword())) {
			
				user.get().setId(userdb.get().getId());
				user.get().setEmail(userdb.get().getEmail());
				user.get().setPassword(userdb.get().getPassword());
				user.get().setName(userdb.get().getName()); 
				user.get().setRoles(userdb.get().getRoles());
			
				return user;
			
			}
		}

		return Optional.empty(); 
		
		
	}

}
