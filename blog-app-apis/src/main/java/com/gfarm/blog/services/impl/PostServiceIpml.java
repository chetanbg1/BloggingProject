package com.gfarm.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gfarm.blog.entities.Category;
import com.gfarm.blog.entities.Post;
import com.gfarm.blog.entities.User;
import com.gfarm.blog.exceptions.ResourceNotFoundException;
import com.gfarm.blog.payloads.CategoryDto;
import com.gfarm.blog.payloads.PostDto;
import com.gfarm.blog.payloads.PostResponse;
import com.gfarm.blog.repositories.CategoryRepo;
import com.gfarm.blog.repositories.PostRepo;
import com.gfarm.blog.repositories.UserRepo;
import com.gfarm.blog.services.PostService;

import jakarta.persistence.PostRemove;

@Service
public class PostServiceIpml implements PostService {

	@Autowired
	public PostRepo postRepo;

	@Autowired
	public UserRepo userRepo;

	@Autowired
	public CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		
	      User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

	        Category category = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));
	        
	       // CategoryDto cate = modelMapper.map(category, CategoryDto.class);
	        Post post = this.modelMapper.map(postDto, Post.class);
	        post.setImageName("default.png");
	        post.setUser(user);
	        post.setCategory(category);

	        Post newPost = this.postRepo.save(post);

	        return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public List<PostDto> getByUser(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		List<Post> posts = postRepo.findByUser(user);
		
		List<PostDto> postList = posts.stream().map((post) -> modelMapper.map(post , PostDto.class)).toList();
		return postList;

	}

	@Override
	public List<PostDto> getByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
		List<Post> posts =  postRepo.findByCategory(category);
		
		return posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).toList();
	}

	@Override
	public void deletePost(Integer postId) {
	postRepo.deleteById(postId);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber , Integer pageSize, String sortBy) {
		
		
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Post> pagePost = postRepo.findAll(p);
		List<Post> allPosts =pagePost.getContent();
		List<PostDto> postDtos =  allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
		//List<Post> allPost = postRepo.findAll();
		//List<PostDto> all =  allPost.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
	
		PostResponse postResp = new PostResponse();
		postResp.setContent(postDtos);
		postResp.setPageNumber(pagePost.getNumber());
		postResp.setPageSize(pagePost.getSize());
		postResp.setTotalElements(pagePost.getTotalElements());
		postResp.setTotalPages(pagePost.getTotalPages());
		postResp.setLastPage(pagePost.isLast());
		return postResp;
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setImageName(postDto.getImageName());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts =postRepo.findByTitleContaining(keyword);
		//List<Post> posts = postRepo.searchByTitle("%"+keyword+"%");
		 List<PostDto> postDto= posts.stream().map((post)->modelMapper.map(post, PostDto.class)).toList();
		return postDto;
	}

}
