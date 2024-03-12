package rs.ac.uns.ftn.springsecurityexample.dto;

import java.util.List;

public class DoctorDTO {
    private String firstName;
    private String lastName;
    private float rating;
    private List<AppointmentDTO> freeAppointments;

    public DoctorDTO() {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<AppointmentDTO> getFreeAppointments() {
        return freeAppointments;
    }

    public void setFreeAppointments(List<AppointmentDTO> freeAppointments) {
        this.freeAppointments = freeAppointments;
    }
}
