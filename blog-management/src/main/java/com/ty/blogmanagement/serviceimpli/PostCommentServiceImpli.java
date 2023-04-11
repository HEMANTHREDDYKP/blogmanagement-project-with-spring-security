package com.ty.blogmanagement.serviceimpli;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.blogmanagement.dto.PostCommentDto;
import com.ty.blogmanagement.entity.Post;
import com.ty.blogmanagement.entity.PostComment;
import com.ty.blogmanagement.entity.User;
import com.ty.blogmanagement.exception.ResourceNotFoundException;
import com.ty.blogmanagement.repository.PostCommentRepo;
import com.ty.blogmanagement.repository.PostRepo;
import com.ty.blogmanagement.repository.UserRepo;
import com.ty.blogmanagement.service.PostCommentService;

@Service
public class PostCommentServiceImpli implements PostCommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostCommentRepo postCommentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostCommentDto createPostComment(int userId, int postId, PostCommentDto postCommentDto) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		PostComment postComment=modelMapper.map(postCommentDto, PostComment.class);
		postComment.setPost(post);
		
		return 	modelMapper.map(postCommentRepo.save(postComment),PostCommentDto.class);
	

	}

	@Override
	public PostCommentDto updatePostComment(int userId, int postId, PostCommentDto postCommentDto) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		PostComment postComment=modelMapper.map(postCommentDto, PostComment.class);
	
				
				postComment.setContent(postCommentDto.getContent());
				postComment.setParentId(postCommentDto.getParentId());
				postComment.setTitle(postCommentDto.getTitle());
				postComment.setPublishedAt(LocalDateTime.now());
				postComment.setCreatedAt(LocalDateTime.now());

			PostComment	savedPostComment = postCommentRepo.save(postComment);

		
		return modelMapper.map(savedPostComment, PostCommentDto.class);
	}

	@Override
	public PostCommentDto deletePostComment(int userId, int postId,int postCommentId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
	
      PostComment postComment=  post.getPostComments().stream().filter(postCom->postCom.getId()==postCommentId).collect(Collectors.toList()).get(0);
	
		if(postComment!=null) {
			 postCommentRepo.delete(postComment);
			 return modelMapper.map(postComment, PostCommentDto.class);
		}
		else {
			return null;
		}
	}

	@Override
	public PostCommentDto getPostComment(int postCommentId) {

		PostComment postComment = postCommentRepo.findById(postCommentId)
				.orElseThrow(() -> new ResourceNotFoundException("PostComment", "Id", postCommentId));

		return modelMapper.map(postComment, PostCommentDto.class);
	}

	@Override
	public List<PostCommentDto> getAllPostComment() {

		List<PostComment> postComments = postCommentRepo.findAll();
		return postComments.stream().map(postComment -> modelMapper.map(postComment, PostCommentDto.class))
				.collect(Collectors.toList());

	}

}
