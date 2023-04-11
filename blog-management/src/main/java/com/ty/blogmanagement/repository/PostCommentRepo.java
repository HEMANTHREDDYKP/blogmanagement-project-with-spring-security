package com.ty.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.blogmanagement.entity.PostComment;

public interface PostCommentRepo extends JpaRepository<PostComment, Integer> {

}
