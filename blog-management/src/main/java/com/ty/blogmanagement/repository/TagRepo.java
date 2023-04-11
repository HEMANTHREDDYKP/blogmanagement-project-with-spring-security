package com.ty.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.blogmanagement.entity.Tag;

public interface TagRepo  extends JpaRepository<Tag, Integer>{

}
