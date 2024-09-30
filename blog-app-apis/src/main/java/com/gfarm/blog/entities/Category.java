package com.gfarm.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	//@NotEmpty
	private String categoryType;
	//@NotEmpty
	private String categoryTitle;
	//@NotEmpty
	private String CategoryDescription;
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
	List<Post> posts;
	
	
	
	
}
