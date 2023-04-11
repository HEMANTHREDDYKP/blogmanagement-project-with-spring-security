package com.ty.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.blogmanagement.entity.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

	User findByEmail(String username);

}
