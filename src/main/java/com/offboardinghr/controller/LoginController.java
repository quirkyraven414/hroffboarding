package com.offboardinghr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offboardinghr.entity.LoginRequest;
import com.offboardinghr.entity.LoginResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class LoginController {
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		// In a real app, you'd retrieve the user from a database and validate
		// credentials
		if (username.equals("hr_head") && password.equals("password")) {
			return ResponseEntity.ok(new LoginResponse("HR"));
		} else if (username.equals("admin_head") && password.equals("password")) {
			return ResponseEntity.ok(new LoginResponse("Admin"));
		} else if (username.equals("finance_head") && password.equals("password")) {
			return ResponseEntity.ok(new LoginResponse("Finance"));
		} else if (username.equals("it_head") && password.equals("password")) {
			return ResponseEntity.ok(new LoginResponse("IT"));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}
}


