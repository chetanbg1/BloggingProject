package com.gfarm.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gfarm.blog.entities.Post;
import com.gfarm.blog.payloads.PostDto;
import com.gfarm.blog.payloads.PostResponse;

@Service
public interface PostService {
	
	public PostDto createPost(PostDto post , Integer userId , Integer categoryId);
	
	public PostDto updatePost(PostDto post , Integer postId);
	
	public List<PostDto> getByUser(Integer userId);
	
	public  List<PostDto> getByCategory(Integer categoryId);
	
	void deletePost(Integer postId);
	
	public PostResponse getAllPost(Integer pageNumber , Integer pageSize, String sortBy);
	
	public PostDto getPost(Integer postId);
	
	public List<PostDto> searchPost(String keyword);
} 
