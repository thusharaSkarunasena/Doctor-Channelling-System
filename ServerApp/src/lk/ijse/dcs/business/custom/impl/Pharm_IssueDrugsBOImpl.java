package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Pharm_IssueDrugsBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.entity.*;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.*;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Pharm_IssueDrugsBOImpl implements Pharm_IssueDrugsBO {

    Pharm_IssueDrugsRepo pharmIssueDrugsRepo =
            (Pharm_IssueDrugsRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.PHARM_ISSUEDRUGS);

    @Override
    public AppointmentDTO getAppointment(String patientID, String date) throws Exception {
        AppointmentDTO appointmentDTO = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmIssueDrugsRepo.setSession(session);
            session.getTransaction().begin();
            Appointment appointment = pharmIssueDrugsRepo.getAppointment(patientID, date);

            if ((!appointment.getAppointmentNO().equals("emptySet")) && (appointment.getPharm_appointment().getEmployeeID().equals("EMP-000"))) {
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
            } else {
                appointmentDTO = new AppointmentDTO();
                appointmentDTO.setAppointmentNO("emptySet");
            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return appointmentDTO;
    }

    @Override
    public List<AppointmentDetailsDTO> getAppointmentDetail(String appointmentNO) throws Exception {
        List<AppointmentDetailsDTO> appointmentDetailsDTOS = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmIssueDrugsRepo.setSession(session);
            ArrayList<AppointmentDetails> appointmentDetails = (ArrayList<AppointmentDetails>) pharmIssueDrugsRepo.getAppointmentDetails(appointmentNO);

            for (AppointmentDetails details : appointmentDetails) {

                    appointmentDetailsDTOS.add(new AppointmentDetailsDTO(
                            details.getAppointmentDetails_fk().getAppointmentNO(),
                            details.getAppointmentDetails_fk().getDrugCode(),
                            "DRGPK-00000",
                            details.getDescription(),
                            details.getQty(),
                            details.getTotal()
                    ));

            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return appointmentDetailsDTOS;
    }

    @Override
    public boolean updateAppointmentAndDetails(String pharmacistID, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmIssueDrugsRepo.setSession(session);
            session.getTransaction().begin();

            Doctor_AppointmentDetailsRepo doctorAppointmentDetailsRepo = (Doctor_AppointmentDetailsRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DOCTOR_APPOINTMENTDETAILS);
            doctorAppointmentDetailsRepo.setSession(session);
            ArrayList<AppointmentDetails> appointmentDetails = (ArrayList<AppointmentDetails>) doctorAppointmentDetailsRepo.getAll();

            for (AppointmentDetails details : appointmentDetails) {
                if (details.getAppointmentDetails_fk().getAppointmentNO().equals(appointmentDetailsDTOS.get(0).getAppointmentNO())) {
                    doctorAppointmentDetailsRepo.delete(details);
                }
            }

            Admin_EmployeeRepo adminEmployeeRepo = (Admin_EmployeeRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ADMIN_EMPLOYEE);
            adminEmployeeRepo.setMySession(session);
            Employee employee_pharm = adminEmployeeRepo.getEmployee(pharmacistID);

            Appointment appointment = pharmIssueDrugsRepo.getAppointment(appointmentDetailsDTOS.get(0).getAppointmentNO());

            Recep_AppointmentRepo recepAppointmentRepo = (Recep_AppointmentRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.RECEP_APPOINTMENT);
            recepAppointmentRepo.setSession(session);
            recepAppointmentRepo.delete(appointment);

            recepAppointmentRepo.save(new Appointment(
                    appointment.getAppointmentNO(),
                    appointment.getDateAndTime(),
                    appointment.getAppointmentType(),
                    appointment.getAppointmentDate(),
                    appointment.getQueueNumber(),
                    appointment.getDetails(),
                    appointment.getOtherDescription(),
                    appointment.getDoctorsDescription(),
                    appointment.getPatient(),
                    appointment.getDoctor_appointment(),
                    employee_pharm,
                    appointment.getCashier_appointment()
            ));

            for (AppointmentDetailsDTO detailsDTO : appointmentDetailsDTOS) {
                Pharm_DrugRepo pharmDrugRepo = (Pharm_DrugRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.PHARM_DRUG);

                pharmDrugRepo.setMySession(session);
                Drug drug = pharmDrugRepo.getDrug(detailsDTO.getDrugCode());

                double up = drug.getUnitPrice();
                double tot = up * detailsDTO.getQty();

                doctorAppointmentDetailsRepo.save(new AppointmentDetails(
                        new AppointmentDetails_FK(
                                detailsDTO.getAppointmentNO(),
                                detailsDTO.getDrugCode()
                        ),
                        detailsDTO.getDescription(),
                        detailsDTO.getQty(),
                        tot
                ));
            }

            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
