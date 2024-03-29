package rs.ac.uns.ftn.springsecurityexample.model;

import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentType;

import javax.persistence.*;

@Entity
@Table(name = "APPOINTMENT_PRICE")
public class AppointmentPrice {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType appointmentType;
    @Column(name = "price", nullable = false)
    private int price;

    public AppointmentPrice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
