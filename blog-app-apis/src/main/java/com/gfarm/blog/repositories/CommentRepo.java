package com.gfarm.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfarm.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
