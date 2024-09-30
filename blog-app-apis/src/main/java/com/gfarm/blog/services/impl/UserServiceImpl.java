package com.gfarm.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gfarm.blog.config.AppConstants;
import com.gfarm.blog.entities.Role;
import com.gfarm.blog.entities.User;
import com.gfarm.blog.exceptions.ResourceNotFoundException;
import com.gfarm.blog.payloads.UserDto;
import com.gfarm.blog.repositories.RoleRepo;
import com.gfarm.blog.repositories.UserRepo;
import com.gfarm.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		
		User user = dtoToUser(userDto);
		User newUser = userRepo.save(user);
		return userToDto(newUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "UserId", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser = userRepo.save(user);
		return userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "UserId", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=userRepo.findAll();
		List<UserDto> userDtos =users.stream().map(user -> this.userToDto(user)).toList();
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		userRepo.delete(user);
	}

	
	public User dtoToUser(UserDto userDto) {
		//User user = new User();
		
		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDto userToDto(User user) {
	//	UserDto dto = new UserDto();
		UserDto dto = modelMapper.map(user, UserDto.class);
//		dto.setId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setAbout(user.getAbout());
//		dto.setPassword(user.getPassword());
		
		return dto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		//encoded the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//roles
		Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);
		
		User  newUser =userRepo.save(user);
		return modelMapper.map(newUser, UserDto.class);
	}

}
