package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;

public interface AppointmentService {
	Appointment findById(Long id);
	List<Appointment> findAll();
}
