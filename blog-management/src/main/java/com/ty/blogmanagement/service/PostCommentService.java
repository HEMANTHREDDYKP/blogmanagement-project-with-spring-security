package com.ty.blogmanagement.service;

import java.util.List;

import com.ty.blogmanagement.dto.PostCommentDto;

public interface PostCommentService {

	public PostCommentDto createPostComment(int userId,int postId,PostCommentDto postCommentDto );
	public PostCommentDto updatePostComment(int userId,int postId,PostCommentDto postCommentDto );
	public PostCommentDto deletePostComment(int userId, int postId,int postCommentId);
	public PostCommentDto getPostComment(int postId );
	public List<PostCommentDto> getAllPostComment();
	
}
