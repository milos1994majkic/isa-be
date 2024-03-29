package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;

import java.time.LocalDate;

public class DoctorSearchDTO {
    private String firstName;
    private String lastName;
    private Float ratingFrom;
    private Float ratingTo;
    private long clinicId;
    private LocalDate date;
    private AppointmentType appointmentType;

    public DoctorSearchDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Float getRatingFrom() {
        return ratingFrom;
    }

    public void setRatingFrom(Float ratingFrom) {
        this.ratingFrom = ratingFrom;
    }

    public Float getRatingTo() {
        return ratingTo;
    }

    public void setRatingTo(Float ratingTo) {
        this.ratingTo = ratingTo;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }
}
