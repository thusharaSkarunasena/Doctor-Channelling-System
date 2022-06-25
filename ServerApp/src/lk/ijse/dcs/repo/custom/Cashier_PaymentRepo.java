package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.AppointmentDetails;
import lk.ijse.dcs.entity.Payment;
import lk.ijse.dcs.repo.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface Cashier_PaymentRepo extends CrudRepository<Payment, String> {

    public void  setMySession(Session mySession) throws Exception;

    public String generatePaymentNO() throws Exception;

    public Appointment getAppointmentNO(String patientID, String date) throws Exception;

    public Appointment getAppointment(String appointmentNO) throws Exception;

    public List<AppointmentDetails> getAppointmentDetails(String appointmentNO) throws Exception;


}
