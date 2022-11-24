package com.techLead.library.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Setter
public class LibraryUser implements UserDetails,  Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_USERS_ROLES",
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<LibraryRoles> roles = new HashSet<>();
	
  
	



	public void addRole(LibraryRoles newRole) {
	    this.roles.add(newRole);  
	}
	
	
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
	
	

	public Set<LibraryRoles> getRoles() {
		return this.roles;
	}
	

	 
	
 
	

}
