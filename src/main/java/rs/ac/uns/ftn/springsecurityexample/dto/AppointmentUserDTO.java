package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentUserDTO {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private long doctorId;
    private String doctor;
    private long userId;
    private boolean ratedClinic;
    private boolean ratedDoctor;
    private long clinicId;
    private String clinic;
    private String room;
    private AppointmentStatus status;
    private AppointmentType type;
    private int price;

    public AppointmentUserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    public boolean isRatedClinic() {
        return ratedClinic;
    }

    public void setRatedClinic(boolean ratedClinic) {
        this.ratedClinic = ratedClinic;
    }

    public boolean isRatedDoctor() {
        return ratedDoctor;
    }

    public void setRatedDoctor(boolean ratedDoctor) {
        this.ratedDoctor = ratedDoctor;
    }
}
