package com.gfarm.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.gfarm.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private CategoryDto categoty;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
