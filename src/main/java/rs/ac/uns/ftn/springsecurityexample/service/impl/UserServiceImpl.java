package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.*;
import rs.ac.uns.ftn.springsecurityexample.mapper.AppointmentMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.UserDataMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.model.Role;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.UserStatus;
import rs.ac.uns.ftn.springsecurityexample.repository.ClinicRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;
import rs.ac.uns.ftn.springsecurityexample.service.RoleService;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private ClinicRepository clinicRepository;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) throws AccessDeniedException {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

	@Override
	public User save(UserRequest userRequest) {
		User u = new User();
		u.setUsername(userRequest.getUsername());
		
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		u.setFirstName(userRequest.getFirstname());
		u.setLastName(userRequest.getLastname());
		u.setEmail(userRequest.getEmail());
		u.setAddress(userRequest.getAddress());
		u.setCity(userRequest.getCity());
		u.setState(userRequest.getState());
		u.setPhone(userRequest.getPhone());
		u.setLbo(userRequest.getLbo());
		
		u.setActivationCode(emailService.generateActivationCode());

		u.setStatus(UserStatus.PENDING);
		
		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		List<Role> roles = roleService.findByName("ROLE_USER");
		u.setRoles(roles);
		
		//emailService.sendActivationCode(u);
		
		return this.userRepository.save(u);
	}
	
	public List<User> getAllPending(){
		return userRepository.getByStatus(UserStatus.PENDING);
	}
	
	public User acceptUser(long userId) {
		User user = userRepository.findById(userId).orElseGet(null);
		if(user == null) {
			return null;
		}
		
		user.setStatus(UserStatus.ACCEPTED);
		
		userRepository.save(user);
		
		emailService.sendActivationCode(user);
		
		return user;
	}
	
	public User denyUser(long userId, String denyReason) {
		User user = userRepository.findById(userId).orElseGet(null);
		if(user == null) {
			return null;
		}
		
		user.setStatus(UserStatus.DENIED);
		
		user.setDenialReason(denyReason);
		
		userRepository.save(user);
		
		emailService.sendDenialReason(user);
		
		return user;
	}

	public UserData updateUser(UserUpdateDTO userUpdateDTO) {
		User user = userRepository.findById(userUpdateDTO.getId()).orElseGet(null);
		if(user == null) {
			return null;
		}

		user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
		user.setFirstName(userUpdateDTO.getFirstname());
		user.setLastName(userUpdateDTO.getLastname());
		user.setAddress(userUpdateDTO.getAddress());
		user.setCity(userUpdateDTO.getCity());
		user.setState(userUpdateDTO.getState());
		user.setPhone(userUpdateDTO.getPhone());

		userRepository.save(user);

        return UserDataMapper.toDTO(user);
	}


	private List<DoctorDTO> getFreeDoctors(long clinicId, ClinicSearchDTO dto){

		Clinic clinic = clinicRepository.findById(clinicId).orElseGet(null);
		if(clinic == null){
			return null;
		}

		List<User> doctors = userRepository.findByClinicId(clinicId);

		List<DoctorDTO> doctorDtos = new ArrayList<DoctorDTO>();
		for(User user : doctors){
			List<Appointment> doctorFreeAppointments = appointmentService.getFreeAppointmentsForDoctor(clinic, user ,dto);
			List<AppointmentDTO> doctorFreeAppointmentsDtos = AppointmentMapper.toDTOList(doctorFreeAppointments);

			DoctorDTO doctorDTO = new DoctorDTO();
			doctorDTO.setFirstName(user.getFirstName());
			doctorDTO.setLastName(user.getLastName());
			doctorDTO.setRating(0);	//ocena doktora
			doctorDTO.setFreeAppointments(doctorFreeAppointmentsDtos);

			doctorDtos.add(doctorDTO);
		}
		return doctorDtos;
	}

}
