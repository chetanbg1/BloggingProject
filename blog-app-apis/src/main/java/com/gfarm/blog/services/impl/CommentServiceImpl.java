package com.gfarm.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfarm.blog.entities.Comment;
import com.gfarm.blog.entities.Post;
import com.gfarm.blog.entities.User;
import com.gfarm.blog.exceptions.ResourceNotFoundException;
import com.gfarm.blog.payloads.CommentDto;
import com.gfarm.blog.repositories.CommentRepo;
import com.gfarm.blog.repositories.PostRepo;
import com.gfarm.blog.repositories.UserRepo;
import com.gfarm.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Comment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getCommnet(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDto createComment(CommentDto comment, Integer postId , Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		Comment com = modelMapper.map(comment, Comment.class);
		com.setPost(post);
		com.setUser(user);
		Comment newComment = commentRepo.save(com);
		return modelMapper.map(newComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		commentRepo.deleteById(commentId);
		
	}

	

}
