package rs.ac.uns.ftn.springsecurityexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Room;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;
import rs.ac.uns.ftn.springsecurityexample.service.impl.AppointmentServiceImpl;

@RestController
@RequestMapping(value = "api/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	
}
