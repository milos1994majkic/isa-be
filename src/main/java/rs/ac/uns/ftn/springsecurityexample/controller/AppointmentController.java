package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentUserDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.DoctorDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.DoctorSearchDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.AppointmentMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Room;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;
import rs.ac.uns.ftn.springsecurityexample.service.impl.AppointmentServiceImpl;

@RestController
@RequestMapping(value = "/api/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserService userService;

	@PostMapping()
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO dto, Principal logged) {
		User user = userService.findByUsername(logged.getName());
		Appointment newCreated = this.appointmentService.create(dto, user);
		if(newCreated == null){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		AppointmentDTO newCreatedDto = AppointmentMapper.toDTO(newCreated);
		return new ResponseEntity<>(newCreatedDto, HttpStatus.OK);
	}

	@PutMapping("/acceptAppointment/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AppointmentDTO> acceptAppointment(@PathVariable Long id) {
		Appointment updatedAppointment = this.appointmentService.acceptAppointment(id);
		if(updatedAppointment == null){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		AppointmentDTO newDto = AppointmentMapper.toDTO(updatedAppointment);
		return new ResponseEntity<>(newDto, HttpStatus.OK);
	}

	@PutMapping("/denyAppointment/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AppointmentDTO> denyAppointment(@PathVariable Long id) {
		Appointment updatedAppointment = this.appointmentService.denyAppointment(id);
		if(updatedAppointment == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		AppointmentDTO newDto = AppointmentMapper.toDTO(updatedAppointment);
		return new ResponseEntity<>(newDto, HttpStatus.OK);
	}

	@GetMapping("/byClinicId/{clinicId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Appointment>> getAllPredefinedAppointments(@PathVariable Long clinicId) {
		List<Appointment> response = appointmentService.findAllPredefined(clinicId);
//		List<AppointmentDTO> dtos = AppointmentMapper.toDTOList(response);
		return new ResponseEntity<List<Appointment>>(response, HttpStatus.OK);
	}

	@GetMapping()
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<AppointmentUserDTO>> getAllUserAppointments(Principal logged) {
		User user = userService.findByUsername(logged.getName());

		List<AppointmentUserDTO> response = appointmentService.findAllByUserId(user.getId());
		return new ResponseEntity<List<AppointmentUserDTO>>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> reserveDefaultAppointment (@PathVariable Long id, Principal logged) {
		User user = userService.findByUsername(logged.getName());

		appointmentService.reserveAppointment(user, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
