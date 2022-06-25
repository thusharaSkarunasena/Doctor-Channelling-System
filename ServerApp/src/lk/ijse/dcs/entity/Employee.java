package lk.ijse.dcs.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements SuperEntity{

    @Id
    @Column(name = "employeeID")
    private String employeeID;
    @Column(name = "name")
    private String name;
    @Column(name = "employment")
    private String employment;
    @Column(name = "address")
    private String address;
    @Column(name = "nic")
    private String nic;
    @Column(name = "cn_home")
    private String cn_home;
    @Column(name = "cn_mobile")
    private String cn_mobile;
    @Column(name = "otherDetails")
    private String otherDetails;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "doctor_appointment")
    private List<Appointment> appointments1=new ArrayList<>();

    @OneToMany(mappedBy = "pharm_appointment")
    private List<Appointment> appointments2=new ArrayList<>();

    @OneToMany(mappedBy = "cashier_appointment")
    private List<Appointment> appointments3=new ArrayList<>();


    @OneToMany(mappedBy = "employee_drugPack")
    private List<DrugPack> cashier_appointment=new ArrayList<>();

    public Employee() {
    }

    public Employee(String employeeID, String name, String employment, String address, String nic, String cn_home, String cn_mobile, String otherDetails, String userName, String password) {
        this.employeeID = employeeID;
        this.name = name;
        this.employment = employment;
        this.address = address;
        this.nic = nic;
        this.cn_home = cn_home;
        this.cn_mobile = cn_mobile;
        this.otherDetails = otherDetails;
        this.userName = userName;
        this.password = password;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
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

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", name='" + name + '\'' +
                ", employment='" + employment + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", cn_home='" + cn_home + '\'' +
                ", cn_mobile='" + cn_mobile + '\'' +
                ", otherDetails='" + otherDetails + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
