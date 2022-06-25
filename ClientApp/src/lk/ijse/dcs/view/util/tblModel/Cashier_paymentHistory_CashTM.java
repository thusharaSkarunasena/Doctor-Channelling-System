package lk.ijse.dcs.view.util.tblModel;

public class Cashier_paymentHistory_CashTM {

    private String date;
    private String appointmentNO;
    private String patientID;
    private String paymentNO;
    private String total;
    private String discount;
    private String paybleTotal;
    private String cashPayment;

    public Cashier_paymentHistory_CashTM() {
    }

    public Cashier_paymentHistory_CashTM(String date, String appointmentNO, String patientID, String paymentNO, String total, String discount, String paybleTotal, String cashPayment) {
        this.date = date;
        this.appointmentNO = appointmentNO;
        this.patientID = patientID;
        this.paymentNO = paymentNO;
        this.total = total;
        this.discount = discount;
        this.paybleTotal = paybleTotal;
        this.cashPayment = cashPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPaymentNO() {
        return paymentNO;
    }

    public void setPaymentNO(String paymentNO) {
        this.paymentNO = paymentNO;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPaybleTotal() {
        return paybleTotal;
    }

    public void setPaybleTotal(String paybleTotal) {
        this.paybleTotal = paybleTotal;
    }

    public String getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(String cashPayment) {
        this.cashPayment = cashPayment;
    }

    @Override
    public String toString() {
        return "Cashier_paymentHistory_CashTM{" +
                "date='" + date + '\'' +
                ", appointmentNO='" + appointmentNO + '\'' +
                ", patientID='" + patientID + '\'' +
                ", paymentNO='" + paymentNO + '\'' +
                ", total='" + total + '\'' +
                ", discount='" + discount + '\'' +
                ", paybleTotal='" + paybleTotal + '\'' +
                ", cashPayment='" + cashPayment + '\'' +
                '}';
    }
}
