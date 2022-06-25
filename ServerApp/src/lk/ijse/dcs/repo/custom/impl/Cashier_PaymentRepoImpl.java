package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.AppointmentDetails;
import lk.ijse.dcs.entity.Payment;
import lk.ijse.dcs.repo.CrudRepositoryImpl;
import lk.ijse.dcs.repo.custom.Cashier_PaymentRepo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Cashier_PaymentRepoImpl extends CrudRepositoryImpl<Payment, String> implements Cashier_PaymentRepo {

    private Session session;

    @Override
    public void setMySession(Session mySession) throws Exception {
        this.session=mySession;
    }

    @Override
    public String generatePaymentNO() throws Exception {
        String lastPaymentNO = null;
        Integer NpartDCount = 0;
        ArrayList<String> NOs = (ArrayList<String>) session.createNativeQuery("select paymentNO from Payment " +
                "order by 1 desc limit 1").list();

        for (String lastNO : NOs) {
            lastPaymentNO = lastNO;
        }
        session.getTransaction().commit();

        if (lastPaymentNO != null) {

            String[] output = lastPaymentNO.split("-");

            Integer Npart = Integer.parseInt(output[1]);
            Npart = Npart + 1;
            Integer testNpart = Npart;


            while (testNpart != 0) {
                testNpart = testNpart / 10;
                NpartDCount++;
            }

            String nextPaymentNO = "PAY-";

            Integer rounds = 8 - NpartDCount;

            while (rounds != 0) {
                nextPaymentNO = nextPaymentNO + "0";
                rounds--;
            }

            nextPaymentNO = nextPaymentNO + "" + Npart;

            return nextPaymentNO;
        } else {
            return "PAY-00000001";
        }
    }

    @Override
    public Appointment getAppointmentNO(String patientID, String date) throws Exception {

        List<Appointment> appointments=session.createQuery("from Appointment where patientID = :pntID and appointmentDate = :apntmntID").setParameter("pntID",patientID).setParameter("apntmntID",date).list();

        Appointment appointment=null;
        for (Appointment apntmnt:appointments){
            appointment=apntmnt;
        }
        if (appointment==null){
            appointment=new Appointment();
            appointment.setAppointmentNO("empty");
        }
        return appointment;
    }

    @Override
    public Appointment getAppointment(String appointmentNO) throws Exception {
        List<Appointment> appointments=session.createQuery("from Appointment where appointmentNO = :apntNO")
                .setParameter("apntNO",appointmentNO).list();

        Appointment appointment=null;
        for (Appointment apntmnt:appointments){
            appointment=apntmnt;
        }
        return appointment;
    }

    @Override
    public List<AppointmentDetails> getAppointmentDetails(String appointmentNO) throws Exception {
        List<AppointmentDetails> appointmentDetails=session.createQuery("from AppointmentDetails " +
                "where appointmentNO = :apntNO").setParameter("apntNO",appointmentNO).list();
        return appointmentDetails;
    }
}
