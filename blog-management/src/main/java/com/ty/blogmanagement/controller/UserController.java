package com.ty.blogmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.blogmanagement.dto.Login;
import com.ty.blogmanagement.dto.UserDto;
import com.ty.blogmanagement.response.BlogResponse;
import com.ty.blogmanagement.service.UserService;
import com.ty.blogmanagement.util.JWTUtil;

@RestController
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService; 
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/user")
	public ResponseEntity<BlogResponse> createUser( @RequestBody UserDto userDto) {
		
		UserDto savedUser=userService.createUser(userDto);

		 return new ResponseEntity<>(new BlogResponse(false, 200, "User created successfully", savedUser),
				HttpStatus.OK);
	}
	@PostMapping("/user/{userId}")
	public ResponseEntity<BlogResponse> updateUser( @RequestBody UserDto userDto,@PathVariable(value = "userId") int userId) {

	 
	 return new ResponseEntity<>(new BlogResponse(false, 200, "User updated successfully",userService.updateUser(userDto, userId)),
				HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<BlogResponse>  getUser(@PathVariable(value = "userId") int userId) {

		
		 return new ResponseEntity<>(new BlogResponse(false, 200, "User retrived successfully",userService.getUser(userId)),
					HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<BlogResponse>  getAllUsers() {

		 return new ResponseEntity<>(new BlogResponse(false, 200, "Users retrived successfully",userService.getAllUsers()),
					HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<BlogResponse>  deleteUser(@PathVariable(value = "userId") int userId) {

		 
		 return new ResponseEntity<>(new BlogResponse(false, 200, "User deleted successfully", userService.deleteUser(userId)),
					HttpStatus.OK);
		 
		 
		
	}
	
	
	//flow-1st
	@PostMapping("/login")
	public ResponseEntity<BlogResponse> login(@RequestBody Login login){
		System.err.println("1");
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		System.err.println(2);
		String token=jwtUtil.generateToken(login.getEmail());
		System.err.println(3);
		return new ResponseEntity<>(new BlogResponse(false, 200, "Token generated successfully", token),HttpStatus.OK);
	}
	
	
	
	

}
