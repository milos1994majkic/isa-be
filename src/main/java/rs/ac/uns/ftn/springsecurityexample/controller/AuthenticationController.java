package rs.ac.uns.ftn.springsecurityexample.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.ac.uns.ftn.springsecurityexample.dto.JwtAuthenticationRequest;
import rs.ac.uns.ftn.springsecurityexample.dto.UserRequest;
import rs.ac.uns.ftn.springsecurityexample.dto.UserTokenState;
import rs.ac.uns.ftn.springsecurityexample.exception.ResourceConflictException;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;
import rs.ac.uns.ftn.springsecurityexample.util.TokenUtils;


//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) throws JsonProcessingException {
		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user);
		int expiresIn = tokenUtils.getExpiredIn();
		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	
	@PostMapping("/signup")
	public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {
		// this need to add to signup form
		String newUsername = "";
		try {
			String email = userRequest.getEmail();
			int atIndex = email.indexOf('@');
			newUsername = email.substring(0, atIndex);
			userRequest.setUsername(newUsername);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid email format.");
		}

		User existUser = this.userService.findByUsername(newUsername);

		if (existUser != null) {
			throw new ResourceConflictException(userRequest.getId(), "Username already exists");
		}

		this.userService.save(userRequest);
		
		String message = "You have successfully sent a registration invitation. " +
				"An administrator will respond to you via email. Please check your email.";

		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok("Logged out successfully");
	}

	@PutMapping("/activate")
	public ResponseEntity<?> activateAccount(@RequestBody String id) {
		this.userService.activateAccount(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
