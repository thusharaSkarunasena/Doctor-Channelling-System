package lk.ijse.dcs.view.util.tblModel;

public class Doctor_PreviousTreatment_AppointmentTM {

    private String appointmentNO;
    private String date;
    private String type;
    private String patientID;

    public Doctor_PreviousTreatment_AppointmentTM() {
    }

    public Doctor_PreviousTreatment_AppointmentTM(String appointmentNO, String date, String type, String patientID) {
        this.appointmentNO = appointmentNO;
        this.date = date;
        this.type = type;
        this.patientID = patientID;
    }

    public String getAppointmentNO() {
        return appointmentNO;
    }

    public void setAppointmentNO(String appointmentNO) {
        this.appointmentNO = appointmentNO;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "Doctor_PreviousTreatment_AppointmentTM{" +
                "appointmentNO='" + appointmentNO + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", patientID='" + patientID + '\'' +
                '}';
    }
}
