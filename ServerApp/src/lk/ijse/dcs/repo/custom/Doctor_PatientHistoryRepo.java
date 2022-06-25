package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.repo.SuperRepository;

import java.util.List;

public interface Doctor_PatientHistoryRepo extends SuperRepository {

    public List<Appointment> getAllPatientHistory(String patientID) throws Exception;

    public Appointment getPatientHistory(String appointmentNO) throws Exception;

    public List<Appointment> searchAllPatientHistory(String patientID, String searchText) throws Exception;

}
