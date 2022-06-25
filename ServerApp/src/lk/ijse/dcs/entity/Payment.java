package lk.ijse.dcs.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment implements SuperEntity{

    @Id
    @Column(name = "paymentNO")
    private String paymentNO;
    @Column(name = "paymentType")
    private String paymentType;
    @Column(name = "total")
    private double total;
    @Column(name = "discount")
    private double discount;
    @Column(name = "netTotal")
    private double netTotal;

    @OneToOne(targetEntity = Appointment.class)
    private Appointment appointment_payment;

    public Payment() {
    }

    public Payment(String paymentNO, String paymentType, double total, double discount, double netTotal, Appointment appointment_payment) {
        this.paymentNO = paymentNO;
        this.paymentType = paymentType;
        this.total = total;
        this.discount = discount;
        this.netTotal = netTotal;
        this.appointment_payment = appointment_payment;
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

    public Appointment getAppointment_payment() {
        return appointment_payment;
    }

    public void setAppointment_payment(Appointment appointment_payment) {
        this.appointment_payment = appointment_payment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentNO='" + paymentNO + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                ", netTotal=" + netTotal +
                ", appointment_payment=" + appointment_payment +
                '}';
    }
}
