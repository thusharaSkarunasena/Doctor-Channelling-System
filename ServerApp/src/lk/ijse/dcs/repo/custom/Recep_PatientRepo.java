package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Patient;
import lk.ijse.dcs.repo.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface Recep_PatientRepo extends CrudRepository<Patient, String> {

    public void setMySession(Session mySession) throws Exception;

    public String generateNextPatientID() throws Exception;

    public Patient getPatient(String patientID) throws Exception;

    public List<Patient> searchPatients(String searchText) throws Exception;

}
