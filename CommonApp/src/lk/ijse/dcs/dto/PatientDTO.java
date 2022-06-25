package lk.ijse.dcs.dto;

public class PatientDTO implements SuperDTO {

    private String patientID;
    private String name;
    private String gender;
    private String dob;
    private String address;
    private String nic;
    private String cn_home;
    private String cn_mobile;

    public PatientDTO() {
    }

    public PatientDTO(String patientID, String name, String gender, String dob, String address, String nic, String cn_home, String cn_mobile) {
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
        return "PatientDTO{" +
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
