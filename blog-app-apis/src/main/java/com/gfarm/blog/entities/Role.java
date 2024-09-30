package com.gfarm.blog.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {

	@jakarta.persistence.Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private String name;
}
