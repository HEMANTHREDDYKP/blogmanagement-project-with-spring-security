package com.ty.blogmanagement.service;

import java.util.List;

import com.ty.blogmanagement.dto.TagDto;
import com.ty.blogmanagement.entity.Tag;

public interface TagService {

	
	
	public TagDto createTag(int userId,int postId,TagDto tagDto);
	public TagDto updateTag(int userId, int postId, TagDto tagDto);
	public TagDto deleteTag(int tagId);
	public TagDto getTag(int tagId);
	public List<TagDto> getTag();
	
	
	
	
	
}
