package com.userregistration.springboot.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface SecurityService {
	UsernamePasswordAuthenticationToken login(String username, String password, HttpSession session);
}
