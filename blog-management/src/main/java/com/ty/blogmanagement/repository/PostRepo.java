package com.ty.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.blogmanagement.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

	void deletePostByUserId(int userId);

	
}
