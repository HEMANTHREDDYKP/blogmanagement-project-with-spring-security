package com.ty.blogmanagement.serviceimpli;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.blogmanagement.dto.PostDto;
import com.ty.blogmanagement.entity.Post;
import com.ty.blogmanagement.entity.User;
import com.ty.blogmanagement.exception.ResourceNotFoundException;
import com.ty.blogmanagement.repository.PostRepo;
import com.ty.blogmanagement.repository.UserRepo;
import com.ty.blogmanagement.service.PostService;

@Service
public class PostServiceImpli implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public PostDto createPost(int userId, PostDto postDto) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Post post = modelMapper.map(postDto, Post.class);
		post.setUser(user);
		Post savedPost = postRepo.save(post);

		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto getSpecificPostOfUser(int userId, int postId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return modelMapper.map(post, PostDto.class);

	}

	@Override
	public PostDto updatePost(int postId, PostDto postDto) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setAuthorId(postDto.getAuthorId());
		post.setMetaTitle(postDto.getMetaTitle());

		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto deletePost(int userId, int postId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		postRepo.delete(post);

		return modelMapper.map(post, PostDto.class);

	}

	@Override
	public PostDto getPost(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		return modelMapper.map(post, PostDto.class);
	}

}
