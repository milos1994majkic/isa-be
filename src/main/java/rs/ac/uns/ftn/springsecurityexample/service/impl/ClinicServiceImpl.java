package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.repository.ClinicRepository;

@Service
public class ClinicServiceImpl {

	@Autowired
	private ClinicRepository clinicRepository;
	
	public Clinic findById(Long id) {
		Clinic clinic = clinicRepository.findById(id).orElse(null);
		return clinic;
	}
	
	public List<Clinic> findAll() {
		return clinicRepository.findAll();
	}
}
