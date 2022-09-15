package com.jumpee.commerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jumpee.commerce.repository.UserRepository;
import com.jumpee.commerce.response.AuthResponse;
import com.jumpee.commerce.response.Message;
import com.jumpee.commerce.exception.EmailCheckException;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.service.AuthService;
import com.jumpee.commerce.service.RoleService;
import com.jumpee.commerce.service.UserService;
import com.jumpee.commerce.model.Auth;

import net.bytebuddy.utility.RandomString;

@RestController
public class RegisterController 
{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthService authService;
	
	private final UserRepository userRepository;
	private String message;
	
	RegisterController(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> createUser(@Valid @RequestBody User newUser)
	{  
		String verifyCode = RandomString.make(5);
		roleService.roleCreated();
		
		User emailExists = userRepository.findByEmail(newUser.getEmail());
		if(emailExists == null)
		{
			userService.createUser(newUser, verifyCode);
			authService.createToken(newUser, verifyCode);
			message = "Account Created";		
			return ResponseEntity.created(null).body(new AuthResponse(verifyCode, message));
		}
		
		throw new EmailCheckException();
	}
	@PutMapping("/verify")
	public ResponseEntity<Message> accountActivate(@Valid @RequestBody Auth token)
	{
		User user = authService.verifyToken(token);
		userService.userActivate(user);
		message = "Account Verified";
		return ResponseEntity.ok().body(new Message(message));
	}
}
