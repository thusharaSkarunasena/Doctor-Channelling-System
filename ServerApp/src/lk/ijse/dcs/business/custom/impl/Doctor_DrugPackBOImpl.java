package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Doctor_DrugPackBO;
import lk.ijse.dcs.dto.DrugPackDTO;
import lk.ijse.dcs.dto.DrugPackDetailsDTO;
import lk.ijse.dcs.entity.DrugPack;
import lk.ijse.dcs.entity.DrugPackDetails;
import lk.ijse.dcs.entity.DrugPackDetails_FK;
import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Admin_EmployeeRepo;
import lk.ijse.dcs.repo.custom.Doctor_DrugPackDetailsRepo;
import lk.ijse.dcs.repo.custom.Doctor_DrugPackRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Doctor_DrugPackBOImpl implements Doctor_DrugPackBO {

    private Doctor_DrugPackRepo doctorDrugPackRepo =
            (Doctor_DrugPackRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DOCTOR_DRUGPACK);
    private Doctor_DrugPackDetailsRepo doctorDrugPackDetailsRepo =
            (Doctor_DrugPackDetailsRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DOCTOR_DRUGPACKDETAILS);

    @Override
    public String generateNextDrugPackCode() throws Exception {
        String nextDrugPackCode = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorDrugPackRepo.setMySession(session);
            session.getTransaction().begin();
            nextDrugPackCode = doctorDrugPackRepo.generateNextDrugPackCode();
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return nextDrugPackCode;
    }

    @Override
    public boolean saveDrugPack(DrugPackDTO drugPackDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorDrugPackRepo.setSession(session);
            doctorDrugPackDetailsRepo.setSession(session);
            session.getTransaction().begin();

            Admin_EmployeeRepo adminEmployeeRepo = (Admin_EmployeeRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ADMIN_EMPLOYEE);
            adminEmployeeRepo.setMySession(session);
            Employee employee = adminEmployeeRepo.getEmployee(drugPackDTO.getEmployeeID());

            DrugPack drugPack = new DrugPack(
                    drugPackDTO.getDrugPackCode(),
                    drugPackDTO.getName(),
                    employee
            );
            doctorDrugPackRepo.save(drugPack);

            List<DrugPackDetailsDTO> drugPackDetailsDTOS = drugPackDTO.getDrugPackDetails();
            DrugPackDetails drugPackDetails = null;

            for (DrugPackDetailsDTO packDetailsDTO : drugPackDetailsDTOS) {
                drugPackDetails = new DrugPackDetails(
                        new DrugPackDetails_FK(
                                drugPackDTO.getDrugPackCode(),
                                packDetailsDTO.getDrugCode()
                        )
                );
                doctorDrugPackDetailsRepo.save(drugPackDetails);
            }

            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDrugPack(DrugPackDTO drugPackDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorDrugPackRepo.setSession(session);
            doctorDrugPackDetailsRepo.setSession(session);
            session.getTransaction().begin();

            Admin_EmployeeRepo adminEmployeeRepo = (Admin_EmployeeRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ADMIN_EMPLOYEE);
            adminEmployeeRepo.setMySession(session);
            Employee employee = adminEmployeeRepo.getEmployee(drugPackDTO.getEmployeeID());

            DrugPack drugPack = new DrugPack(
                    drugPackDTO.getDrugPackCode(),
                    drugPackDTO.getName(),
                    employee
            );
            doctorDrugPackRepo.update(drugPack);

            ArrayList<DrugPackDetails> oldDrugPackDetails = (ArrayList<DrugPackDetails>) doctorDrugPackDetailsRepo.getAll();
            for (DrugPackDetails drugPackDetails : oldDrugPackDetails) {
                if (drugPackDetails.getDrugPackDetails_fk().getDrugPackCode().equals(drugPackDTO.getDrugPackCode())) {
                    doctorDrugPackDetailsRepo.delete(drugPackDetails);
                }
            }

            List<DrugPackDetailsDTO> drugPackDetailsDTOS = drugPackDTO.getDrugPackDetails();
            DrugPackDetails drugPackDetails = null;

            for (DrugPackDetailsDTO packDetailsDTO : drugPackDetailsDTOS) {
                drugPackDetails = new DrugPackDetails(
                        new DrugPackDetails_FK(
                                drugPackDTO.getDrugPackCode(),
                                packDetailsDTO.getDrugCode()
                        )
                );
                doctorDrugPackDetailsRepo.save(drugPackDetails);
            }

            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteDrugPack(String drugPackCode) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorDrugPackRepo.setSession(session);
            doctorDrugPackRepo.setMySession(session);
            doctorDrugPackDetailsRepo.setSession(session);
            session.getTransaction().begin();

            ArrayList<DrugPackDetails> oldDrugPackDetails = (ArrayList<DrugPackDetails>) doctorDrugPackDetailsRepo.getAll();
            for (DrugPackDetails drugPackDetails : oldDrugPackDetails) {
                if (drugPackDetails.getDrugPackDetails_fk().getDrugPackCode().equals(drugPackCode)) {
                    doctorDrugPackDetailsRepo.delete(drugPackDetails);
                }
            }

            DrugPack drugPack = doctorDrugPackRepo.getDrugPack(drugPackCode);
            doctorDrugPackRepo.delete(drugPack);

            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DrugPackDTO getDrugPack(String drugPackCode) throws Exception {
        DrugPackDTO drugPackDTO = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorDrugPackRepo.setMySession(session);
            doctorDrugPackDetailsRepo.setSession(session);
            session.getTransaction().begin();

            DrugPack drugPack = doctorDrugPackRepo.getDrugPack(drugPackCode);

            ArrayList<DrugPackDetails> drugPackDetails = (ArrayList<DrugPackDetails>) doctorDrugPackDetailsRepo.getAll();
            ArrayList<DrugPackDetailsDTO> drugPackDetailsDTOS = new ArrayList<>();

            for (DrugPackDetails packDetails : drugPackDetails) {
                if (packDetails.getDrugPackDetails_fk().getDrugPackCode().equals(drugPackCode)) {
                    drugPackDetailsDTOS.add(new DrugPackDetailsDTO(
                            packDetails.getDrugPackDetails_fk().getDrugPackCode(),
                            packDetails.getDrugPackDetails_fk().getDrugCode()
                    ));
                }
            }

            drugPackDTO = new DrugPackDTO(
                    drugPack.getDrugPackCode(),
                    drugPack.getName(),
                    drugPack.getEmployee_drugPack().getEmployeeID(),
                    drugPackDetailsDTOS
            );

            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return drugPackDTO;
    }

    @Override
    public List<DrugPackDTO> getMyAllDrugPacks(String employeeID) throws Exception {
        List<DrugPackDTO> drugPackDTOS = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorDrugPackRepo.setMySession(session);
            session.getTransaction().begin();
            List<DrugPack> drugPacks = doctorDrugPackRepo.getMyAllDrugPacks(employeeID);
            for (DrugPack drugPack : drugPacks) {
                if(!drugPack.getDrugPackCode().equals("DRGPK-00000")){
                    drugPackDTOS.add(new DrugPackDTO(
                            drugPack.getDrugPackCode(),
                            drugPack.getName()
                    ));
                }
            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return drugPackDTOS;
    }

    @Override
    public List<DrugPackDTO> searchDrugPacks(String employeeID, String searchText) throws Exception {
        List<DrugPackDTO> drugPackDTOS = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            doctorDrugPackRepo.setMySession(session);
            session.getTransaction().begin();
            List<DrugPack> drugPacks = doctorDrugPackRepo.searchDrugPacks(employeeID, searchText);
            for (DrugPack drugPack : drugPacks) {
                if(!drugPack.getDrugPackCode().equals("DRGPK-00000")){
                    drugPackDTOS.add(new DrugPackDTO(
                            drugPack.getDrugPackCode(),
                            drugPack.getName()
                    ));
                }
            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return drugPackDTOS;
    }
}
