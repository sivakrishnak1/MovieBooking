package com.project.moviebooking.controller;

import java.util.Collections;
import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.moviebooking.model.Movie;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.repository.MovieRepository;
import com.project.moviebooking.repository.ShowRepository;


@RestController
@RequestMapping("/movie")
public class MovieController {

	Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	MovieRepository movieRepo;

	@Autowired
	ShowRepository showRepo;

	// GET /movie/{movieId}/shows/{showId}
	@GetMapping("/{movieId}/shows/{showId}")
	public List<Show> getMovieShows(@PathVariable long movieId, @PathVariable long showId) {

		if (movieRepo.findById(movieId).isPresent()) {
			Movie movie = movieRepo.findById(movieId).get();
			List<Show> findAllByMovie = showRepo.findAllByMovie(movie);
			
			logger.info(">>>showId: {}", showId);
			if (showId == 0)
				return findAllByMovie;
			for (Show show : findAllByMovie) {
				if (show.getId() == showId) {
					return List.of(show);
				}
			}
		}
		
		return Collections.emptyList();

	}

	// Add a movie
	@PostMapping()
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}

	@GetMapping("{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable long id) {
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
