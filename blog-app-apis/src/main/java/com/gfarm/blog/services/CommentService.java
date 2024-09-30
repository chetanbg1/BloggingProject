package com.gfarm.blog.services;

import java.util.List;

import com.gfarm.blog.entities.Comment;
import com.gfarm.blog.payloads.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto comment , Integer postId, Integer userId);
	
	public List<Comment> getAll();
	
	public Comment getCommnet(int id);
	
	public Comment updateComment(Comment comment);
	
	public void deleteComment(Integer commentId);
}
