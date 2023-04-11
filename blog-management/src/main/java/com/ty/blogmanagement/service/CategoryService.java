package com.ty.blogmanagement.service;

import java.util.List;

import com.ty.blogmanagement.dto.CategoryDto;

public interface CategoryService {

	
	public CategoryDto createCategory(int userId,int postId,CategoryDto categoryDto);
	public CategoryDto deleteCategory(int categoryId);
	public CategoryDto getCategory(int categoryId);
	public List<CategoryDto> getAllCategory();
	CategoryDto updateCategory(CategoryDto categoryDto);
	
	
	
}
