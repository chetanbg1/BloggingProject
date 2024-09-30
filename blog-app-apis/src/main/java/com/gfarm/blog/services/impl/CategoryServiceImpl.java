package com.gfarm.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfarm.blog.entities.Category;
import com.gfarm.blog.exceptions.ResourceNotFoundException;
import com.gfarm.blog.payloads.CategoryDto;
import com.gfarm.blog.repositories.CategoryRepo;
import com.gfarm.blog.services.CategoryService;
@Service
public class CategoryServiceImpl  implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cate = modelMapper.map(categoryDto, Category.class);
		Category newCategory = categoryRepo.save(cate);
		return modelMapper.map(newCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	
		Category category = categoryRepo.findById(categoryId).orElseThrow(() ->new ResourceNotFoundException("category", "id", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryType(categoryDto.getCategoryDescription());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		categoryRepo.save(category);
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryRepo.deleteById(categoryId);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categiry", "id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> allCate = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
		return allCate;
	}
	
	
	
	
}
