package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.PaymentDTO;

public interface Cashier_PaymentBO extends SuperBO {

    public String getAppointmentNO(String patientID, String date) throws Exception;

    public String generatePaymentNO() throws Exception;

    public String getPaymentTotal(String appointmentNO) throws Exception;

    public boolean savePayment(String cashierID, PaymentDTO paymentDTO) throws Exception;

}
