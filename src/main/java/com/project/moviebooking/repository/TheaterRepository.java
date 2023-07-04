package com.project.moviebooking.repository;

import org.springframework.stereotype.Repository;

import com.project.moviebooking.model.Theater;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

}
