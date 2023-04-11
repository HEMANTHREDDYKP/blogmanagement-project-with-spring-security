package com.ty.blogmanagement.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
	
	private int id;
	private int parentId;
	@NotEmpty
	@Size(min = 3,max = 10,message = "title cant be empty ")
	private String title;
	private String metaTitle;
	private String content;
	//remove this if u get jakson.detained error
	private List<PostDto> posts;
	
}
