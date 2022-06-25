package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.DrugPackDetails;
import lk.ijse.dcs.repo.CrudRepositoryImpl;
import lk.ijse.dcs.repo.custom.Doctor_PatientTreatmentRepo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Doctor_PatientTreatmentRepoImpl extends CrudRepositoryImpl<Appointment, String> implements Doctor_PatientTreatmentRepo {

    private Session session;

    @Override
    public void setMySession(Session mySession) throws Exception {
        this.session = mySession;
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
    public List<DrugPackDetails> getDrugPackDetail(String drugPackCode) throws Exception {
        List<DrugPackDetails> drugPackDetails=session.createQuery("from DrugPackDetails where drugPackCode = :dpc")
                .setParameter("dpc",drugPackCode).list();
        return drugPackDetails;
    }
}
