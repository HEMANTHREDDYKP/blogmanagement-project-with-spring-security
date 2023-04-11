package com.ty.blogmanagement.serviceimpli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ty.blogmanagement.dto.UserDto;
import com.ty.blogmanagement.entity.User;
import com.ty.blogmanagement.exception.ResourceNotFoundException;
import com.ty.blogmanagement.repository.PostRepo;
import com.ty.blogmanagement.repository.UserRepo;
import com.ty.blogmanagement.service.UserService;


//flow 4th step implement userdetailsservice and override methods
@Service
public class UserServiceImpli implements UserService, UserDetailsService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepo.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto getUser(int userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastLogin(userDto.getLastLogin());
		user.setLastName(userDto.getLastName());
		user.setMiddleName(userDto.getMiddleName());
		user.setRole(userDto.getRole());
		user.setMobile(userDto.getMobile());
		user.setPassword(userDto.getPassword());
		userRepo.save(user);

		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto deleteUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepo.delete(user);

		return modelMapper.map(user, UserDto.class);

	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();
		return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	
	//flow 5th step using user repo get the details of user from database and convert into userdetails object
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("in user details");
		User user = userRepo.findByEmail(username);
		System.out.println(user.getEmail());
		if(user!=null) {
			System.err.println("in user details if block");
//		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
//				user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

			return 	new org.springframework.security.core.userdetails.User(user.getEmail(),
					user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

		}
		else {
			
			throw new UsernameNotFoundException(username);
		}
	}

}
