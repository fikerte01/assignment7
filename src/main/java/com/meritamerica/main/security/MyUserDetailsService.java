package com.meritamerica.main.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.meritamerica.main.repositories.*;



	@Component
	
	public class MyUserDetailsService implements UserDetailsService {
	    private UserRepo users;
	    public MyUserDetailsService(UserRepo users) {
	        this.users = users;
	    }
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return this.users.findByUserName(username)
	            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
	    }
	}


