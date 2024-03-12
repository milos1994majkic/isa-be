package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.RoomDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Room;

@Component
public class RoomMapper {

	public static RoomDTO toDTO(Room room) {
		RoomDTO dto = new RoomDTO();
		dto.setId(room.getId());
		dto.setName(room.getName());
		return dto;
	}
}
