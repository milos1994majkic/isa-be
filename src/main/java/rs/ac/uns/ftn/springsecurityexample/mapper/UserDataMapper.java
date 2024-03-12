package rs.ac.uns.ftn.springsecurityexample.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.UserData;
import rs.ac.uns.ftn.springsecurityexample.model.User;

@Component
public class UserDataMapper {

	public static UserData toDTO(User user) {
		UserData dto = new UserData();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setUsername(user.getUsername());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setAddress(user.getAddress());
		dto.setCity(user.getCity());
		dto.setState(user.getState());
		dto.setPhone(user.getPhone());
		dto.setLbo(user.getLbo());
		dto.setRole(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.joining(",")));
		return dto;
	}
}
