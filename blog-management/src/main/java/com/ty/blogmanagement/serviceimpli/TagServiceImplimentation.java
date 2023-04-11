package com.ty.blogmanagement.serviceimpli;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.blogmanagement.dto.TagDto;
import com.ty.blogmanagement.entity.Post;
import com.ty.blogmanagement.entity.Tag;
import com.ty.blogmanagement.entity.User;
import com.ty.blogmanagement.exception.ResourceNotFoundException;
import com.ty.blogmanagement.repository.PostRepo;
import com.ty.blogmanagement.repository.TagRepo;
import com.ty.blogmanagement.repository.UserRepo;
import com.ty.blogmanagement.service.TagService;
@Service
public class TagServiceImplimentation implements TagService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TagRepo tagRepo;

	@Override
	public TagDto createTag(int userId, int postId, TagDto tagDto) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		Tag tag = modelMapper.map(tagDto, Tag.class);
		tag.setPost(post);

		Tag savedTag = tagRepo.save(tag);

		return modelMapper.map(savedTag, TagDto.class);
	}

	@Override
	public TagDto updateTag(int userId, int postId, TagDto tagDto) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		
		Tag tag=tagRepo.findById(tagDto.getId()).orElseThrow(()->new ResourceNotFoundException("Tag","Tag Id", tagDto.getId()));
	
		tag.setContent(tagDto.getContent());
		tag.setMetaTitle(tagDto.getTitle());
		tag.setTitle(tagDto.getTitle());
		
		Tag savedTag=tagRepo.save(tag);
		return modelMapper.map(savedTag, TagDto.class);
	}

	@Override
	public TagDto deleteTag(int tagId) {

		Tag tag = tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "Tag Id", tagId));

		tagRepo.delete(tag);
		
		return modelMapper.map(tag, TagDto.class);
	}

	@Override
	public TagDto getTag(int tagId) {
		Tag tag = tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "TAg Id", tagId));

		return modelMapper.map(tag, TagDto.class);
	}

	@Override
	public List<TagDto> getTag() {
		List<Tag> tags = tagRepo.findAll();

		return tags.stream().map(tag -> modelMapper.map(tag, TagDto.class)).collect(Collectors.toList());

	}

}
