package lk.ijse.dcs.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient implements SuperEntity{

    @Id
    @Column(name = "patientID")
    private String patientID;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "dob")
    private String dob;
    @Column(name = "address")
    private String address;
    @Column(name = "nic")
    private String nic;
    @Column(name = "cn_home")
    private String cn_home;
    @Column(name = "cn_mobile")
    private String cn_mobile;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments=new ArrayList<>();

    public Patient() {
    }

    public Patient(String patientID, String name, String gender, String dob, String address, String nic, String cn_home, String cn_mobile) {
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.nic = nic;
        this.cn_home = cn_home;
        this.cn_mobile = cn_mobile;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCn_home() {
        return cn_home;
    }

    public void setCn_home(String cn_home) {
        this.cn_home = cn_home;
    }

    public String getCn_mobile() {
        return cn_mobile;
    }

    public void setCn_mobile(String cn_mobile) {
        this.cn_mobile = cn_mobile;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID='" + patientID + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", cn_home='" + cn_home + '\'' +
                ", cn_mobile='" + cn_mobile + '\'' +
                '}';
    }
}
