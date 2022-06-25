package lk.ijse.dcs.view.util.tblModel;

public class Pharm_PreviousSendOuts_SendOutTM {

    private String appointmentNO;
    private String date;
    private String time;
    private String patientID;

    public Pharm_PreviousSendOuts_SendOutTM() {
    }

    public Pharm_PreviousSendOuts_SendOutTM(String appointmentNO, String date, String time, String patientID) {
        this.appointmentNO = appointmentNO;
        this.date = date;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "Pharm_PreviousSendOuts_SendOutTM{" +
                "appointmentNO='" + appointmentNO + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", patientID='" + patientID + '\'' +
                '}';
    }
}
