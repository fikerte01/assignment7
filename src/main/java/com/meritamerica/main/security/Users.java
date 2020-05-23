package com.meritamerica.main.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.NameAlreadyBoundException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity

public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
private boolean enabled;

private String roles = "ROLE_USER";

// create ADMIN and USER authorities

@NotEmpty
private String authorities;

public Users(String username, String password, boolean enabled) {
	super();
	this.username = username;
	this.password = password;
	this.enabled = true;
}

public Users() {
	this.enabled = true;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public String getRoles() {
	return roles;
}

public void setRoles(String roles) {
	this.roles = roles;
}

public String getAuthorities() {
	return authorities;
}

public void setAuthorities(String authorities) {
	this.authorities = authorities;
}
// need list of arrays granting....don't understand???

public List<String> getRoleList(){
	if(this.roles.length()>0) {
		return Arrays.asList(this.roles.split(","));
	}
	return new ArrayList<>();
}
 public List<GrantedAuthority> getAuthorityList(){
	 if (this.authorities.length()>0) {
		 String[] arrayString = this.authorities.split(",");
		 List<GrantedAuthority> authorityList = new ArrayList<>();
		 for(String authory : arrayString) {
			 authorityList.add(new Authority(authory));
		 }
		 return authorityList;
	 }
	 return new ArrayList<GrantedAuthority>();
 }

public UserDetails orElseThrow(Object object) {
	// TODO Auto-generated method stub
	return null;
}


}


