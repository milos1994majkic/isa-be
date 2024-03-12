package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.RatingCreationDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.model.Rating;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.ClinicRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.RatingRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Rating findById(Long id) {
		Rating rating = ratingRepository.findById(id).orElse(null);
		return rating;
	}
	
	public List<Rating> findAll() {
		return ratingRepository.findAll();
	}
	
	public Rating createAppointment(RatingCreationDTO dto) {
		Rating rating = new Rating();
		rating.setValue(dto.getValue());
		
		Clinic clinic = null;
		if(dto.getClinicId() != -1) {
			clinic = clinicRepository.findById(dto.getClinicId()).orElse(null);
			if(clinic == null) {
				return null;
			}
		}
		
		User doctor = null;
		if(dto.getDoctorId() != -1) {
			doctor = userRepository.findById(dto.getDoctorId()).orElse(null);
			if(doctor == null) {
				return null;
			}
		}
		
		rating.setClinic(clinic);
		rating.setDoctor(doctor);
		
		
		User logged = null;		//uzmi logovanog kada resimo spring security
		rating.setUser(logged);
		ratingRepository.save(rating);
		
		return rating;
	}
}
