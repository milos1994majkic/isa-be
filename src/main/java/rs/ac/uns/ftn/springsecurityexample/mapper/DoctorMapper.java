package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.springsecurityexample.dto.DoctorDTO;
import rs.ac.uns.ftn.springsecurityexample.model.User;

@Component
public class DoctorMapper {
    public static DoctorDTO toDTO(User user) {
        DoctorDTO dto = new DoctorDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());

        return dto;
    }
}
