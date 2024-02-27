package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.model.Rating;

public interface RatingService {
	Rating findById(Long id);
	List<Rating> findAll();
}
