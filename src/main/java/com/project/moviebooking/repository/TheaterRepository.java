package com.project.moviebooking.repository;

import org.springframework.stereotype.Repository;

import com.project.moviebooking.model.Theater;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

	List<Theater> findByCity(String city);
}
