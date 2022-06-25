package lk.ijse.dcs.view.util.tblModel;

public class Doctor_PatientHistory_AppointmentTM {

    private String appointmentNO;
    private String date;
    private String type;
    private String doctorsID;

    public Doctor_PatientHistory_AppointmentTM() {
    }

    public Doctor_PatientHistory_AppointmentTM(String appointmentNO, String date, String type, String doctorsID) {
        this.appointmentNO = appointmentNO;
        this.date = date;
        this.type = type;
        this.doctorsID = doctorsID;
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

    public String getDoctorsID() {
        return doctorsID;
    }

    public void setDoctorsID(String doctorsID) {
        this.doctorsID = doctorsID;
    }

    @Override
    public String toString() {
        return "Doctor_PatientHistory_AppointmentTM{" +
                "appointmentNO='" + appointmentNO + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", doctorsID='" + doctorsID + '\'' +
                '}';
    }
}
