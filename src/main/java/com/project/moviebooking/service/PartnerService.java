package com.project.moviebooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.moviebooking.model.Theater;
import com.project.moviebooking.repository.TheaterRepository;

@Service
public class PartnerService {

	Logger logger = LoggerFactory.getLogger(PartnerService.class);
	Map<Long, Theater> theaters = new HashMap<>();

	@Autowired
	TheaterRepository theaterRepository;

	public Theater getTheaterDetails(int theaterId) {
		return theaters.get(theaterId);
	}

	public Theater addTheater(Theater theater) {
		if (theaters.containsKey(theater.getId())) {
			// if (theaterRepository.findById(theater.getId()).isPresent()) {
			logger.info("theater already exists in the system");
			return null;
		}

		theaters.put(theater.getId(), theater);
		// theaterRepository.save(theater);

		return theaters.get(theater.getId());
	}

	public Theater updateTheater(Theater theater) {
		if (theaters.containsKey(theater.getId())) {
			logger.info("theaterId found, hence udpated it.");
			theaters.put(theater.getId(), theater);
		}
		return theaters.get(theater.getId());
	}

}
