package com.ty.blogmanagement.controller;

import java.util.List;

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

import com.ty.blogmanagement.dto.TagDto;
import com.ty.blogmanagement.response.BlogResponse;
import com.ty.blogmanagement.service.TagService;

@RestController
public class TagController {

	@Autowired
	private TagService tagService;

	@PostMapping("user/{userId}/post/{postId}/tag")
	public ResponseEntity<BlogResponse> createTag(@PathVariable int userId,@PathVariable int postId, @RequestBody TagDto tagDto) {

		TagDto savedTag = tagService.createTag(userId, postId, tagDto);

		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 201, "Tag saved successfully", savedTag),
				HttpStatus.CREATED);

	}

	@PutMapping("user/{userId}/post/{postId}/tag")
	public ResponseEntity<BlogResponse> updateTag(@PathVariable int userId,@PathVariable int postId,@RequestBody TagDto tagDto) {

		TagDto updatedTag = tagService.updateTag(userId, postId, tagDto);

		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Tag updated successfully", updatedTag),
				HttpStatus.OK);

	}

	@GetMapping("user/post/tag/{tagId}")
	public ResponseEntity<BlogResponse> getTag(@PathVariable int tagId) {

		TagDto tag = tagService.getTag(tagId);

		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 302, "Tag retrived successfully", tag),
				HttpStatus.FOUND);

	}

	@DeleteMapping("user/post/tag/{tagId}")
	public ResponseEntity<BlogResponse> deleteTag(@PathVariable int tagId) {

		TagDto deletedTag =tagService.deleteTag(tagId);

		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 200, "Tag deleted successfully",deletedTag),
				HttpStatus.OK);

	}
	@GetMapping("user/post/tag")
	public ResponseEntity<BlogResponse> getTag() {

		List<TagDto> tag = tagService.getTag();

		return new ResponseEntity<BlogResponse>(new BlogResponse(false, 302, "Tags retrived successfully", tag),
				HttpStatus.FOUND);

	}
	

}
