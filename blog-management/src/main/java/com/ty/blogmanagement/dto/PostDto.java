package com.ty.blogmanagement.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

	private int id;
	@NotNull(message = "Author Id cant be empty")
	private int authorId;
	@NotNull
	@Size(min = 4,max = 10,message = "Title length should be>=4 and <=10")
	private String title;
	@NotNull(message = "Meta Title can't be empty")
	private String metaTitle;

	
	private UserDto user;
	
	private List<PostCommentDto> postComments;
}
