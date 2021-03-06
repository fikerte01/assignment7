package com.meritamerica.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


  @EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	@Autowired
     private  MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	 //set the configuration 
    	 
    	 
		auth.userDetailsService(myUserDetailsService);
    	 
    	
     }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/authenticate").permitAll()
		.antMatchers(HttpMethod.POST, "/AccountHolders").hasRole("ADMIN")
	    .antMatchers("/AccountHolders/**").hasRole("ADMIN")
	    .antMatchers("/Me/**").hasRole("AccountHolder")	
	    .antMatchers(HttpMethod.POST,"/CDOfferings").hasRole("ADMIN")
	    .antMatchers(HttpMethod.POST, "/createUser").hasRole("ADMIN")
	    .antMatchers(HttpMethod.GET, "/CDOfferings").permitAll()		
		.anyRequest().authenticated()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
     
     @Bean
     public PasswordEncoder getPasswordEncoder() {
     return NoOpPasswordEncoder.getInstance();
     }
  }
  
  // step 2
  //Intercept all incoming requests by extracting JWT from the header 
  //Validate and set in execution context

