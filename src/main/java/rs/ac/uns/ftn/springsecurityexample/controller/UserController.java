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

import rs.ac.uns.ftn.springsecurityexample.dto.*;
import rs.ac.uns.ftn.springsecurityexample.mapper.UserDataMapper;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	private AppointmentService appointmentService;

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")	
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	@PreAuthorize("hasRole('USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}

	@GetMapping("/pending_users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserData> getAllPending() {
		List<User> pendingUsers = userService.getAllPending();
		List<UserData> dtos = new ArrayList<UserData>();
		for(User user : pendingUsers) {
			dtos.add(UserDataMapper.toDTO(user));
		}
		return dtos;
	}

	@GetMapping("/accept_user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> acceptUser(@RequestParam long userId) {
		this.userService.acceptUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body("User accepted successfully.");
	}

	@GetMapping("/deny_user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> denyUser(@RequestParam long userId, @RequestParam String denyReason) {
		this.userService.denyUser(userId, denyReason);
		return ResponseEntity.status(HttpStatus.OK).body("User denied.");
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserData> updateProfile(@RequestBody UserUpdateDTO userUpdateDTO) {
		UserData userData = this.userService.updateUser(userUpdateDTO);
		if(userData == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userData, HttpStatus.OK);
	}

	@PostMapping("/doctor/search")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<DoctorDTO>> search(@RequestBody DoctorSearchDTO dto) {
		List<DoctorDTO> dtos = this.userService.getFreeDoctors(dto);
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
