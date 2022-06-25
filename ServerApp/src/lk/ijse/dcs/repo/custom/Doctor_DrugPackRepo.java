package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.DrugPack;
import lk.ijse.dcs.repo.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface Doctor_DrugPackRepo extends CrudRepository<DrugPack, String> {

    public void setMySession(Session mySession) throws Exception;

    public String generateNextDrugPackCode() throws Exception;

    public DrugPack getDrugPack(String drugPackCode) throws Exception;

    public List<DrugPack> getMyAllDrugPacks(String employeeID) throws Exception;

    public List<DrugPack> searchDrugPacks(String employeeID, String searchText) throws Exception;

}
