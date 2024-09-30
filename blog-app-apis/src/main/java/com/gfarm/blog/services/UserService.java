package com.gfarm.blog.services;

import java.util.List;

import com.gfarm.blog.entities.User;
import com.gfarm.blog.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto user);
	public UserDto updateUser(UserDto user , Integer userId);
	public UserDto getUserById(Integer userId);
	public List<UserDto> getAllUsers();
	public void deleteUser(Integer userId);
	
	UserDto registerNewUser(UserDto user);

}
