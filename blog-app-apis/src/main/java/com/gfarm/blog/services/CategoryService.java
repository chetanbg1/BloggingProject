package com.gfarm.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gfarm.blog.entities.Category;
import com.gfarm.blog.payloads.CategoryDto;

@Service
public interface CategoryService {
	
	
	CategoryDto createCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	void deleteCategory(Integer categoryId);

	// get
	CategoryDto getCategory(Integer categoryId);

	// get All

	List<CategoryDto> getCategories();

}
