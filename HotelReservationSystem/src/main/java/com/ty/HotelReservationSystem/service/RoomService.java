package com.ty.HotelReservationSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.HotelReservationSystem.controller.RoomController;
import com.ty.HotelReservationSystem.dto.ResponseStructure;
import com.ty.HotelReservationSystem.dto.Room;
import com.ty.HotelReservationSystem.dto.Users;
import com.ty.HotelReservationSystem.repository.RoomRepository;
import com.ty.HotelReservationSystem.repository.UsersRepository;

@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepository;
	@Autowired
	UsersRepository userRepository;

	public ResponseEntity<ResponseStructure<Room>> save(Room room) {
		Room rooms= roomRepository.save(room);
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		responseStructure.setMessage("Record saved successfully");
		responseStructure.setStatusCode( HttpStatus.CREATED.value());
		responseStructure.setData(rooms);
		return new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Room>>findAllRooms() {
		List<Room> room = roomRepository.findAll();
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		responseStructure.setMessage("Record retreived successfully");
		responseStructure.setStatusCode( HttpStatus.FOUND.value());
		responseStructure.setData(room);
		return new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.FOUND);
	}

	public  ResponseEntity<ResponseStructure<Room>> findRoomById(int id) {
		Optional<Room> optional = roomRepository.findById(id);
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		if (optional.isPresent()) {
			Room room = optional.get();
			responseStructure.setMessage("Record retreived successfully");
			responseStructure.setStatusCode( HttpStatus.FOUND.value());
			responseStructure.setData(room);
		}
		return new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.FOUND);
	}

	public  ResponseEntity<ResponseStructure<Room>> updateRoom(int id, Room rooms) {
		Optional<Room> optional = roomRepository.findById(id);
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		if (optional.isPresent()) {
			Room room = optional.get();
			rooms.setId(room.getId());

			if (rooms.getPrice() == 0.0) {
				rooms.setPrice(room.getPrice());
			}
			if (rooms.getType() == null) {
				rooms.setType(room.getType());
			}
			if (rooms.getBookedBy() == null) {
				rooms.setBookedBy(room.getBookedBy());
			}
			if (rooms.isAvaiable() == false) {
				rooms.setAvaiable(room.isAvaiable());
			}
			roomRepository.save(rooms);
			responseStructure.setMessage("Record updated successfully");
			responseStructure.setStatusCode( HttpStatus.ACCEPTED.value());
			responseStructure.setData(room);			
		}
		return new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Room>> bookRoom(int userId, int roomId) {
		Optional<Room> roomOptional = roomRepository.findById(roomId);
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		if (roomOptional.isPresent()) {
			Room room = roomOptional.get();

			Optional<Users> userOptional = userRepository.findById(userId);
			if (userOptional.isPresent()) {
				Users user = userOptional.get();
				if (room != null && user != null && !room.isAvaiable()) {
					room.setAvaiable(true);
					room.setBookedBy(user);
				}
			}
			roomRepository.save(room);
			responseStructure.setMessage("Record updated successfully");
			responseStructure.setStatusCode( HttpStatus.OK.value());
			responseStructure.setData(room);
		}
			return new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.OK);
		} 
	

	public ResponseEntity<ResponseStructure<Room>> returnRoom(int roomId) {
		Optional<Room> roomOptional = roomRepository.findById(roomId);
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		if (roomOptional.isPresent()) {
			Room room = roomOptional.get();
			if (room != null && room.isAvaiable()) {
				room.setAvaiable(false);
				room.setBookedBy(null);
			}	
		roomRepository.save(room);
		responseStructure.setMessage("Record updated successfully");
		responseStructure.setStatusCode( HttpStatus.OK.value());
		responseStructure.setData(room);
		} 
		return new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.OK);
	}
}
