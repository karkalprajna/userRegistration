package com.userregistration.springboot.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.userregistration.springboot.model.User;
import com.userregistration.springboot.service.SecurityService;
import com.userregistration.springboot.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@PostMapping("/registration")
	public ResponseEntity<User> createUser(@RequestBody User user) { 
		return ResponseEntity.ok().body(userService.save(user));
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,HttpSession session) {
		 
		return securityService.login(username, password,session).toString();
	}

	
	@GetMapping("/logout")
	public String logout(SessionStatus session) {
		SecurityContextHolder.getContext().setAuthentication(null);
		session.setComplete();
		return "loged out!";
	}

	// forgot password
	@PostMapping("/forgotpassword")
	public ResponseEntity<String> forgotPassword(@RequestBody User user){
		return ResponseEntity.ok().body(userService.forgotpassword(user.getUsername()));
	}
	
	
	@GetMapping("/registration/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		return ResponseEntity.ok().body(userService.findByUsername(username));
	}
}
