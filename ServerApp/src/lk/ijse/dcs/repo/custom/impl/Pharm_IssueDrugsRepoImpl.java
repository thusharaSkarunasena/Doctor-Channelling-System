package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.AppointmentDetails;
import lk.ijse.dcs.repo.custom.Pharm_IssueDrugsRepo;
import org.hibernate.Session;

import java.util.List;

public class Pharm_IssueDrugsRepoImpl implements Pharm_IssueDrugsRepo {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public Appointment getAppointment(String patientID, String date) throws Exception {
        List<Appointment> appointments=session.createQuery("from Appointment where patientID = :pntID " +
                "and appointmentDate = :apntmntID")
                .setParameter("pntID",patientID)
                .setParameter("apntmntID",date).list();

        Appointment appointment=null;
        for (Appointment apntmnt:appointments){
            appointment=apntmnt;
        }
        if (appointment==null){
            appointment=new Appointment();
            appointment.setAppointmentNO("emptySet");
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
        List<AppointmentDetails> appointmentDetails=session.createQuery("from AppointmentDetails" +
                " where appointmentNO = :apntNO").setParameter("apntNO",appointmentNO).list();
        return appointmentDetails;
    }
}
