package com.ty.blogmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.blogmanagement.dto.PostCommentDto;
import com.ty.blogmanagement.response.BlogResponse;
import com.ty.blogmanagement.service.PostCommentService;

@RestController
public class PostCommentController {

	@Autowired
	private PostCommentService postCommentService;
	
	@PostMapping("user/{userId}/post/{postId}/postComment")
	public ResponseEntity<BlogResponse> createPostComment(@PathVariable int userId,@PathVariable int postId,@RequestBody PostCommentDto postCommentDto){
		
		
		 return new ResponseEntity<BlogResponse>(new BlogResponse(false, 201, "Post Comment created successfully", postCommentService.createPostComment(userId, postId, postCommentDto)),
					HttpStatus.CREATED);
	}
	@PutMapping("user/{userId}/post/{postId}/postComment")
	public ResponseEntity<BlogResponse> updatePostComment(@PathVariable int userId,@PathVariable int postId,@RequestBody PostCommentDto postCommentDto){
		
		
		 return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Post Comment updated successfully",postCommentService.updatePostComment(userId, postId, postCommentDto)),
					HttpStatus.OK);
	}
	
	@DeleteMapping("user/post/postComment/{postComment}")
	public ResponseEntity<BlogResponse> deletePostComment(@PathVariable int userId,@PathVariable int postId,@PathVariable int postCommentId){
		
		;
		
		if(postCommentService.deletePostComment(userId, postId, postCommentId)!=null) {
			return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Post comment deleted successfully",postCommentService.deletePostComment(userId, postId, postCommentId)),
					HttpStatus.OK);
			}
			else {
				return new ResponseEntity<BlogResponse>(new BlogResponse(true,400 , "Post comment not deleted ",postCommentService.deletePostComment(userId, postId, postCommentId)),
						HttpStatus.BAD_REQUEST);
			}
	}
	
	@GetMapping("user/post/postComment/{postCommentId}")
	public ResponseEntity<BlogResponse> getPostCommentById(@PathVariable int postCommentId){
		
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Post comment retrived successfully",postCommentService.getPostComment(postCommentId)),
				HttpStatus.OK);
	}
	
	@GetMapping("user/post/postComment")
	public ResponseEntity<BlogResponse> getAllPostComments(){
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Post comments retrived successfully",postCommentService.getAllPostComment()),
				HttpStatus.OK);
	}
	
	
	
}
