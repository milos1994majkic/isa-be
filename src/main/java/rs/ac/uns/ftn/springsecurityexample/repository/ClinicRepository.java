package rs.ac.uns.ftn.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.springsecurityexample.model.Clinic;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    @Query("SELECT c FROM Clinic c WHERE " +
            "(:address IS NULL OR c.address LIKE %:address%) AND " +
            "(:ratingFrom IS NULL OR c.rating >= :ratingFrom) AND " +
            "(:ratingTo IS NULL OR c.rating <= :ratingTo)")
    List<Clinic> findByAddressAndRatingRange(
            @Param("address") String address,
            @Param("ratingFrom") Float ratingFrom,
            @Param("ratingTo") Float ratingTo
    );
}
