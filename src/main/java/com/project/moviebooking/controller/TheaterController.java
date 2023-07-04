package com.project.moviebooking.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.moviebooking.model.Show;
import com.project.moviebooking.repository.MovieRepository;
import com.project.moviebooking.repository.ShowRepository;
import com.project.moviebooking.repository.TheaterRepository;

@RestController
@RequestMapping("/theater")
public class TheaterController {

	Logger logger = LoggerFactory.getLogger(TheaterController.class);

	@Autowired
	ShowRepository showRepo;
	@Autowired
	TheaterRepository theaterRepo;
	@Autowired
	MovieRepository movieRepo;

	// list all shows for a theater
	@GetMapping("{theaterId}/show")
	public List<Show> GetShowsForTheater(@PathVariable long theaterId) {
		return showRepo.findAllByTheater(theaterRepo.findById(theaterId).get());
	}

}
