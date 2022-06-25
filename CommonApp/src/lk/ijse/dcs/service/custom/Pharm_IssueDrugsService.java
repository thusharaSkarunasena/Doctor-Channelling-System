package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.service.SuperService;

import java.util.List;

public interface Pharm_IssueDrugsService extends SuperService {

    public AppointmentDTO getAppointment(String patientID, String date) throws Exception;

    public List<AppointmentDetailsDTO> getAppointmentDetail(String appointmentNO) throws Exception;

    public boolean updateAppointmentAndDetails(String pharmacistID, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception;

}
