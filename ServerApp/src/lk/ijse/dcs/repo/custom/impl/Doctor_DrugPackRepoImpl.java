package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.DrugPack;
import lk.ijse.dcs.repo.CrudRepositoryImpl;
import lk.ijse.dcs.repo.custom.Doctor_DrugPackRepo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Doctor_DrugPackRepoImpl extends CrudRepositoryImpl<DrugPack, String> implements Doctor_DrugPackRepo {

    private Session session;

    @Override
    public void setMySession(Session mySession) throws Exception {
        this.session=mySession;
    }

    @Override
    public String generateNextDrugPackCode() throws Exception {
        String lastDrugPackCode = null;
        Integer NpartDCount = 0;
        ArrayList<String> ids = (ArrayList<String>) session.createNativeQuery("select drugPackCode from DrugPack " +
                "order by 1 desc limit 1").list();

        for (String lastID : ids) {
            lastDrugPackCode = lastID;
        }
        session.getTransaction().commit();

        if (lastDrugPackCode != null) {

            String[] output = lastDrugPackCode.split("-");

            Integer Npart = Integer.parseInt(output[1]);
            Npart = Npart + 1;
            Integer testNpart = Npart;


            while (testNpart != 0) {
                testNpart = testNpart / 10;
                NpartDCount++;
            }

            String nextDrugPackCode = "DRGPK-";

            Integer rounds = 5 - NpartDCount;

            while (rounds != 0) {
                nextDrugPackCode = nextDrugPackCode + "0";
                rounds--;
            }

            nextDrugPackCode = nextDrugPackCode + "" + Npart;

            return nextDrugPackCode;
        } else {
            return "DRGPK-00001";
        }
    }

    @Override
    public DrugPack getDrugPack(String drugPackCode) throws Exception {
        DrugPack drugPack=null;
        ArrayList<DrugPack> drugPacks = (ArrayList<DrugPack>) session.createQuery("from DrugPack").list();
        boolean isExist=false;
        for(DrugPack drgpk:drugPacks){
            if (drgpk.getDrugPackCode().equals(drugPackCode)){
                isExist=true;
                drugPack=drgpk;
                break;
            }
        }
        return drugPack;
    }

    @Override
    public List<DrugPack> getMyAllDrugPacks(String employeeID) throws Exception {
        return session.createQuery("from DrugPack where employeeID = :empID").setParameter("empID", employeeID).list();
    }

    @Override
    public List<DrugPack> searchDrugPacks(String employeeID, String searchText) throws Exception {
        String newSearchText="%"+searchText+"%";
        return session.createQuery("from DrugPack where employeeID = :empID and drugPackCode like :searchText  " +
                "or name like :searchText").setParameter("empID", employeeID)
                .setParameter("searchText", newSearchText).list();

    }
}
