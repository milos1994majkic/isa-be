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

	public void updateClinicRating(long clinicId){
		Clinic clinic = clinicRepository.findById(clinicId).orElse(null);
		if(clinic == null){
			return;
		}
		float averageGrade = ratingRepository.getAverageForClinic(clinicId);
		clinic.setRating(averageGrade);
		clinicRepository.save(clinic);
	}

	public void updateDoctorRating(long doctorId){
		User user = userRepository.findById(doctorId).orElse(null);
		if(user == null){
			return;
		}
		float averageGrade = ratingRepository.getAverageForDoctor(doctorId);
		user.setRating(averageGrade);
		userRepository.save(user);
	}

	public Rating create(RatingCreationDTO dto, User loggedUser) {
		Rating rating = new Rating();
		rating.setValue(dto.getValue());
		Clinic clinic = null;
		if(dto.getClinicId() != 0) {
			clinic = clinicRepository.findById(dto.getClinicId()).orElse(null);
		}
		User doctor = null;
		if(dto.getDoctorId() != 0) {
			doctor = userRepository.findById(dto.getDoctorId()).orElse(null);
		}
		rating.setClinic(clinic);
		rating.setDoctor(doctor);
		rating.setUser(loggedUser);
		ratingRepository.save(rating);
		if(clinic != null){
			updateClinicRating(clinic.getId());
		} else if(doctor != null){
			updateDoctorRating(doctor.getId());
		}
		return rating;
	}
}
