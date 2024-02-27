package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.model.Room;

public interface RoomService {
	Room findById(Long id);
	List<Room> findAll();
}
