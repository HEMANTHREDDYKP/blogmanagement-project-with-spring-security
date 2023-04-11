package com.ty.blogmanagement.service;

import java.util.List;

import com.ty.blogmanagement.dto.UserDto;

public interface UserService {

	
	public UserDto createUser(UserDto userDto);
	public UserDto getUser(int userId);
	public UserDto updateUser(UserDto userDto,int userId);
	public UserDto deleteUser(int userId);
	public List<UserDto> getAllUsers();
	
	
	
}
