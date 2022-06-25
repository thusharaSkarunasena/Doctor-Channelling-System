package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Recep_AppointmentBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.entity.Appointment;
import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.entity.Patient;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Admin_EmployeeRepo;
import lk.ijse.dcs.repo.custom.Recep_AppointmentRepo;
import lk.ijse.dcs.repo.custom.Recep_PatientRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

public class Recep_AppointmentBOImpl implements Recep_AppointmentBO {

    private Recep_AppointmentRepo recepAppointmentRepo=
            (Recep_AppointmentRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.RECEP_APPOINTMENT);

    @Override
    public String generateNextAppointmentNumber() throws Exception {
        String nextAppointmentNumber=null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()) {
            recepAppointmentRepo.setMySession(session);
            session.getTransaction().begin();
            nextAppointmentNumber=recepAppointmentRepo.generateNextAppointmentNumber();
            if (session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
        }
        return nextAppointmentNumber;
    }

    @Override
    public String getNextQueueNumber(String date, String doctorID) throws Exception {
        String nextQueueNumber=null;
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            recepAppointmentRepo.setMySession(session);
            session.getTransaction().begin();
            nextQueueNumber=recepAppointmentRepo.getNextQueueNumber(date, doctorID);
            if(session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
        }
        return nextQueueNumber;
    }

    @Override
    public boolean reserveAppointment(AppointmentDTO appointmentDTO) throws Exception {
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            Recep_PatientRepo recepPatientRepo= (Recep_PatientRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.RECEP_PATIENT);
            recepPatientRepo.setMySession(session);
            Patient patient=recepPatientRepo.getPatient(appointmentDTO.getPatientID());

            Admin_EmployeeRepo adminEmployeeRepo= (Admin_EmployeeRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ADMIN_EMPLOYEE);
            adminEmployeeRepo.setMySession(session);
            Employee employee_doc=adminEmployeeRepo.getEmployee(appointmentDTO.getDoctorsID());
            Employee demo_employee=adminEmployeeRepo.getEmployee("EMP-000");

            recepAppointmentRepo.setSession(session);
            session.getTransaction().begin();
            recepAppointmentRepo.save(new Appointment(
                    appointmentDTO.getAppointmentNO(),
                    appointmentDTO.getDateAndTime(),
                    appointmentDTO.getAppointmentType(),
                    appointmentDTO.getAppointmentDate(),
                    appointmentDTO.getQueueNumber(),
                    appointmentDTO.getDetails(),
                    appointmentDTO.getOtherDescription(),
                    appointmentDTO.getDoctorsDescription(),
                    patient,
                    employee_doc,
                    demo_employee,
                    demo_employee
            ));
            if(session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
