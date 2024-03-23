package rs.ac.uns.ftn.springsecurityexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rs.ac.uns.ftn.springsecurityexample.dto.RoomDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.RoomMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Room;
import rs.ac.uns.ftn.springsecurityexample.service.RoomService;
import rs.ac.uns.ftn.springsecurityexample.service.impl.RoomServiceImpl;

@RestController
@RequestMapping(value = "/api/room", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public RoomDTO findById(@PathVariable Long id) {
		return RoomMapper.toDTO(this.roomService.findById(id));
	}

	@GetMapping("")
	public ResponseEntity<List<RoomDTO>> findAll() {
		List<Room> rooms = this.roomService.findAll();
		List<RoomDTO> dtos = new ArrayList<>();
		for (Room room : rooms) {
			dtos.add(RoomMapper.toDTO(room));
		}
		return new ResponseEntity<List<RoomDTO>>(dtos, HttpStatus.OK);
	}
}
