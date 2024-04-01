package rs.ac.uns.ftn.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.springsecurityexample.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT AVG(r.value) FROM Rating r WHERE r.clinic.id = ?1")
    float getAverageForClinic(long clinicId);
    @Query("SELECT AVG(r.value) FROM Rating r WHERE r.doctor.id = ?1")
    float getAverageForDoctor(long doctorId);
    @Query("SELECT COUNT(r) > 0 FROM Rating r WHERE r.user.id = :userId AND " +
            "(r.clinic.id = :clinicId)")
    boolean existsRatingByUserAndClinic(@Param("userId") long userId, @Param("clinicId") long clinicId);
    @Query("SELECT COUNT(r) > 0 FROM Rating r WHERE r.user.id = :userId AND " +
            "(r.doctor.id = :doctorId)")
    boolean existsRatingByUserAndDoctor(@Param("userId") long userId, @Param("doctorId") long doctorId);
}
