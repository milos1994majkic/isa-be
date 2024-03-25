package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.RatingCreationDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.RatingDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.AppointmentMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.RatingCreationMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.RatingMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Rating;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.RatingService;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;
import rs.ac.uns.ftn.springsecurityexample.service.impl.RatingServiceImpl;

@RestController
@RequestMapping(value = "/api/rating", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<List<RatingDTO>> findAll() {
		List<Rating> ratings = this.ratingService.findAll();
		List<RatingDTO> dtos = new ArrayList<>();
		for (Rating rating : ratings) {
			dtos.add(RatingMapper.toDTO(rating));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping()
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<RatingCreationDTO> create(@RequestBody RatingCreationDTO dto, Principal logged) {
		User user = userService.findByUsername(logged.getName());
		Rating newRating = ratingService.create(dto, user);
		if(newRating == null){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		RatingCreationDTO ratingCreationDTO = RatingCreationMapper.toDTO(newRating);
		return new ResponseEntity<>(ratingCreationDTO, HttpStatus.OK);
	}

}
