package com.project.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.project.moviebooking.model.TheaterSeats;

public interface TheaterSeatsRepository extends CrudRepository<TheaterSeats, Long>{

	 
}
