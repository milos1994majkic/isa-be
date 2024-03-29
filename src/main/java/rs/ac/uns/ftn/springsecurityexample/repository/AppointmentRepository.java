package rs.ac.uns.ftn.springsecurityexample.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> getByClinicId(Long clinicId);
	List<Appointment> getByDoctorIdAndDate(Long doctorId, LocalDate date);
	List<Appointment> findByUserIsNullAndClinicId(Long clinicId);
    List<Appointment> findAllByUserIdAndStatus(Long id, AppointmentStatus status);
}
