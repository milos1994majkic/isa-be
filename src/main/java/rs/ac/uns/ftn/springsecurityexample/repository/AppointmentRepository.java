package rs.ac.uns.ftn.springsecurityexample.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentUserDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> getByClinicId(Long clinicId);
	List<Appointment> getByDoctorIdAndDate(Long doctorId, LocalDate date);
	List<Appointment> findByUserIsNullAndClinicId(Long clinicId);
    List<Appointment> findAllByUserId(Long id);
}
