package com.simply.notify.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simply.notify.user_service.entity.User;

@RestController(value = "/user")
public class UserController {
	
	@GetMapping(value = "/{id}")
	public User getUser(@RequestParam(value = "id") Long id) {
		User user = new User();
		return user;
	}

}
