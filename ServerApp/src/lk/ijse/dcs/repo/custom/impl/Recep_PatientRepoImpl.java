package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Patient;
import lk.ijse.dcs.repo.CrudRepositoryImpl;
import lk.ijse.dcs.repo.custom.Recep_PatientRepo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Recep_PatientRepoImpl extends CrudRepositoryImpl<Patient, String> implements Recep_PatientRepo {

    private Session session;

    @Override
    public void setMySession(Session mySession) throws Exception {
        this.session=mySession;
    }

    @Override
    public String generateNextPatientID() throws Exception {
        String lastPatientID = null;
        Integer NpartDCount = 0;
        ArrayList<String> ids = (ArrayList<String>) session.createNativeQuery("select patientID from Patient " +
                "order by 1 desc limit 1").list();

        for (String lastID : ids) {
            lastPatientID = lastID;
        }
        session.getTransaction().commit();

        if (lastPatientID != null) {

            String[] output = lastPatientID.split("-");

            Integer Npart = Integer.parseInt(output[1]);
            Npart = Npart + 1;
            Integer testNpart = Npart;


            while (testNpart != 0) {
                testNpart = testNpart / 10;
                NpartDCount++;
            }

            String nextPatientId = "PNT-";

            Integer rounds = 5 - NpartDCount;

            while (rounds != 0) {
                nextPatientId = nextPatientId + "0";
                rounds--;
            }

            nextPatientId = nextPatientId + "" + Npart;

            return nextPatientId;
        } else {
            return "PNT-00001";
        }
    }

    @Override
    public Patient getPatient(String patientID) throws Exception {
        Patient patient=null;
        ArrayList<Patient> patients = (ArrayList<Patient>) session.createQuery("from Patient").list();
        boolean isExist=false;
        for(Patient pnt:patients){
            if (pnt.getPatientID().equals(patientID)){
                isExist=true;
                patient=pnt;
                break;
            }
        }
        return patient;
    }

    @Override
    public List<Patient> searchPatients(String searchText) throws Exception {
        String newSearchText="%"+searchText+"%";
        return session.createQuery("from Patient where patientID like :searchText " +
                "or name like :searchText  or dob like :searchText or gender like :searchText or nic like :searchText")
                .setParameter("searchText", newSearchText).list();
    }

}
