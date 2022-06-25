package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;

import java.util.List;

public interface Doctor_PatientTreatmentBO extends SuperBO {

    public String getReservedAppointmentNO(String currentDate, String EmployeeID, String patientID) throws Exception;

    public boolean saveAppointmentAndDetails(String appointmentNO, String doctorsDescription, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception;
}
