package com.ty.HotelReservationSystem.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "roomId")
	@SequenceGenerator(name = "roomId", initialValue = 201, allocationSize = 1)
	private int id;
	private String type;
	private double price;
	private boolean avaiable;
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users bookedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvaiable() {
		return avaiable;
	}

	public void setAvaiable(boolean avaiable) {
		this.avaiable = avaiable;
	}

	public Users getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(Users bookedBy) {
		this.bookedBy = bookedBy;
	}

}
