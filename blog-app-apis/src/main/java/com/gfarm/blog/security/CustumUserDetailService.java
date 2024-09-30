package com.gfarm.blog.security;

import java.lang.module.ResolutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gfarm.blog.entities.User;
import com.gfarm.blog.exceptions.ResourceNotFoundException;
import com.gfarm.blog.repositories.UserRepo;
@Service
public class CustumUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user", "eamil : " +username, 0));
		
		return user;
	}

}
