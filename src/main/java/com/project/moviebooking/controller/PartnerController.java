package com.project.moviebooking.controller;

import java.util.Collections;
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
import com.project.moviebooking.model.Theater;
import com.project.moviebooking.repository.MovieRepository;
import com.project.moviebooking.repository.ShowRepository;
import com.project.moviebooking.repository.TheaterRepository;
import com.project.moviebooking.service.PartnerService;

@RestController
@RequestMapping("/partner")
public class PartnerController {

	@Autowired
	PartnerService partnerService;

	@Autowired
	TheaterRepository theaterRepository;
	@Autowired
	ShowRepository showRepo;
	@Autowired
	MovieRepository movieRepo;

	Logger logger = LoggerFactory.getLogger(PartnerController.class);

	@GetMapping("/theater")
	public List<Theater> getTheaters() {
		return theaterRepository.findAll();
	}

	// Add Theater
	@PostMapping("/theater")
	public Theater createTheater(@RequestBody Theater theater) {
		return theaterRepository.save(theater);
	}

	/* ++++++++Theater Onboarding++++++++++++++++++++++++++ */

	// Get details of a Theater
	@GetMapping("/theater/{theaterId}")
	public ResponseEntity<Theater> getDetails(@PathVariable long theaterId) {

		Optional<Theater> findById = theaterRepository.findById(theaterId);
		if (findById.isPresent()) {
			return new ResponseEntity<Theater>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Theater>((Theater) null, HttpStatus.NOT_FOUND);
		}

	}

	// Update Theater
	@PutMapping("theater/{theaterId}")
	public ResponseEntity<Theater> updateDetails(@RequestBody Theater theater, long theaterId) {

		Optional<Theater> findById = theaterRepository.findById(theaterId);
		if (findById.isPresent()) {
			return new ResponseEntity<Theater>(theaterRepository.save(theater), HttpStatus.OK);
		} else {
			return new ResponseEntity<Theater>((Theater) null, HttpStatus.NOT_FOUND);
		}
	}

	// Delete a Theater
	@DeleteMapping("theater/{theaterId}")
	public void delete(long theaterId) {
		theaterRepository.deleteById(null);
	}

	/* ++++++++++++ Theater partner to add/update/delete show ++++++++++++++++++ */

	// Theater to add Show
	@PostMapping("/theater/{theaterId}/show")
	public ResponseEntity<Show> createShow(@RequestBody Show show, @PathVariable long theaterId) {
		return new ResponseEntity<Show>(showRepo.save(show), HttpStatus.OK);
	}

	// Theater to update Show
	@PutMapping("/theater/{theaterId}/show/{showId}")
	public ResponseEntity<Show> updateShow(@RequestBody Show show, @PathVariable long theaterId,
			@PathVariable long showId) {

		// is showid valid??
		if (showRepo.findById(showId).isEmpty()) {
			logger.error("Show {} is not valid", showId);
			return new ResponseEntity<Show>((Show) null, HttpStatus.BAD_REQUEST);
		} else {
			// check if the show belongs the theaterid
			if (showRepo.findById(showId).get().getTheater().getId() != theaterId) {
				logger.error("show {} does not belong to theater {}", showId, theaterId);
				return new ResponseEntity<Show>((Show) null, HttpStatus.BAD_REQUEST);
			}
		}

		show.setId(showId);
		Optional<Theater> findById = theaterRepository.findById(theaterId);
		if (findById.isEmpty()) {
			logger.info("Invalid theater {}", theaterId);
			return new ResponseEntity<Show>((Show) null, HttpStatus.BAD_REQUEST);
		}
		show.setTheater(findById.get());
		return new ResponseEntity<Show>(showRepo.save(show), HttpStatus.OK);

	}

	// Theater to delete a show/delete a show for a theater
	@DeleteMapping("/theater/{theaterId}/show/{showId}")
	public ResponseEntity<Show> deleteShow(@PathVariable long showId, @PathVariable long theaterId) {
		Optional<Show> findById = showRepo.findById(showId);

		// is showid valid??
		if (showRepo.findById(showId).isEmpty()) {
			logger.error("Show {} is not valid", showId);
			return new ResponseEntity<Show>((Show) null, HttpStatus.BAD_REQUEST);
		} else {
			// check if the show belongs the theaterid
			if (showRepo.findById(showId).get().getTheater().getId() != theaterId) {
				logger.error("show {} does not belong to theater {}", showId, theaterId);
				return new ResponseEntity<Show>((Show) null, HttpStatus.BAD_REQUEST);
			}
		}

		showRepo.deleteById(showId);
		return new ResponseEntity<Show>(findById.get(), HttpStatus.OK);
	}

	// list all shows for a theater
	@GetMapping("/theater/{theaterId}/show")
	public List<Show> GetShowsForTheater(@PathVariable long theaterId) {

		Optional<Theater> findById = theaterRepository.findById(theaterId);
		if (findById.isPresent()) {
			return showRepo.findAllByTheater(findById.get());
		}
		return Collections.emptyList();
	}

}
