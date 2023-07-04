package com.project.moviebooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.moviebooking.model.Movie;
import com.project.moviebooking.repository.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieRepository movieRepo;

	// Add a movie
	@PostMapping()
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}

	@GetMapping("{id}")
	public ResponseEntity<Movie> getSample(@PathVariable long id) {
		Optional<Movie> findById = movieRepo.findById(id);
		if (findById.isPresent()) {
			return new ResponseEntity<Movie>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Movie>((Movie) null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping()
	public List<Movie> getAllMovies() {
		return movieRepo.findAll();
	}

}
