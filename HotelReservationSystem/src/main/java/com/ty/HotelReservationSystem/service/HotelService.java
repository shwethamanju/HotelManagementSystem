package com.ty.HotelReservationSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ty.HotelReservationSystem.dto.Hotel;
import com.ty.HotelReservationSystem.dto.ResponseStructure;
import com.ty.HotelReservationSystem.dto.Room;
import com.ty.HotelReservationSystem.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	HotelRepository hotelRepository;

	public ResponseEntity<ResponseStructure<Hotel>> save(Hotel hotel) {
		Hotel  hotels= hotelRepository.save(hotel);
		ResponseStructure<Hotel> responseStructure = new ResponseStructure<Hotel>();
		responseStructure.setMessage("Record saved successfully");
		responseStructure.setStatusCode( HttpStatus.CREATED.value());
		responseStructure.setData(hotels);
		return new ResponseEntity<ResponseStructure<Hotel>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Hotel>>  findAllHotels() {
		List<Hotel> hotel= hotelRepository.findAll();
		ResponseStructure<Hotel> responseStructure = new ResponseStructure<Hotel>();
		responseStructure.setMessage("Record saved successfully");
		responseStructure.setStatusCode( HttpStatus.FOUND.value());
		responseStructure.setData(hotel);
		return new ResponseEntity<ResponseStructure<Hotel>>(responseStructure,HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Hotel>> findHotelById(int id) {
		Optional<Hotel> optional = hotelRepository.findById(id);
		ResponseStructure<Hotel> responseStructure = new ResponseStructure<Hotel>();
		if (optional.isPresent()) {
			Hotel hotel = optional.get();
			responseStructure.setMessage("Record saved successfully");
			responseStructure.setStatusCode( HttpStatus.FOUND.value());
			responseStructure.setData(hotel);
		}
			return new ResponseEntity<ResponseStructure<Hotel>>(responseStructure,HttpStatus.FOUND);
		}

	public ResponseEntity<ResponseStructure<Hotel>> updateHotel(int id, Hotel hotel) {
		Optional<Hotel> optional = hotelRepository.findById(id);
		ResponseStructure<Hotel> responseStructure = new ResponseStructure<Hotel>();
		if (optional.isPresent()) {
			Hotel hotels = optional.get();
			hotel.setId(hotels.getId());
			// if the user is not updating name in the request body,previous name has to be retained
			if (hotel.getName() == null) {
				hotel.setName(hotels.getName());
			}
            
			// if the user is not updating address in the request body,previous address has to be retained
			
			 if (hotel.getAddress() == null) {
				hotel.setAddress(hotels.getAddress());				
			}
             
			// if the user is not updating city in the request body,previous city has to be retained
			 if (hotel.getCity() == null) {
				hotel.setCity(hotels.getCity());
			}

			// if the user is not updating country in the request body,previous country has to be retained
			 if (hotel.getCountry() == null) {
				hotel.setCountry(hotels.getCountry());
			}			 
			hotelRepository.save(hotel);
			responseStructure.setMessage("Record saved successfully");
			responseStructure.setStatusCode( HttpStatus.ACCEPTED.value());
			responseStructure.setData(hotel);
		} 
		return new ResponseEntity<ResponseStructure<Hotel>>(responseStructure,HttpStatus.ACCEPTED);
	}

}
