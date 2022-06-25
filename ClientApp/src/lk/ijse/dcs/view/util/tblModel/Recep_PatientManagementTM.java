package lk.ijse.dcs.view.util.tblModel;

public class Recep_PatientManagementTM {

    private String patientID;
    private String name;
    private String dob;
    private String gender;
    private String nic;

    public Recep_PatientManagementTM() {
    }

    public Recep_PatientManagementTM(String patientID, String name, String dob, String gender, String nic) {
        this.patientID = patientID;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.nic = nic;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Recep_PatientManagementTM{" +
                "patientID='" + patientID + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
