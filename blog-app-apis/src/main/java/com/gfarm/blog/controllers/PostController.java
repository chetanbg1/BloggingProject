package com.gfarm.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gfarm.blog.config.AppConstants;
import com.gfarm.blog.entities.Post;
import com.gfarm.blog.payloads.ApiResponse;
import com.gfarm.blog.payloads.PostDto;
import com.gfarm.blog.payloads.PostResponse;
import com.gfarm.blog.services.FileService;
import com.gfarm.blog.services.PostService;

@RestController
@RequestMapping("/blog/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto newPost = postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(newPost, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)Integer pageSize,
			@RequestParam(value = "sortBy" , defaultValue = AppConstants.SORT_BY, required = false) String sortBy){
		return new ResponseEntity<>(postService.getAllPost(pageSize, pageSize, sortBy), HttpStatus.OK);
	}

	@GetMapping("/getByUser/{userId}")
	public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<PostDto>>(postService.getByUser(userId), HttpStatus.OK);
	}

	@GetMapping("/getByCategory/{categoryId}")
	public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId) {
		return new ResponseEntity<List<PostDto>>(postService.getByCategory(categoryId), HttpStatus.OK);
	}

	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
		return new ResponseEntity<PostDto>(postService.getPost(postId), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted", false), HttpStatus.OK);
	}
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
		
		return new ResponseEntity<List<PostDto>>(postService.searchPost(keywords),HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@PathVariable Integer postId,
			@RequestParam ("image") MultipartFile image) throws IOException{
		
		 PostDto postDto  =postService.getPost(postId);
		String fileName   = this.fileService.uploadImage(path, image);
		
		
		postDto.setImageName(fileName);
		PostDto updatedPost =this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatedPost , HttpStatus.OK);
	}
}
