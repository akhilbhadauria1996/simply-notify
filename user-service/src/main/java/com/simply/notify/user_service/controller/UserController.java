package com.simply.notify.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simply.notify.user_service.entity.User;
import com.simply.notify.user_service.repo.UserRepo;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		user = userRepo.save(user);
		return user;
	}
	
	@GetMapping(value = "/{id}")
	public User getUser(@PathVariable(value = "id") Long id) {
		User user = userRepo.findById(id).get();
		return user;
	}
	
	@GetMapping
	public List<User> getUserList(){
		List<User> userList = userRepo.findAll();
		return userList;
	}

}
