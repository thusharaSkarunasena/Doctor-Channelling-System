package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.AppointmentDetails;
import lk.ijse.dcs.repo.SuperRepository;

import java.util.List;

public interface Pharm_IssueDrugsRepo extends SuperRepository {

    public Appointment getAppointment(String patientID, String date) throws Exception;

    public Appointment getAppointment(String appointmentNO) throws Exception;

    public List<AppointmentDetails> getAppointmentDetails(String appointmentNO) throws Exception;


}
