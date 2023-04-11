package com.ty.blogmanagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.blogmanagement.response.BlogResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) // when ever we get resource not found exception this method will
														// get called
	public ResponseEntity<BlogResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		String message = ex.getMessage();

		return new ResponseEntity<BlogResponse>(new BlogResponse(true, 404, message,null), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> hendleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		Map<String, String> responce = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			responce.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(responce, HttpStatus.BAD_REQUEST);
	}
}
