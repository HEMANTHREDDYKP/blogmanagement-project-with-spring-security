package com.ty.blogmanagement.service;

import com.ty.blogmanagement.dto.PostDto;

public interface PostService {

	
	
	public PostDto createPost(int userId,PostDto postDto);
	public PostDto getSpecificPostOfUser(int userId,int postId);
	public PostDto updatePost(int postId,PostDto postDto);
	public PostDto deletePost(int userId,int postId);
	public PostDto getPost(int postId);
	
	
 	
}
