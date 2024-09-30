package com.gfarm.blog.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfarm.blog.entities.User;
import com.gfarm.blog.payloads.ApiResponse;
import com.gfarm.blog.payloads.UserDto;
import com.gfarm.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		return new ResponseEntity<UserDto>(userService.createUser(user) , HttpStatus.CREATED);
		//return  ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}
	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers() , HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
		return new ResponseEntity<>(userService.getUserById(userId) , HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user,@PathVariable Integer userId) {
		return new ResponseEntity<>(userService.updateUser(user,userId) , HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", false) , HttpStatus.OK);
	}
	
}
