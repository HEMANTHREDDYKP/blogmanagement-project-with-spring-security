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

import com.ty.blogmanagement.dto.PostDto;
import com.ty.blogmanagement.response.BlogResponse;
import com.ty.blogmanagement.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/post")
	public ResponseEntity<BlogResponse> createPost(@PathVariable("userId") int userId, @RequestBody PostDto postDto) {
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 201, "Post created successfully", postService.createPost(userId, postDto)),
				HttpStatus.OK);
	}

	@PutMapping("/user/{userId}/post")
	public ResponseEntity<BlogResponse> updatePost(@PathVariable int userId,  @RequestBody PostDto postDto) {

		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Post updated successfully", postService.updatePost(userId, postDto)),
				HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<BlogResponse> getPostOfSpecificUser(@PathVariable(value = "userId") int userId,
			@PathVariable(value = "postId") int postId) {

		
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Post retrived successfully",postService.getSpecificPostOfUser(userId, postId)),
				HttpStatus.OK);
	}

	@DeleteMapping("user/{userId}/post/{postId}")
	public ResponseEntity<BlogResponse> deletePost(@PathVariable int userId, @PathVariable int postId) {

		
		if(postService.deletePost(userId, postId)!=null) {
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Post deleted successfully",postService.deletePost(userId, postId)),
				HttpStatus.OK);
		}
		else {
			return new ResponseEntity<BlogResponse>(new BlogResponse(true,400 , "Post not deleted ",postService.deletePost(userId, postId)),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/user/post/{postId}")
	public ResponseEntity<BlogResponse> getPost(@PathVariable int postId) {

		return new ResponseEntity<BlogResponse>(new BlogResponse(false,400 , "Post retrived successfully ",postService.getPost(postId)),
				HttpStatus.OK);

	}

}
