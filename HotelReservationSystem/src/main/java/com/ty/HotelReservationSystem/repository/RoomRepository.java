package com.ty.HotelReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.HotelReservationSystem.dto.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{

}
