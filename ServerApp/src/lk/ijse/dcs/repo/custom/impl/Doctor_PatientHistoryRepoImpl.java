package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.repo.custom.Doctor_PatientHistoryRepo;
import org.hibernate.Session;

import java.util.List;

public class Doctor_PatientHistoryRepoImpl implements Doctor_PatientHistoryRepo {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
    }

    @Override
    public List<Appointment> getAllPatientHistory(String patientID) throws Exception {
        return session.createQuery("from Appointment where patientID= : pntID")
                .setParameter("pntID", patientID).list();
    }

    @Override
    public Appointment getPatientHistory(String appointmentNO) throws Exception {
        List<Appointment> appointments=session.createQuery("from Appointment").list();
        Appointment appointment=null;
        for(Appointment apntmnt:appointments){
            if(apntmnt.getAppointmentNO().equals(appointmentNO)){
                appointment=apntmnt;
                break;
            }
        }
        return appointment;
    }

    @Override
    public List<Appointment> searchAllPatientHistory(String patientID, String searchText) throws Exception {
        String newSearchText="%"+searchText+"%";
        return session.createQuery("from Appointment where patientID = :pntID and appointmentNO like :searchText " +
                "or appointmentDate like :searchText or doctorsID like :searchText")
                .setParameter("pntID", patientID).setParameter("searchText", newSearchText).list();

    }
}
