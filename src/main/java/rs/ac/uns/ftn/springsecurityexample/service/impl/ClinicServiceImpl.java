package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.ClinicDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.ClinicSearchDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.DoctorDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.ClinicMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.DoctorMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.AppointmentPrice;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentPriceRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ClinicRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;
import rs.ac.uns.ftn.springsecurityexample.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private AppointmentPriceRepository appointmentPriceRepository;

	public Clinic findById(Long id) {
		Clinic clinic = clinicRepository.findById(id).orElse(null);
		return clinic;
	}
	
	public List<Clinic> findAll() {
		return clinicRepository.findAll();
	}




	private List<Appointment> getFreeAppointmentsForClinic(Clinic clinic, ClinicSearchDTO dto){

		List<Appointment> freeAppointments = new ArrayList<Appointment>();
		List<User> doctors = userRepository.findByClinicId(clinic.getId());
		for(User user : doctors){
			List<Appointment> doctorFreeAppointments = appointmentService.getFreeAppointmentsForDoctor(clinic, user, dto.getDate(), dto.getAppointmentType());
			freeAppointments.addAll(doctorFreeAppointments);
		}
		return freeAppointments;
	}

	public List<ClinicDTO> search(ClinicSearchDTO dto) {

		List<Clinic> additionalSearchedClinics = clinicRepository.findByAddressAndRatingRange(dto.getAddress(), dto.getRatingFrom(), dto.getRatingTo());

		List<ClinicDTO> searchedClinicDtos = new ArrayList<ClinicDTO>();
		for(Clinic clinic : additionalSearchedClinics){
			List<Appointment> freeAppointments = getFreeAppointmentsForClinic(clinic, dto);
			if(!freeAppointments.isEmpty()){
				ClinicDTO clinicDTO = ClinicMapper.toDTO(clinic);

				AppointmentPrice price = appointmentPriceRepository.getByClinicIdAndAppointmentType(clinic.getId(), dto.getAppointmentType());
				clinicDTO.setPrice(price.getPrice());
				searchedClinicDtos.add(clinicDTO);
			}
		}
		return searchedClinicDtos;
	}


}
