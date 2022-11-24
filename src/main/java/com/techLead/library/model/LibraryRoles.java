package com.techLead.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;



import lombok.Data;

@Entity
@Data
@Table(name = "TB_ROLE")
public class LibraryRoles implements GrantedAuthority, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(nullable = false, unique = true)
	private String roleName;

	@Override
	public String getAuthority() {
		return this.roleName.toString();
		
		
	}

}
	
