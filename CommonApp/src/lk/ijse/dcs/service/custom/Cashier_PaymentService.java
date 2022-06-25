package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.PaymentDTO;
import lk.ijse.dcs.service.SuperService;

public interface Cashier_PaymentService extends SuperService {

    public String getAppointmentNO(String patientID, String date) throws Exception;

    public String generatePaymentNO() throws Exception;

    public String getPaymentTotal(String appointmentNO) throws Exception;

    public boolean savePayment(String cashierID, PaymentDTO paymentDTO) throws Exception;

}
