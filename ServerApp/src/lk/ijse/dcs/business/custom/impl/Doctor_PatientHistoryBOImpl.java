package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Doctor_PatientHistoryBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.AppointmentDetails;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Doctor_AppointmentDetailsRepo;
import lk.ijse.dcs.repo.custom.Doctor_PatientHistoryRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Doctor_PatientHistoryBOImpl implements Doctor_PatientHistoryBO {

    private Doctor_PatientHistoryRepo doctorPatientHistoryRepo =
            (Doctor_PatientHistoryRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DOCTOR_PATIENTHISTORY);

    @Override
    public List<AppointmentDTO> getAllPatientHistory(String patientID) throws Exception {
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorPatientHistoryRepo.setSession(session);
            session.getTransaction().begin();
            List<Appointment> appointments = doctorPatientHistoryRepo.getAllPatientHistory(patientID);
            for (Appointment appointment : appointments) {
                if ((appointment.getPatient().getPatientID().equals(patientID) && (!appointment.getDoctorsDescription().equals("null_doctorsDescription_null")))) {
                    appointmentDTOS.add(new AppointmentDTO(
                            appointment.getAppointmentNO(),
                            appointment.getPatient().getPatientID(),
                            appointment.getDateAndTime(),
                            appointment.getAppointmentDate(),
                            appointment.getAppointmentType(),
                            appointment.getDoctor_appointment().getEmployeeID(),
                            appointment.getPharm_appointment().getEmployeeID(),
                            appointment.getCashier_appointment().getEmployeeID(),
                            appointment.getDetails(),
                            appointment.getOtherDescription(),
                            appointment.getQueueNumber(),
                            appointment.getDoctorsDescription()
                    ));
                }
            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return appointmentDTOS;
    }

    @Override
    public List<AppointmentDTO> searchAllPatientHistory(String patientID, String searchText) throws Exception {
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorPatientHistoryRepo.setSession(session);
            session.getTransaction().begin();
            List<Appointment> appointments = doctorPatientHistoryRepo.searchAllPatientHistory(patientID, searchText);
            for (Appointment appointment : appointments) {
                if ((appointment.getPatient().getPatientID().equals(patientID) && (!appointment.getDoctorsDescription().equals("null_doctorsDescription_null")))) {
                    appointmentDTOS.add(new AppointmentDTO(
                            appointment.getAppointmentNO(),
                            appointment.getPatient().getPatientID(),
                            appointment.getDateAndTime(),
                            appointment.getAppointmentDate(),
                            appointment.getAppointmentType(),
                            appointment.getDoctor_appointment().getEmployeeID(),
                            appointment.getPharm_appointment().getEmployeeID(),
                            appointment.getCashier_appointment().getEmployeeID(),
                            appointment.getDetails(),
                            appointment.getOtherDescription(),
                            appointment.getQueueNumber(),
                            appointment.getDoctorsDescription()
                    ));
                }
            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return appointmentDTOS;
    }

    @Override
    public AppointmentDTO getPatientHistory(String appointmentNO) throws Exception {
        AppointmentDTO appointmentDTO = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorPatientHistoryRepo.setSession(session);
            session.getTransaction().begin();

            Appointment appointment = doctorPatientHistoryRepo.getPatientHistory(appointmentNO);

            appointmentDTO = new AppointmentDTO(
                    appointment.getAppointmentNO(),
                    appointment.getPatient().getPatientID(),
                    appointment.getDateAndTime(),
                    appointment.getAppointmentDate(),
                    appointment.getAppointmentType(),
                    appointment.getDoctor_appointment().getEmployeeID(),
                    appointment.getPharm_appointment().getEmployeeID(),
                    appointment.getCashier_appointment().getEmployeeID(),
                    appointment.getDetails(),
                    appointment.getOtherDescription(),
                    appointment.getQueueNumber(),
                    appointment.getDoctorsDescription()
            );

            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return appointmentDTO;
    }

    @Override
    public List<AppointmentDetailsDTO> getPatientHistoryDetails(String appointmentNO) throws Exception {
        List<AppointmentDetailsDTO> appointmentDetailsDTOS=new ArrayList<>();
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            Doctor_AppointmentDetailsRepo doctorAppointmentDetailsRepo= (Doctor_AppointmentDetailsRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DOCTOR_APPOINTMENTDETAILS);
            doctorAppointmentDetailsRepo.setSession(session);
            ArrayList<AppointmentDetails> appointmentDetails= (ArrayList<AppointmentDetails>) doctorAppointmentDetailsRepo.getAll();

            for(AppointmentDetails details:appointmentDetails){
                if(details.getAppointmentDetails_fk().getAppointmentNO().equals(appointmentNO)){
                    appointmentDetailsDTOS.add(new AppointmentDetailsDTO(
                            details.getAppointmentDetails_fk().getAppointmentNO(),
                            details.getAppointmentDetails_fk().getDrugCode(),
                            "DRGPK-00000",
                            details.getDescription(),
                            details.getQty(),
                            details.getTotal()
                    ));
                }
            }
        }
        return appointmentDetailsDTOS;
    }

}
