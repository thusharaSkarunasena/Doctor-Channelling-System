package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Cashier_PaymentBO;
import lk.ijse.dcs.dto.PaymentDTO;
import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.AppointmentDetails;
import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.entity.Payment;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Admin_EmployeeRepo;
import lk.ijse.dcs.repo.custom.Cashier_PaymentRepo;
import lk.ijse.dcs.repo.custom.Recep_AppointmentRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;

public class Cashier_PaymentBOImpl implements Cashier_PaymentBO {

    private Cashier_PaymentRepo cashierPaymentRepo =
            (Cashier_PaymentRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.CASHIER_PAYMENT);

    @Override
    public String getAppointmentNO(String patientID, String date) throws Exception {
        String appointmentNO = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierPaymentRepo.setMySession(session);
            session.getTransaction().begin();
            Appointment appointment=cashierPaymentRepo.getAppointmentNO(patientID, date);

            if(!appointment.getAppointmentNO().equals("empty")){
                if(!appointment.getPharm_appointment().getEmployeeID().equals("EMP-000")){
                    if(appointment.getCashier_appointment().getEmployeeID().equals("EMP-000")){
                        appointmentNO=appointment.getAppointmentNO();
                    }else{
                        appointmentNO="empty";
                    }
                }else{
                    appointmentNO="empty";
                }
            }else{
                appointmentNO="empty";
            }

            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return appointmentNO;
    }

    @Override
    public String generatePaymentNO() throws Exception {
        String paymentNO = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierPaymentRepo.setMySession(session);
            session.getTransaction().begin();
            paymentNO = cashierPaymentRepo.generatePaymentNO();
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return paymentNO;
    }

    @Override
    public String getPaymentTotal(String appointmentNO) throws Exception {
        String paymentTotal_str = null;
        Double paymentTotal_dbl = 0.00;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierPaymentRepo.setMySession(session);
            ArrayList<AppointmentDetails> appointmentDetails = (ArrayList<AppointmentDetails>) cashierPaymentRepo.getAppointmentDetails(appointmentNO);

            for (AppointmentDetails details : appointmentDetails) {
                paymentTotal_dbl += details.getTotal();
            }
            paymentTotal_dbl += 750.00;
            paymentTotal_str = Double.toString(paymentTotal_dbl);
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return paymentTotal_str;
    }

    @Override
    public boolean savePayment(String cashierID, PaymentDTO paymentDTO) throws Exception {
        try {
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            cashierPaymentRepo.setSession(session1);
            cashierPaymentRepo.setMySession(session1);
            session1.getTransaction().begin();
            session2.getTransaction().begin();

            Admin_EmployeeRepo adminEmployeeRepo = (Admin_EmployeeRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ADMIN_EMPLOYEE);
            adminEmployeeRepo.setMySession(session2);
            Employee employee_cashier = adminEmployeeRepo.getEmployee(cashierID);

            Appointment appointment = cashierPaymentRepo.getAppointment(paymentDTO.getAppointmentNO());

            Recep_AppointmentRepo recepAppointmentRepo = (Recep_AppointmentRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.RECEP_APPOINTMENT);
            recepAppointmentRepo.setSession(session2);
            Appointment updatedAppointmnet=new Appointment(
                    appointment.getAppointmentNO(),
                    appointment.getDateAndTime(),
                    appointment.getAppointmentType(),
                    appointment.getAppointmentDate(),
                    appointment.getQueueNumber(),
                    appointment.getDetails(),
                    appointment.getOtherDescription(),
                    appointment.getDoctorsDescription(),
                    appointment.getPatient(),
                    appointment.getDoctor_appointment(),
                    appointment.getPharm_appointment(),
                    employee_cashier
            );

            recepAppointmentRepo.update(updatedAppointmnet);

            session2.save(new Payment(
                paymentDTO.getPaymentNO(),
                    paymentDTO.getPaymentType(),
                    paymentDTO.getTotal(),
                    paymentDTO.getDiscount(),
                    paymentDTO.getNetTotal(),
                    updatedAppointmnet
            ));

            if (session1.getTransaction().isActive()) {
                session1.getTransaction().commit();
            }
            if (session2.getTransaction().isActive()) {
                session2.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
