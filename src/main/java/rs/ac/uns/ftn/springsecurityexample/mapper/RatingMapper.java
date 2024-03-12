package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.RatingDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Rating;

@Component
public class RatingMapper {

	public static RatingDTO toDTO(Rating rating) {
		RatingDTO dto = new RatingDTO();
		dto.setId(rating.getId());
		dto.setValue(rating.getValue());
		if(rating.getClinic() != null) {
			dto.setClinicName(rating.getClinic().getName());
			
		}
		if(rating.getDoctor() != null) {
			dto.setDoctorName(rating.getDoctor().getFirstName() + " " + rating.getDoctor().getLastName());
			
		}
		dto.setUserName(rating.getUser().getEmail());
		
		return dto;

	}
}
