package com.ty.HotelReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.HotelReservationSystem.dto.Users;

public interface UsersRepository  extends JpaRepository<Users, Integer>{

}
