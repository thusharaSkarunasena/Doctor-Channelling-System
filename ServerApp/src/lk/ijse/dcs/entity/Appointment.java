package lk.ijse.dcs.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment implements SuperEntity{

    @Id
    @Column(name = "appointmentNO")
    private String appointmentNO;
    @Column(name = "dateAndTime")
    private String dateAndTime;
    @Column(name = "appointmentType")
    private String appointmentType;
    @Column(name = "appointmentDate")
    private String appointmentDate;
    @Column(name = "queueNumber")
    private String queueNumber;
    @Column(name = "details")
    private String details;
    @Column(name = "otherDescription")
    private String otherDescription;
    @Column(name = "doctorsDescription")
    private String doctorsDescription;

    @ManyToOne
    @JoinColumn(name = "patientID", referencedColumnName = "patientID")
    private Patient patient;

    @OneToMany(mappedBy = "appointment")
    private List<AppointmentDetails> appointmentDetails=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "doctorsID", referencedColumnName = "employeeID")
    private Employee doctor_appointment;

    @ManyToOne
    @JoinColumn(name = "pharmID", referencedColumnName = "employeeID")
    private Employee pharm_appointment;

    @ManyToOne
    @JoinColumn(name = "cashierID", referencedColumnName = "employeeID")
    private Employee cashier_appointment;

    public Appointment() {
    }

    public Appointment(String appointmentNO, String dateAndTime, String appointmentType, String appointmentDate, String queueNumber, String details, String otherDescription, String doctorsDescription, Patient patient, Employee doctor_appointment, Employee pharm_appointment, Employee cashier_appointment) {
        this.appointmentNO = appointmentNO;
        this.dateAndTime = dateAndTime;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.queueNumber = queueNumber;
        this.details = details;
        this.otherDescription = otherDescription;
        this.doctorsDescription = doctorsDescription;
        this.patient = patient;
        this.doctor_appointment = doctor_appointment;
        this.pharm_appointment = pharm_appointment;
        this.cashier_appointment = cashier_appointment;
    }

    public String getAppointmentNO() {
        return appointmentNO;
    }

    public void setAppointmentNO(String appointmentNO) {
        this.appointmentNO = appointmentNO;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(String queueNumber) {
        this.queueNumber = queueNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
    }

    public String getDoctorsDescription() {
        return doctorsDescription;
    }

    public void setDoctorsDescription(String doctorsDescription) {
        this.doctorsDescription = doctorsDescription;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<AppointmentDetails> getAppointmentDetails() {
        return appointmentDetails;
    }

    public void setAppointmentDetails(List<AppointmentDetails> appointmentDetails) {
        this.appointmentDetails = appointmentDetails;
    }

    public Employee getDoctor_appointment() {
        return doctor_appointment;
    }

    public void setDoctor_appointment(Employee doctor_appointment) {
        this.doctor_appointment = doctor_appointment;
    }

    public Employee getPharm_appointment() {
        return pharm_appointment;
    }

    public void setPharm_appointment(Employee pharm_appointment) {
        this.pharm_appointment = pharm_appointment;
    }

    public Employee getCashier_appointment() {
        return cashier_appointment;
    }

    public void setCashier_appointment(Employee cashier_appointment) {
        this.cashier_appointment = cashier_appointment;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentNO='" + appointmentNO + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", appointmentType='" + appointmentType + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", queueNumber='" + queueNumber + '\'' +
                ", details='" + details + '\'' +
                ", otherDescription='" + otherDescription + '\'' +
                ", doctorsDescription='" + doctorsDescription + '\'' +
                ", patient=" + patient +
                ", doctor_appointment=" + doctor_appointment +
                ", pharm_appointment=" + pharm_appointment +
                ", cashier_appointment=" + cashier_appointment +
                '}';
    }
}
