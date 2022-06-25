package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.AppointmentDTO;

public interface Recep_AppointmentBO  extends SuperBO {

    public String generateNextAppointmentNumber() throws Exception;

    public String getNextQueueNumber(String date, String doctorID) throws Exception;

    public boolean reserveAppointment(AppointmentDTO appointmentDTO) throws Exception;

}
