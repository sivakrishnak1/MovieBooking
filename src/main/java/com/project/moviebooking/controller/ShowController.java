package com.project.moviebooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.moviebooking.model.Show;
import com.project.moviebooking.repository.ShowRepository;

@RestController
@RequestMapping("/show")
public class ShowController {

	Logger logger = LoggerFactory.getLogger(ShowController.class);

	@Autowired
	ShowRepository showRepo;

	@GetMapping()
	public List<Show> GetAllShows() {
		return showRepo.findAll();
	}
	
	@GetMapping("{showId}")
	public Show getShowDetails(@PathVariable long showId) {
		return showRepo.findById(showId).isPresent() ? showRepo.findById(showId).get() : null;
	}

}
