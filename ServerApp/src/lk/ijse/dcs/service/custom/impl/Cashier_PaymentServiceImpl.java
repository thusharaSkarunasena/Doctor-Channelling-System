package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Cashier_PaymentBO;
import lk.ijse.dcs.dto.PaymentDTO;
import lk.ijse.dcs.service.custom.Cashier_PaymentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Cashier_PaymentServiceImpl extends UnicastRemoteObject implements Cashier_PaymentService {

    private Cashier_PaymentBO cashierPaymentBO =
            (Cashier_PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CASHIER_PAYMENT);

    public Cashier_PaymentServiceImpl() throws RemoteException {
    }

    @Override
    public String getAppointmentNO(String patientID, String date) throws Exception {
        return cashierPaymentBO.getAppointmentNO(patientID, date);
    }

    @Override
    public String generatePaymentNO() throws Exception {
        return cashierPaymentBO.generatePaymentNO();
    }

    @Override
    public String getPaymentTotal(String appointmentNO) throws Exception {
        return cashierPaymentBO.getPaymentTotal(appointmentNO);
    }

    @Override
    public boolean savePayment(String cashierID, PaymentDTO paymentDTO) throws Exception {
        return cashierPaymentBO.savePayment(cashierID, paymentDTO);
    }
}
