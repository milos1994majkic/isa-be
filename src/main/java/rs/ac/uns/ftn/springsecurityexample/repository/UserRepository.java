package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.UserStatus;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	List<User> getByStatus(UserStatus status);

	List<User> findByClinicId(Long clinicId);
}
