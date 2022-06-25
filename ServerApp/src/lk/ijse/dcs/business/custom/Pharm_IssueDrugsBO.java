package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;

import java.util.List;

public interface Pharm_IssueDrugsBO extends SuperBO {

    public AppointmentDTO getAppointment(String patientID, String date) throws Exception;

    public List<AppointmentDetailsDTO> getAppointmentDetail(String appointmentNO) throws Exception;

    public boolean updateAppointmentAndDetails(String pharmacistID, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception;

}
