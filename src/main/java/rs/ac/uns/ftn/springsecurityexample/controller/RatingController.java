package rs.ac.uns.ftn.springsecurityexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.RatingDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.RatingMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Rating;
import rs.ac.uns.ftn.springsecurityexample.service.RatingService;
import rs.ac.uns.ftn.springsecurityexample.service.impl.RatingServiceImpl;

@RestController
@RequestMapping(value = "/api/rating", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@GetMapping("")
	public ResponseEntity<List<RatingDTO>> findAll() {
		List<Rating> ratings = this.ratingService.findAll();
		List<RatingDTO> dtos = new ArrayList<>();
		for (Rating rating : ratings) {
			dtos.add(RatingMapper.toDTO(rating));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

}
