package com.techLead.library.dtos;

import java.io.Serializable;



import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
public class BookDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Book name is required")	
	private String name;
	@NotNull(message = "Author name is required")
	private String author;
	
	
}
