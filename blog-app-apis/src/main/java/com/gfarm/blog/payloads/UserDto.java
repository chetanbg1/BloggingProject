package com.gfarm.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.gfarm.blog.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	
	
	
}
