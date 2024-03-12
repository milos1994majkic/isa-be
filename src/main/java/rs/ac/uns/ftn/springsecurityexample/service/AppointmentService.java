package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.dto.ClinicSearchDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface AppointmentService {
	Appointment findById(Long id);
	List<Appointment> findAll();

    List<Appointment> getFreeAppointmentsForDoctor(Clinic clinic, User user, ClinicSearchDTO dto);
}
