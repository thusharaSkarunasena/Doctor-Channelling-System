package lk.ijse.dcs.dto;

public class AppointmentDetailsDTO implements SuperDTO {

    private String appointmentNO;
    private String drugCode;
    private String drugPackCode;
    private String description;
    private int qty;
    private double total;

    public AppointmentDetailsDTO() {
    }

    public AppointmentDetailsDTO(String appointmentNO, String drugCode, String drugPackCode, String description, int qty, double total) {
        this.appointmentNO = appointmentNO;
        this.drugCode = drugCode;
        this.drugPackCode = drugPackCode;
        this.description = description;
        this.qty = qty;
        this.total = total;
    }

    public String getAppointmentNO() {
        return appointmentNO;
    }

    public void setAppointmentNO(String appointmentNO) {
        this.appointmentNO = appointmentNO;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugPackCode() {
        return drugPackCode;
    }

    public void setDrugPackCode(String drugPackCode) {
        this.drugPackCode = drugPackCode;
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
        return "AppointmentDetailsDTO{" +
                "appointmentNO='" + appointmentNO + '\'' +
                ", drugCode='" + drugCode + '\'' +
                ", drugPackCode='" + drugPackCode + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
