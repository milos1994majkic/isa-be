package rs.ac.uns.ftn.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.AppointmentPrice;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentPriceRepository extends JpaRepository<AppointmentPrice, Long> {

    AppointmentPrice getByClinicIdAndAppointmentType(Long clinicId, AppointmentType appointmentType);
}
