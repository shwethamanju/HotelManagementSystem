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

import com.ty.HotelReservationSystem.dto.ResponseStructure;
import com.ty.HotelReservationSystem.dto.Room;
import com.ty.HotelReservationSystem.dto.Users;
import com.ty.HotelReservationSystem.service.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	RoomService roomService;

	@PostMapping("/room")
	 public ResponseEntity<ResponseStructure<Room>> saveRoom(@RequestBody Room room) {
		return roomService.save(room);
	}
	
	@GetMapping("/room")
	public ResponseEntity<ResponseStructure<Room>>  getAllRooms(){
		return roomService.findAllRooms();
	}
	
	@GetMapping("/room/{id}")
	public ResponseEntity<ResponseStructure<Room>> getRoombasedOnId(@PathVariable int id) {
		return roomService.findRoomById(id);
	}
	
	@PutMapping("/room/{id}")
	public ResponseEntity<ResponseStructure<Room>> updateRoom(@PathVariable int id,@RequestBody Room room) {
		return roomService.updateRoom(id, room);		
	}
	
	@PostMapping("/room/{userId}/{roomId}")
	public ResponseEntity<ResponseStructure<Room>>  bookRoomifAvailable(@PathVariable int userId,@PathVariable int roomId) {
		return roomService.bookRoom(userId, roomId);		
	}
	
	@PostMapping("/room/{roomId}")
	public ResponseEntity<ResponseStructure<Room>> returnRoom(@PathVariable int roomId) {
		return roomService.returnRoom(roomId);
	}
}
