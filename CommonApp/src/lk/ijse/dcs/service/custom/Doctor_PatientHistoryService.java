package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.service.SuperService;

import java.util.List;

public interface Doctor_PatientHistoryService extends SuperService {

    public List<AppointmentDTO> getAllPatientHistory(String patientID) throws Exception;

    public List<AppointmentDTO> searchAllPatientHistory(String patientID, String searchText) throws Exception;

    public AppointmentDTO getPatientHistory(String appointmentNO) throws Exception;

    public List<AppointmentDetailsDTO> getPatientHistoryDetails(String appointmentNO) throws Exception;

}
