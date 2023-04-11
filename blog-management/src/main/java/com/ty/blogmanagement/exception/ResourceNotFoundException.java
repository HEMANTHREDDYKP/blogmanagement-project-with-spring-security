package com.ty.blogmanagement.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	private String resource;
	private String fieldName;
	private int fieldValue;
	public ResourceNotFoundException(String resource, String fieldName, int fieldValue) {
		super(String.format("%s with %s = %s not found", resource,fieldName,fieldValue));
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
}
