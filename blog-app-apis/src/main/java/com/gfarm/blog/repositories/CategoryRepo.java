package com.gfarm.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfarm.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
