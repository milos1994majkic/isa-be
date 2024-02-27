package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.model.Clinic;

public interface ClinicService {
	Clinic findById(Long id);
	List<Clinic> findAll();
}
