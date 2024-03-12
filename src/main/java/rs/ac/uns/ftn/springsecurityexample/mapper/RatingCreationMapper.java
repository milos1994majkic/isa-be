package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.RatingCreationDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Rating;

@Component
public class RatingCreationMapper {

	public static RatingCreationDTO toDTO(Rating rating) {
		RatingCreationDTO dto = new RatingCreationDTO();
		dto.setId(rating.getId());
		dto.setValue(rating.getValue());
		dto.setClinicId(rating.getClinic().getId());
		dto.setDoctorId(rating.getDoctor().getId());
		return dto;
	}
}
