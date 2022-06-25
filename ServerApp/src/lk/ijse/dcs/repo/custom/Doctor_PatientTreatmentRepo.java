package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.DrugPackDetails;
import lk.ijse.dcs.repo.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface Doctor_PatientTreatmentRepo extends CrudRepository<Appointment, String> {

    public void setMySession(Session mySession) throws Exception;

    public Appointment getAppointment(String appointmentNO) throws Exception;

    public List<DrugPackDetails> getDrugPackDetail(String drugPackCode) throws Exception;

}
