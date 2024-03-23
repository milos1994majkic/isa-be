package rs.ac.uns.ftn.springsecurityexample.service;

import java.time.LocalDate;
import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.dto.*;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;

public interface AppointmentService {
	Appointment findById(Long id);
	List<Appointment> findAll();
    List<Appointment> getFreeAppointmentsForDoctor(Clinic clinic, User user, LocalDate date, AppointmentType type);
	Appointment create(AppointmentDTO dto, User loggedUser);
	Appointment acceptAppointment(Long id);
	Appointment denyAppointment(Long id);
	List<Appointment> findAllPredefined(Long clinicId);
	List<AppointmentUserDTO> findAllByUserId(Long id);
	void reserveAppointment(User loggedUser, Long id);
}
