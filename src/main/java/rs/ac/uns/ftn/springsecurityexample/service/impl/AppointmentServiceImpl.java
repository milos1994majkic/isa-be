package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public Appointment findById(Long id) {
		Appointment appointment = appointmentRepository.findById(id).orElse(null);
		return appointment;
	}
	
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}
}
