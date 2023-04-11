package com.ty.blogmanagement.serviceimpli;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.blogmanagement.dto.CategoryDto;
import com.ty.blogmanagement.entity.Category;
import com.ty.blogmanagement.entity.Post;
import com.ty.blogmanagement.entity.User;
import com.ty.blogmanagement.exception.ResourceNotFoundException;
import com.ty.blogmanagement.repository.CategoryRepo;
import com.ty.blogmanagement.repository.PostRepo;
import com.ty.blogmanagement.repository.UserRepo;
import com.ty.blogmanagement.service.CategoryService;

@Service
public class CategoryServiceImpli implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(int userId, int postId, CategoryDto categoryDto) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Post post=postRepo.findById(postId).orElseThrow(()->new  ResourceNotFoundException("Post","Post Id", postId));
	
		Category category=modelMapper.map(categoryDto, Category.class);
		
		category.setPost(post);
		
		
		Category category2=categoryRepo.save(category);
		
		return modelMapper.map(category2, CategoryDto.class);
		
		
		

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {

		Category category = categoryRepo.findById(categoryDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryDto.getId()));
		category.setId(categoryDto.getId());
		category.setMetaTitle(categoryDto.getMetaTitle());
		category.setParentId(categoryDto.getParentId());
		category.setTitle(categoryDto.getTitle());

		return modelMapper.map(category, CategoryDto.class);

	}

	@Override
	public CategoryDto deleteCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		categoryRepo.deleteById(categoryId);
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		;
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();

		return categories.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
	}

}
