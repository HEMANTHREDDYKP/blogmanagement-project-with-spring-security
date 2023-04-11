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

import com.ty.blogmanagement.dto.CategoryDto;
import com.ty.blogmanagement.response.BlogResponse;
import com.ty.blogmanagement.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("user/{userId}/post/{postId}/category")
	public ResponseEntity<BlogResponse> createCategory(@PathVariable int userId,@PathVariable int postId,@RequestBody CategoryDto categoryDto){
		
		
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 201, "Category created successfully",categoryService.createCategory(userId, postId, categoryDto)),
				HttpStatus.CREATED);
		
	}
	
	@PutMapping("user/post/category")
	public ResponseEntity<BlogResponse> updateCategory(@RequestBody CategoryDto categoryDto){
		
		
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Category updated successfully",categoryService.updateCategory(categoryDto)),
				HttpStatus.OK);
		
	}
	
	@GetMapping("user/post/category/{categoryId}")
	public ResponseEntity<BlogResponse> getCategory(@PathVariable int categoryId){
		
		
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Category retrived successfully",categoryService.getCategory(categoryId)),
				HttpStatus.OK);
	}
	@GetMapping("user/post/category")
	public ResponseEntity<BlogResponse> getAllCategory(){
		
		
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Category retrived successfully",categoryService.getAllCategory()),
				HttpStatus.OK);
	}
	
	
	@DeleteMapping("user/post/category/{categoryId}")
	public ResponseEntity<BlogResponse> deleteCategory(@PathVariable int categoryId){
		
		
		
		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Category retrived successfully",categoryService.deleteCategory(categoryId)),
				HttpStatus.OK);
	}
	
	
	
	
	
	
}
