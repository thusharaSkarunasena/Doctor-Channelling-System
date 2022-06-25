package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.repo.CrudRepository;
import lk.ijse.dcs.repo.SuperRepository;
import org.hibernate.Session;

public interface Recep_AppointmentRepo extends CrudRepository<Appointment, String>,  SuperRepository {

    public void setMySession(Session mySession) throws Exception;

    public String generateNextAppointmentNumber() throws Exception;

    public String getNextQueueNumber(String date, String doctorID) throws Exception;

}
