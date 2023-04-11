package com.ty.blogmanagement.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentDto {

	private int id;
	@NotNull
	@Size(min=3,max=7,message = "Title length should be >=3 and <=7")
	private String title;
	@NotNull(message = "Parant Id cant be empty")
	private int parentId;
	@NotNull
	private String published;
	@NotNull(message = "Parant Id cant be empty")
	private String content;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime publishedAt;
}
