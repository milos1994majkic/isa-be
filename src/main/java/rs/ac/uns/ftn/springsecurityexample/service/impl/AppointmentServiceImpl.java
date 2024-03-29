package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentUserDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.ClinicSearchDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.AppointmentMapper;
import rs.ac.uns.ftn.springsecurityexample.model.*;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;
import rs.ac.uns.ftn.springsecurityexample.repository.*;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ClinicRepository clinicRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private AppointmentPriceRepository appointmentPriceRepository;

	@Autowired
	private EmailService emailService;

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
	public List<Appointment> getFreeAppointmentsForDoctor(Clinic clinic, User doctor, LocalDate date, AppointmentType type){
		if(doctor.getAppointmentType() != type){
			return new ArrayList<Appointment>();
		}

		List<Appointment> freeAppointments = new ArrayList<Appointment>();
		List<Appointment> doctorAppointments = appointmentRepository.getByDoctorIdAndDate(doctor.getId(), date);

		LocalTime startTime = clinic.getStartTime();
		LocalTime endTime = clinic.getEndTime();

		while(startTime.isBefore(endTime)){
			if(isDoctorFree(doctorAppointments, startTime)){
				Appointment appointment = new Appointment();
				appointment.setDate(date);
				appointment.setTime(startTime);
				appointment.setDoctor(doctor);
				appointment.setUser(null);
				appointment.setClinic(clinic);
				appointment.setRoom(null);
				appointment.setStatus(AppointmentStatus.CREATED);
				appointment.setType(type);
				AppointmentPrice price = appointmentPriceRepository.getByClinicIdAndAppointmentType(clinic.getId(), type);
				appointment.setPrice(price.getPrice());

				freeAppointments.add(appointment);
			}

			startTime = startTime.plusMinutes(30);
		}
		return freeAppointments;
	}

	public Appointment create(AppointmentDTO dto, User loggedUser) {
		User doctor = this.userRepository.findById(dto.getDoctorId()).orElse(null);
		if(doctor == null){
			return null;
		}
		Clinic clinic = this.clinicRepository.findById(dto.getClinicId()).orElse(null);
		if(clinic == null){
			return null;
		}

		Room room = roomRepository.findById(1l).orElse(null);

		Appointment appointment = AppointmentMapper.toDomain(dto);
		appointment.setUser(loggedUser);
		appointment.setRoom(room);
		appointment.setStatus(AppointmentStatus.CREATED);
		appointment.setDoctor(doctor);
		appointment.setClinic(clinic);
		appointment.setDate(dto.getDate());
		appointment.setType(dto.getType());
		appointment.setPrice(dto.getPrice());
		appointment = this.appointmentRepository.save(appointment);
		emailService.sendAppointmentInquiry(loggedUser, appointment);

		return appointment;
	}

	public Appointment acceptAppointment(Long id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElse(null);
		if (appointment == null) {
			return null;
		}

		if (appointment.getStatus() != AppointmentStatus.CREATED) {
			return null;
		}
		appointment.setStatus(AppointmentStatus.ACCEPTED);

		this.appointmentRepository.save(appointment);

		return appointment;
	}

	public Appointment denyAppointment(Long id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElse(null);
		if (appointment == null) {
			return null;
		}
		if (appointment.getStatus() != AppointmentStatus.CREATED) {
			return null;
		}
		appointment.setStatus(AppointmentStatus.DENIED);

		this.appointmentRepository.save(appointment);

		return appointment;
	}

	public List<Appointment> findAllPredefined(Long clinicId) {

		return appointmentRepository.findByUserIsNullAndClinicId(clinicId);
	}

	public List<AppointmentUserDTO> findAllByUserId(Long id) {
		List<Appointment> appointments = appointmentRepository.findAllByUserIdAndStatus(id, AppointmentStatus.ACCEPTED);
		List<AppointmentUserDTO> appointmentsUser = new ArrayList<AppointmentUserDTO>();
		for (Appointment appointment: appointments) {
			AppointmentUserDTO dto = new AppointmentUserDTO();

			dto.setId(appointment.getId());
			dto.setDate(appointment.getDate());
			dto.setTime(appointment.getTime());
			dto.setDoctorId(appointment.getDoctor().getId());
			dto.setDoctor(appointment.getDoctor().getFirstName() + " " +appointment.getDoctor().getLastName());
			dto.setUserId(appointment.getUser().getId());
			dto.setClinicId(appointment.getClinic().getId());
			dto.setClinic(appointment.getClinic().getName());
			dto.setRoom(appointment.getRoom().getName());
			dto.setStatus(appointment.getStatus());
			dto.setPrice(appointment.getPrice());
			dto.setType(appointment.getType());

			dto.setRated(ratingRepository.existsRatingByUserAndDoctorOrClinic(appointment.getUser().getId(), appointment.getDoctor().getId(), appointment.getClinic().getId()));

			appointmentsUser.add(dto);
		}

		return appointmentsUser;
	}

	public void reserveAppointment(User loggedUser, Long id) {
		Appointment appointment = this.appointmentRepository.findById(id).orElse(null);
		if (appointment == null) {
			return;
		}
		appointment.setUser(loggedUser);
		appointmentRepository.save(appointment);
		emailService.sendAppointmentConfiramtion(loggedUser);
	}
}
