package com.ty.blogmanagement.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private int id;
	@NotNull(message = "first name cant be empty")
	private String firstName;
	@NotNull(message = "middle name cant be empty")
	private String middleName;
	@NotNull(message = "last name cant be empty")
	private String lastName;
	@NotNull(message = "enter proper mobile number")
	private long mobile;
	@Email(message = "Enter proper email")
	private String email;
	private String role;
	@NotNull
	@Size(min = 3,max = 10,message = "password length should be >=3 and <=10")
	private String password;
	private LocalDateTime registeredAt;
	private LocalDateTime lastLogin;
	
	//private List<PostDto> posts;
}
