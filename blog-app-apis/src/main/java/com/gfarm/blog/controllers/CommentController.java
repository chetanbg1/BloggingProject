package com.gfarm.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfarm.blog.payloads.ApiResponse;
import com.gfarm.blog.payloads.CommentDto;
import com.gfarm.blog.services.CommentService;

@RestController
@RequestMapping("/blog/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/{postId}/{userId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,
			 @PathVariable Integer userId){
		return new ResponseEntity<CommentDto>(commentService.createComment(commentDto, postId , userId) , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted", true), HttpStatus.OK);
	}
}
