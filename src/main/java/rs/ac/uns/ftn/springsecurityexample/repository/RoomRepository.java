package rs.ac.uns.ftn.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
