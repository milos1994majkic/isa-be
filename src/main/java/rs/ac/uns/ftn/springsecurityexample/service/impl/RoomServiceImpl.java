package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Room;
import rs.ac.uns.ftn.springsecurityexample.repository.RoomRepository;

@Service
public class RoomServiceImpl {

	@Autowired
	private RoomRepository roomRepository;

	public Room findById(Long id) {
		Room room = roomRepository.findById(id).orElse(null);
		return room;
	}

	public List<Room> findAll() {
		return roomRepository.findAll();
	}
}
