package com.ty.HotelReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.HotelReservationSystem.dto.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

}
