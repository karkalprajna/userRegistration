package com.userregistration.springboot.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	AuthenticationManager authenticationManager ;

	@Override
	public UsernamePasswordAuthenticationToken login(String username, String password,HttpSession session) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
        = new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());

        //authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            session.setAttribute("username", userDetails.getUsername());
            return usernamePasswordAuthenticationToken;
        }
        
        //check for invalid input result
		return null;
	}
	
	

}
