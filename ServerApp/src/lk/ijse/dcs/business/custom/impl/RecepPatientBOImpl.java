package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Recep_PatientBO;
import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.entity.Patient;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Recep_PatientRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class RecepPatientBOImpl implements Recep_PatientBO {

    private Recep_PatientRepo patientRepo =
            (Recep_PatientRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.RECEP_PATIENT);

    @Override
    public String generateNextPatientID() throws Exception {
        String nextID = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            patientRepo.setMySession(session);
            session.getTransaction().begin();
            nextID = patientRepo.generateNextPatientID();
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return nextID;
    }

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            patientRepo.setSession(session);
            session.getTransaction().begin();
            patientRepo.save(new Patient(
                    patientDTO.getPatientID(),
                    patientDTO.getName(),
                    patientDTO.getGender(),
                    patientDTO.getDob(),
                    patientDTO.getAddress(),
                    patientDTO.getNic(),
                    patientDTO.getCn_home(),
                    patientDTO.getCn_mobile()
            ));
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            patientRepo.setSession(session);
            session.getTransaction().begin();
            patientRepo.update(new Patient(
                    patientDTO.getPatientID(),
                    patientDTO.getName(),
                    patientDTO.getGender(),
                    patientDTO.getDob(),
                    patientDTO.getAddress(),
                    patientDTO.getNic(),
                    patientDTO.getCn_home(),
                    patientDTO.getCn_mobile()
            ));
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletePatient(String patientID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            patientRepo.setSession(session);
            patientRepo.setMySession(session);
            session.getTransaction().begin();
            Patient patient=patientRepo.getPatient(patientID);
            patientRepo.delete(patient);
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public PatientDTO getPatient(String patientID) throws Exception {
        Patient patient = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            patientRepo.setMySession(session);
            session.getTransaction().begin();
            patient = patientRepo.getPatient(patientID);
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return new PatientDTO(
                patient.getPatientID(),
                patient.getName(),
                patient.getGender(),
                patient.getDob(),
                patient.getAddress(),
                patient.getNic(),
                patient.getCn_home(),
                patient.getCn_mobile()
        );
    }

    @Override
    public List<PatientDTO> getAllPatients() throws Exception {
        List<Patient> patients = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            patientRepo.setSession(session);
            session.getTransaction().begin();
            patients = patientRepo.getAll();
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }

        List<PatientDTO> patientDTOS = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(
                    patient.getPatientID(),
                    patient.getName(),
                    patient.getGender(),
                    patient.getDob(),
                    patient.getAddress(),
                    patient.getNic(),
                    patient.getCn_home(),
                    patient.getCn_mobile()
            ));
        }
        return patientDTOS;
    }

    @Override
    public List<PatientDTO> searchPatients(String searchText) throws Exception {
        List<Patient> patients = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            patientRepo.setMySession(session);
            session.getTransaction().begin();
            patients = patientRepo.searchPatients(searchText);
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }

        List<PatientDTO> patientDTOS = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(
                    patient.getPatientID(),
                    patient.getName(),
                    patient.getGender(),
                    patient.getDob(),
                    patient.getAddress(),
                    patient.getNic(),
                    patient.getCn_home(),
                    patient.getCn_mobile()
            ));
        }
        return patientDTOS;
    }

}
