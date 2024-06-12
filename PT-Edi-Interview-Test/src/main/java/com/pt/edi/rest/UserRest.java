package com.pt.edi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pt.edi.dto.DtoResponse;
import com.pt.edi.entity.UserEntity;
import com.pt.edi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public DtoResponse<UserEntity> getDataUser(@PathVariable("id") String id) {
		return userService.getDataUser(id);
	}

	@PostMapping
	public DtoResponse<UserEntity> setDataUser(@RequestBody UserEntity param) {
		return userService.setDataUser(param);
	}

	@DeleteMapping("/{id}")
	public DtoResponse<UserEntity> deleteDataUser(@PathVariable("id") String id) {
		return userService.delDataUser(id);
	}
}
