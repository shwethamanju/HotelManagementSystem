package com.ty.HotelReservationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.HotelReservationSystem.dto.ResponseStructure;
import com.ty.HotelReservationSystem.dto.Users;
import com.ty.HotelReservationSystem.service.UserService;

@RestController
public class UsersController {

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<Users>> saveUsers(@RequestBody Users users) {
		return userService.save(users);
	}

	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<Users>> getAllUsers() {
		return userService.getAllUser();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<Users>> findUserById(@PathVariable int id) {
		return userService.findUserById(id);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<Users>>  deleteUsers(@PathVariable int id) {
		return userService.deleteusers(id);

	}

	@PutMapping("user/{id}")
	public ResponseEntity<ResponseStructure<Users>> updateUsers(@PathVariable int id, @RequestBody Users users) {
		return userService.updateUsers(id, users);
	}
}
