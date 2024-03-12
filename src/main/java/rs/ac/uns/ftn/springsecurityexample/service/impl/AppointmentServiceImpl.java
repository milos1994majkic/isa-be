package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.ClinicSearchDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentRepository;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	public Appointment findById(Long id) {
		Appointment appointment = appointmentRepository.findById(id).orElse(null);
		return appointment;
	}

	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	public List<Appointment> getAllByClinicId(long clinicId) {
		return appointmentRepository.getByClinicId(clinicId);
	}

	private boolean isDoctorFree(List<Appointment> doctorAppointments, LocalTime time){
		for(Appointment appointment : doctorAppointments){
			if(appointment.getTime() == time){
				return false;
			}
		}
		return true;
	}
	public List<Appointment> getFreeAppointmentsForDoctor(Clinic clinic, User doctor, ClinicSearchDTO dto){
		if(doctor.getAppointmentType() != dto.getAppointmentType()){
			return new ArrayList<Appointment>();
		}

		List<Appointment> freeAppointments = new ArrayList<Appointment>();
		List<Appointment> doctorAppointments = appointmentRepository.getByDoctorIdAndDate(doctor.getId(), dto.getDate());

		LocalTime startTime = clinic.getStartTime();
		LocalTime endTime = clinic.getEndTime();

		while(startTime.isBefore(endTime)){
			if(isDoctorFree(doctorAppointments, startTime)){
				Appointment appointment = new Appointment();
				appointment.setDate(dto.getDate());
				appointment.setTime(startTime);
				appointment.setDoctor(doctor);
				appointment.setUser(null);
				appointment.setClinic(clinic);
				appointment.setRoom(null);
				appointment.setStatus(AppointmentStatus.CREATED);
				appointment.setType(dto.getAppointmentType());

				freeAppointments.add(appointment);
			}

			startTime = startTime.plusMinutes(30);
		}
		return freeAppointments;
	}
	
}
