package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.PatientDTO;

import java.util.List;

public interface Recep_PatientBO extends SuperBO {

    public String generateNextPatientID() throws Exception;

    public boolean savePatient(PatientDTO patientDTO) throws Exception;

    public boolean updatePatient(PatientDTO patientDTO) throws Exception;

    public boolean deletePatient(String patientID) throws Exception;

    public PatientDTO getPatient(String patientID) throws Exception;

    public List<PatientDTO> getAllPatients() throws Exception;

    public List<PatientDTO> searchPatients(String searchText) throws Exception;

}
