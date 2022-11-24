package com.techLead.library.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.techLead.library.model.LibraryRoles;

import lombok.Data;

@Data
public class LibraryUserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Name  is required")
	@Length(min = 3)
	private String name;
	@NotBlank(message = "Email  is required")
	private String email;

	private String password;

	private Set<LibraryRoles> roles = new HashSet<>();

	

	
 
}
