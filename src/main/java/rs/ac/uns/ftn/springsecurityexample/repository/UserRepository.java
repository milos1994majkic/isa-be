package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.UserStatus;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	List<User> getByStatus(UserStatus status);

	List<User> findByClinicId(Long clinicId);

	@Query("SELECT u FROM User u WHERE " +
			"(u.clinic.id = :clinicId) AND " +
			"(:firstName IS NULL OR u.firstName LIKE %:firstName%) AND " +
			"(:lastName IS NULL OR u.lastName LIKE %:lastName%) AND " +
			"(:ratingFrom IS NULL OR u.rating >= :ratingFrom) AND " +
			"(:ratingTo IS NULL OR u.rating <= :ratingTo)")
	List<User> searchDoctor(@Param("clinicId") long clinicId,
							  @Param("firstName") String firstName,
							  @Param("lastName") String lastName,
							  @Param("ratingFrom") Float ratingFrom,
							  @Param("ratingTo") Float ratingTo);

	@Query("SELECT u FROM User u WHERE u.activationCode = :id")
	User findByActivationCode(@Param("id") String id);
}
