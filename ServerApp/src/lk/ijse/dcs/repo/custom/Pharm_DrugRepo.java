package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Drug;
import lk.ijse.dcs.repo.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface Pharm_DrugRepo extends CrudRepository<Drug, String> {

    public void setMySession(Session mySession) throws Exception;

    public String generateNextDrugCode() throws Exception;

    public Drug getDrug(String drugCode) throws Exception;

    public List<Drug> searchDrug(String searchText) throws Exception;

}
