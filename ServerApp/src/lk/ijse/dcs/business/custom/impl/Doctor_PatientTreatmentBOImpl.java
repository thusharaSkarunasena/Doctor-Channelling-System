package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Doctor_PatientTreatmentBO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.AppointmentDetails;
import lk.ijse.dcs.entity.AppointmentDetails_FK;
import lk.ijse.dcs.entity.DrugPackDetails;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Doctor_AppointmentDetailsRepo;
import lk.ijse.dcs.repo.custom.Doctor_PatientTreatmentRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class Doctor_PatientTreatmentBOImpl implements Doctor_PatientTreatmentBO {

    private Doctor_PatientTreatmentRepo doctorPatientTreatmentRepo =
            (Doctor_PatientTreatmentRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DOCTOR_PATIENTTREATMENT);

    private Doctor_AppointmentDetailsRepo doctorAppointmentDetailsRepo =
            (Doctor_AppointmentDetailsRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DOCTOR_APPOINTMENTDETAILS);

    @Override
    public String getReservedAppointmentNO(String currentDate, String EmployeeID, String patientID) throws Exception {
        String reservedAppointmentNO = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorPatientTreatmentRepo.setSession(session);
            session.getTransaction().begin();
            List<Appointment> appointments = doctorPatientTreatmentRepo.getAll();
            for (Appointment appointment : appointments) {
                if ((appointment.getAppointmentDate().equals(currentDate)) &&
                        (appointment.getDoctor_appointment().getEmployeeID().equals(EmployeeID)) &&
                        (appointment.getPatient().getPatientID().equals(patientID)) &&
                        (appointment.getDoctorsDescription().equals("null_doctorsDescription_null"))) {
                    reservedAppointmentNO = appointment.getAppointmentNO();
                    break;
                }
            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return reservedAppointmentNO;
    }

    @Override
    public boolean saveAppointmentAndDetails(String appointmentNO, String doctorsDescription, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception {
        try {
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            Session session3 = HibernateUtil.getSessionFactory().openSession();

            session1.getTransaction().begin();
            session2.getTransaction().begin();
            session3.getTransaction().begin();

            doctorPatientTreatmentRepo.setSession(session1);
            doctorPatientTreatmentRepo.setMySession(session1);

            Appointment appointment = doctorPatientTreatmentRepo.getAppointment(appointmentNO);


            doctorPatientTreatmentRepo.setSession(session2);
            doctorPatientTreatmentRepo.setMySession(session2);

            Appointment updatedAppointment = new Appointment(
                    appointment.getAppointmentNO(),
                    appointment.getDateAndTime(),
                    appointment.getAppointmentType(),
                    appointment.getAppointmentDate(),
                    appointment.getQueueNumber(),
                    appointment.getDetails(),
                    appointment.getOtherDescription(),
                    doctorsDescription,
                    appointment.getPatient(),
                    appointment.getDoctor_appointment(),
                    appointment.getPharm_appointment(),
                    appointment.getCashier_appointment()
            );
            doctorPatientTreatmentRepo.update(updatedAppointment);

            doctorPatientTreatmentRepo.setSession(session3);
            doctorPatientTreatmentRepo.setMySession(session3);
            doctorAppointmentDetailsRepo.setSession(session3);


            for (AppointmentDetailsDTO detailsDTO : appointmentDetailsDTOS) {
                if (detailsDTO.getDrugCode().equals("DRG-00000")) {
                    List<DrugPackDetails> drugPackDetails = doctorPatientTreatmentRepo.getDrugPackDetail(detailsDTO.getDrugPackCode());
                    for (DrugPackDetails packDetails : drugPackDetails) {
                        doctorAppointmentDetailsRepo.save(new AppointmentDetails(
                                new AppointmentDetails_FK(
                                        detailsDTO.getAppointmentNO(),
                                        packDetails.getDrugPackDetails_fk().getDrugCode()
                                ),
                                detailsDTO.getDescription(),
                                detailsDTO.getQty(),
                                detailsDTO.getTotal()
                        ));
                    }
                } else {
                    doctorAppointmentDetailsRepo.save(new AppointmentDetails(
                            new AppointmentDetails_FK(
                                    detailsDTO.getAppointmentNO(),
                                    detailsDTO.getDrugCode()
                            ),
                            detailsDTO.getDescription(),
                            detailsDTO.getQty(),
                            detailsDTO.getTotal()
                    ));
                }
            }

            if (session1.getTransaction().isActive()) {
                session1.getTransaction().commit();
            }
            if (session2.getTransaction().isActive()) {
                session2.getTransaction().commit();
            }
            if (session3.getTransaction().isActive()) {
                session3.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
