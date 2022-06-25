package lk.ijse.dcs.dto;

public class PaymentDTO implements SuperDTO {

    private String paymentNO;
    private String paymentType;
    private double total;
    private double discount;
    private double netTotal;
    private String appointmentNO;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentNO, String paymentType, double total, double discount, double netTotal, String appointmentNO) {
        this.paymentNO = paymentNO;
        this.paymentType = paymentType;
        this.total = total;
        this.discount = discount;
        this.netTotal = netTotal;
        this.appointmentNO = appointmentNO;
    }

    public String getPaymentNO() {
        return paymentNO;
    }

    public void setPaymentNO(String paymentNO) {
        this.paymentNO = paymentNO;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public String getAppointmentNO() {
        return appointmentNO;
    }

    public void setAppointmentNO(String appointmentNO) {
        this.appointmentNO = appointmentNO;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentNO='" + paymentNO + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                ", netTotal=" + netTotal +
                ", appointmentNO='" + appointmentNO + '\'' +
                '}';
    }
}
