package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Drug;
import lk.ijse.dcs.repo.CrudRepositoryImpl;
import lk.ijse.dcs.repo.custom.Pharm_DrugRepo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Pharm_DrugRepoImpl extends CrudRepositoryImpl<Drug, String> implements Pharm_DrugRepo {

    private Session session;

    @Override
    public void setMySession(Session mySession) throws Exception {
        this.session=mySession;
    }

    @Override
    public String generateNextDrugCode() throws Exception {
        String lastDrugCode = null;
        Integer NpartDCount = 0;
        ArrayList<String> ids = (ArrayList<String>) session.createNativeQuery("select drugCode from Drug " +
                "order by 1 desc limit 1").list();

        for (String lastID : ids) {
            lastDrugCode = lastID;
        }
        session.getTransaction().commit();

        if (lastDrugCode != null) {

            String[] output = lastDrugCode.split("-");

            Integer Npart = Integer.parseInt(output[1]);
            Npart = Npart + 1;
            Integer testNpart = Npart;

            while (testNpart != 0) {
                testNpart = testNpart / 10;
                NpartDCount++;
            }

            String nextDrugCode = "DRG-";

            Integer rounds = 5 - NpartDCount;

            while (rounds != 0) {
                nextDrugCode = nextDrugCode + "0";
                rounds--;
            }

            nextDrugCode = nextDrugCode + "" + Npart;

            return nextDrugCode;
        } else {
            return "DRG-00001";
        }
    }

    @Override
    public Drug getDrug(String drugCode) throws Exception {
        Drug drug=null;
        ArrayList<Drug> drugs = (ArrayList<Drug>) session.createQuery("from Drug").list();
        boolean isExist=false;
        for(Drug drg:drugs){
            if (drg.getDrugCode().equals(drugCode)){
                isExist=true;
                drug=drg;
                break;
            }
        }
        return drug;
    }

    @Override
    public List<Drug> searchDrug(String searchText) throws Exception {
        String newSearchText="%"+searchText+"%";
        return session.createQuery("from Drug where drugCode like :searchText or name like :searchText " +
                "or brand like :searchText or description like :searchText")
                .setParameter("searchText", newSearchText).list();
    }

}
