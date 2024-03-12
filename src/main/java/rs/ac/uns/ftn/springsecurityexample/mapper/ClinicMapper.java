package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.ClinicDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;

@Component
public class ClinicMapper {

	public static ClinicDTO toDTO(Clinic clinic) {
		ClinicDTO dto = new ClinicDTO();
		dto.setId(clinic.getId());
		dto.setName(clinic.getName());
		dto.setAddress(clinic.getAddress());
		dto.setRating(clinic.getRating());
		return dto;
	}
}
