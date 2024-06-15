package com.ty.HotelReservationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.HotelReservationSystem.dto.Hotel;
import com.ty.HotelReservationSystem.dto.ResponseStructure;
import com.ty.HotelReservationSystem.dto.Room;
import com.ty.HotelReservationSystem.service.HotelService;

@RestController
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping("/hotel")
	public ResponseEntity<ResponseStructure<Hotel>> save(@RequestBody Hotel hotel) {
		return hotelService.save(hotel);
	}

	@GetMapping("/hotel")
	public ResponseEntity<ResponseStructure<Hotel>> getAllHotels() {
		return hotelService.findAllHotels();
	}

	@GetMapping("/hotel/{id}")
	public ResponseEntity<ResponseStructure<Hotel>>  getHotelOnId(@PathVariable int id) {
		return hotelService.findHotelById(id);
	}

	@PutMapping("hotel/{id}")
	public ResponseEntity<ResponseStructure<Hotel>> updateHotel(@PathVariable int id, @RequestBody Hotel hotel) {
		return hotelService.updateHotel(id, hotel);
	}

}
