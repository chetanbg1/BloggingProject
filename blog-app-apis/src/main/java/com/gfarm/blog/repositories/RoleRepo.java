package com.gfarm.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfarm.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
