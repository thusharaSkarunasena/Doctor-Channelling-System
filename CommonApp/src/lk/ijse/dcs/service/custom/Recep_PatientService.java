package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.observer.Observable;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.reservation.Reservation;
import lk.ijse.dcs.service.SuperService;

import java.util.List;

public interface Recep_PatientService extends SuperService, Observable, Reservation {

    public String generateNextPatientID() throws Exception;

    public boolean savePatient(Observer observer, PatientDTO patientDTO) throws Exception;

    public boolean updatePatient(Observer observer, PatientDTO patientDTO) throws Exception;

    public boolean deletePatient(Observer observer, String patientID) throws Exception;

    public PatientDTO getPatient(String patientID) throws Exception;

    public List<PatientDTO> getAllPatients() throws Exception;

    public List<PatientDTO> searchPatients(String searchText) throws Exception;

}
