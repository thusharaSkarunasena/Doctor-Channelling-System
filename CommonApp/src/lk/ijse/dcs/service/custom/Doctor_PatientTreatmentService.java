package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.service.SuperService;

import java.util.List;

public interface Doctor_PatientTreatmentService extends SuperService {

    public String getReservedAppointmentNO(String currentDate, String EmployeeID, String patientID) throws Exception;

    public boolean saveAppointmentAndDetails(String appointmentNO, String doctorsDescription, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception;

}
