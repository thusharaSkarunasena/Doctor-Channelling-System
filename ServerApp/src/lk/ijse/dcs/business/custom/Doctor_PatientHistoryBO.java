package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;

import java.util.List;

public interface Doctor_PatientHistoryBO extends SuperBO {

    public List<AppointmentDTO> getAllPatientHistory(String patientID) throws Exception;

    public List<AppointmentDTO> searchAllPatientHistory(String patientID, String searchText) throws Exception;

    public AppointmentDTO getPatientHistory(String appointmentNO) throws Exception;

    public List<AppointmentDetailsDTO> getPatientHistoryDetails(String appointmentNO) throws Exception;

}
