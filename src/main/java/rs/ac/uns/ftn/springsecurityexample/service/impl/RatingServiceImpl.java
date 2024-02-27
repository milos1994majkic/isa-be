package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Rating;
import rs.ac.uns.ftn.springsecurityexample.repository.RatingRepository;

@Service
public class RatingServiceImpl {

	@Autowired
	private RatingRepository ratingRepository;
	
	public Rating findById(Long id) {
		Rating rating = ratingRepository.findById(id).orElse(null);
		return rating;
	}
	
	public List<Rating> findAll() {
		return ratingRepository.findAll();
	}
}
