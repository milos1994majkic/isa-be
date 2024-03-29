package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.dto.RatingCreationDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Rating;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface RatingService {
	Rating findById(Long id);
	List<Rating> findAll();
	Rating create(RatingCreationDTO dto, User loggedUser);
}
