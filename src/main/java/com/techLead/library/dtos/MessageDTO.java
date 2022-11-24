package com.techLead.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageDTO {
	
	private String message;

	@Override
	public String toString() {
		return "MessageDTO [message=" + message + "]";
	}
	
	
}
