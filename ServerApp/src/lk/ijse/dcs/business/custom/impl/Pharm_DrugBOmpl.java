package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Pharm_DrugBO;
import lk.ijse.dcs.dto.DrugDTO;
import lk.ijse.dcs.entity.Drug;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Pharm_DrugRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Pharm_DrugBOmpl implements Pharm_DrugBO {

    private Pharm_DrugRepo pharmDrugRepo =
            (Pharm_DrugRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.PHARM_DRUG);

    @Override
    public String generateNextDrugCode() throws Exception {
        String nextDrugCode = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmDrugRepo.setMySession(session);
            session.getTransaction().begin();
            nextDrugCode = pharmDrugRepo.generateNextDrugCode();
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return nextDrugCode;
    }

    @Override
    public boolean saveDrug(DrugDTO drugDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmDrugRepo.setSession(session);
            session.getTransaction().begin();
            pharmDrugRepo.save(new Drug(
                    drugDTO.getDrugCode(),
                    drugDTO.getName(),
                    drugDTO.getBrand(),
                    drugDTO.getDescription(),
                    drugDTO.getUnitPrice()
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
    public boolean updateDrug(DrugDTO drugDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmDrugRepo.setSession(session);
            session.getTransaction().begin();
            pharmDrugRepo.update(new Drug(
                    drugDTO.getDrugCode(),
                    drugDTO.getName(),
                    drugDTO.getBrand(),
                    drugDTO.getDescription(),
                    drugDTO.getUnitPrice()
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
    public boolean deleteDrug(String drugCode) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmDrugRepo.setSession(session);
            pharmDrugRepo.setMySession(session);
            session.getTransaction().begin();
            Drug drug = pharmDrugRepo.getDrug(drugCode);
            pharmDrugRepo.delete(drug);
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DrugDTO getDrug(String drugCode) throws Exception {
        Drug drug = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmDrugRepo.setMySession(session);
            session.getTransaction().begin();
            drug = pharmDrugRepo.getDrug(drugCode);
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        return new DrugDTO(
                drug.getDrugCode(),
                drug.getName(),
                drug.getBrand(),
                drug.getDescription(),
                drug.getUnitPrice()
        );
    }

    @Override
    public List<DrugDTO> getAllDrugs() throws Exception {
        List<Drug> drugs;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmDrugRepo.setSession(session);
            session.getTransaction().begin();
            drugs = pharmDrugRepo.getAll();
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        List<DrugDTO> drugDTOS = new ArrayList<>();

        for (Drug drug : drugs) {
            if(!drug.getDrugCode().equals("DRG-00000")){
                drugDTOS.add(new DrugDTO(
                        drug.getDrugCode(),
                        drug.getName(),
                        drug.getBrand(),
                        drug.getDescription(),
                        drug.getUnitPrice()
                ));
            }
        }
        return drugDTOS;
    }

    @Override
    public List<DrugDTO> searchDrug(String searchText) throws Exception {
        List<Drug> drugs;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            pharmDrugRepo.setMySession(session);
            session.getTransaction().begin();
            drugs = pharmDrugRepo.searchDrug(searchText);
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
        List<DrugDTO> drugDTOS = new ArrayList<>();

        for (Drug drug : drugs) {
            if(!drug.getDrugCode().equals("DRG-00000")){
                drugDTOS.add(new DrugDTO(
                        drug.getDrugCode(),
                        drug.getName(),
                        drug.getBrand(),
                        drug.getDescription(),
                        drug.getUnitPrice()
                ));
            }
        }
        return drugDTOS;
    }

}
