package com.ty.blogmanagement.response;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponse {
	private boolean error;
	private int statusCode;
	private String message;
	private Object blogObject;

}
