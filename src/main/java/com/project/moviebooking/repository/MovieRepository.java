package com.project.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.moviebooking.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
