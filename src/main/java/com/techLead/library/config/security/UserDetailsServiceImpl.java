package com.techLead.library.config.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techLead.library.model.LibraryUser;
import com.techLead.library.repositories.UserRepository;

 

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LibraryUser libraryUser = userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));
		
		return new org.springframework.security.core.userdetails.User(libraryUser.getUsername(), 
				 libraryUser.getPassword(),
				true, true, true, true, 
				libraryUser.getAuthorities());  
 
	} 
	
	

}
