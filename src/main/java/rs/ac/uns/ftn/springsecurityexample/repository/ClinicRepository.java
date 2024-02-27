package rs.ac.uns.ftn.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

}
