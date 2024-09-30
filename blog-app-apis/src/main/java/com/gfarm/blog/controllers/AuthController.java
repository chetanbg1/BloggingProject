package com.gfarm.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfarm.blog.payloads.JWTAuthRequest;
import com.gfarm.blog.payloads.JWTAuthResponse;
import com.gfarm.blog.payloads.UserDto;
import com.gfarm.blog.security.JWTTokenHelper;
import com.gfarm.blog.services.UserService;

@RestController
@RequestMapping("/blog/v1/auth/")
public class AuthController {
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request){
		
		this.authenticate(request.getEmail() , request.getPassword());
		UserDetails userDetails =this.userDetailsService.loadUserByUsername(request.getEmail());
		
		 String token =this.jwtTokenHelper.generateToken(userDetails);
		 
		 JWTAuthResponse response = new JWTAuthResponse();
		 response.setToken(token);
		 
		 return new ResponseEntity<JWTAuthResponse>(response , HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		this.authenticationManager.authenticate(authenticationToken);	
	}
	
	//register new user
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		
		return new ResponseEntity<UserDto>(userService.registerNewUser(userDto), HttpStatus.CREATED);
	}
	
	
}
