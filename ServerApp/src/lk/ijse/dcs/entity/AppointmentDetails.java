package lk.ijse.dcs.entity;

import javax.persistence.*;

@Entity
@Table(name = "appointmentDetail")
public class AppointmentDetails implements SuperEntity{

    @EmbeddedId
    private AppointmentDetails_FK appointmentDetails_fk;
    @Column(name = "description")
    private String description;
    @Column(name = "qty")
    private int qty;
    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "appointmentNO", referencedColumnName = "appointmentNO", insertable = false, updatable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "drugCode", referencedColumnName = "drugCode", insertable = false, updatable = false)
    private Drug drug_appointmentDetails;

    public AppointmentDetails() {
    }

    public AppointmentDetails(AppointmentDetails_FK appointmentDetails_fk, String description, int qty, double total) {
        this.appointmentDetails_fk = appointmentDetails_fk;
        this.description = description;
        this.qty = qty;
        this.total = total;
    }

    public AppointmentDetails_FK getAppointmentDetails_fk() {
        return appointmentDetails_fk;
    }

    public void setAppointmentDetails_fk(AppointmentDetails_FK appointmentDetails_fk) {
        this.appointmentDetails_fk = appointmentDetails_fk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "AppointmentDetails{" +
                "appointmentDetails_fk=" + appointmentDetails_fk +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
