package rs.ac.uns.ftn.springsecurityexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rs.ac.uns.ftn.springsecurityexample.dto.ClinicDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.ClinicSearchDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.ClinicMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.service.ClinicService;

@RestController
@RequestMapping(value = "/api/clinic", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ClinicController {
	@Autowired
	private ClinicService clinicService;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ClinicDTO findById(@PathVariable Long id) {
		return ClinicMapper.toDTO(this.clinicService.findById(id));
	}
	
	@GetMapping()
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<ClinicDTO>> findAll() {
		List<Clinic> clinics = this.clinicService.findAll();
		List<ClinicDTO> dtos = new ArrayList<>();
		for (Clinic clinic : clinics) {
			dtos.add(ClinicMapper.toDTO(clinic));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping("/search")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<ClinicDTO>> search(@RequestBody ClinicSearchDTO dto) {
		List<ClinicDTO> dtos = this.clinicService.search(dto);
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
