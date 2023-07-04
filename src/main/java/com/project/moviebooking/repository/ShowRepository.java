package com.project.moviebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.moviebooking.model.Movie;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.model.Theater;

public interface ShowRepository extends JpaRepository<Show, Long>{

	List<Show> findAllByTheater(Theater theater);
	
	List<Show> findAllByMovie(Movie movie);
}
