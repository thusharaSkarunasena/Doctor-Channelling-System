package lk.ijse.dcs.dto;

public class AppointmentDTO implements SuperDTO {

    private String appointmentNO;
    private String patientID;
    private String dateAndTime;
    private String appointmentDate;
    private String appointmentType;
    private String doctorsID;
    private String pharmID;
    private String cashierID;
    private String details;
    private String otherDescription;
    private String queueNumber;
    private String doctorsDescription;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String appointmentNO, String patientID, String dateAndTime, String appointmentDate, String appointmentType, String doctorsID, String pharmID, String cashierID, String details, String otherDescription, String queueNumber, String doctorsDescription) {
        this.appointmentNO = appointmentNO;
        this.patientID = patientID;
        this.dateAndTime = dateAndTime;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.doctorsID = doctorsID;
        this.pharmID = pharmID;
        this.cashierID = cashierID;
        this.details = details;
        this.otherDescription = otherDescription;
        this.queueNumber = queueNumber;
        this.doctorsDescription = doctorsDescription;
    }

    public String getAppointmentNO() {
        return appointmentNO;
    }

    public void setAppointmentNO(String appointmentNO) {
        this.appointmentNO = appointmentNO;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDoctorsID() {
        return doctorsID;
    }

    public void setDoctorsID(String doctorsID) {
        this.doctorsID = doctorsID;
    }

    public String getPharmID() {
        return pharmID;
    }

    public void setPharmID(String pharmID) {
        this.pharmID = pharmID;
    }

    public String getCashierID() {
        return cashierID;
    }

    public void setCashierID(String casheirID) {
        this.cashierID = casheirID;
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

    public String getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(String queueNumber) {
        this.queueNumber = queueNumber;
    }

    public String getDoctorsDescription() {
        return doctorsDescription;
    }

    public void setDoctorsDescription(String doctorsDescription) {
        this.doctorsDescription = doctorsDescription;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "appointmentNO='" + appointmentNO + '\'' +
                ", patientID='" + patientID + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentType='" + appointmentType + '\'' +
                ", doctorsID='" + doctorsID + '\'' +
                ", pharmID='" + pharmID + '\'' +
                ", cashierID='" + cashierID + '\'' +
                ", details='" + details + '\'' +
                ", otherDescription='" + otherDescription + '\'' +
                ", queueNumber='" + queueNumber + '\'' +
                ", doctorsDescription='" + doctorsDescription + '\'' +
                '}';
    }
}
