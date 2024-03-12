package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;

import java.time.LocalDate;

public class ClinicSearchDTO {
    private LocalDate date;
    private AppointmentType appointmentType;
    private String address;
    private Float ratingFrom;
    private Float ratingTo;

    public ClinicSearchDTO() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
