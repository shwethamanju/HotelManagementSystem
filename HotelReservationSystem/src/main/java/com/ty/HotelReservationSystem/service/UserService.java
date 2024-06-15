package com.ty.HotelReservationSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.HotelReservationSystem.dto.ResponseStructure;
import com.ty.HotelReservationSystem.dto.Users;
import com.ty.HotelReservationSystem.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	UsersRepository userRepository;

	public ResponseEntity<ResponseStructure<Users>> save(Users users) {
		Users user= userRepository.save(users);
		ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
		responseStructure.setMessage("Record saved successfully");
		responseStructure.setStatusCode( HttpStatus.CREATED.value());
		responseStructure.setData(user);
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
		}

	public ResponseEntity<ResponseStructure<Users>> getAllUser() {
		List<Users> user= userRepository.findAll();
		ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();	
		responseStructure.setMessage("Record retrieved successfully");
		responseStructure.setStatusCode( HttpStatus.FOUND.value());		
		responseStructure.setData(user);
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Users>> findUserById(int id){
		Optional<Users> optional = userRepository.findById(id);
		ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
		if (optional.isPresent()) {
			Users user = optional.get();			
			responseStructure.setMessage("Record retrieved successfully");
			responseStructure.setStatusCode( HttpStatus.FOUND.value());		
			responseStructure.setData(user);		
		}
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Users>> deleteusers(int id) {
		Optional<Users> optional = userRepository.findById(id);
		ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
		if (optional.isPresent()) {
			Users user = optional.get();
			userRepository.delete(user);
			responseStructure.setMessage("Record deleted successfully");
			responseStructure.setStatusCode( HttpStatus.OK.value());		
			responseStructure.setData(user);						
		}
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.OK);	
		}
	

	public ResponseEntity<ResponseStructure<Users>> updateUsers(int id, Users users) {
		Optional<Users> optional = userRepository.findById(id);
		ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
		if (optional.isPresent()) {
			Users user = optional.get();
			users.setId(user.getId());
			// if the user is trying to update only username in the request body,previous password has to be retained
			if (users.getPassword() == null) {
				users.setPassword(user.getPassword());
			}
			// if the user is trying to update only password in the in the request body,previous username has to be retained
			if (users.getUserName() == null) {
				users.setUserName(user.getUserName());
			}
			userRepository.save(users);
			responseStructure.setMessage("Record updated successfully");
			responseStructure.setStatusCode( HttpStatus.ACCEPTED.value());		
			responseStructure.setData(users);			
		} 
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.ACCEPTED);
		}
	}
