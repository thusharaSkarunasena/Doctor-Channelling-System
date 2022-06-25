package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.service.SuperService;

public interface Recep_AppointmentService extends SuperService {

    public String generateNextAppointmentNumber() throws Exception;

    public String getNextQueueNumber(String date, String doctorID) throws Exception;

    public boolean reserveAppointment(AppointmentDTO appointmentDTO) throws Exception;

}
