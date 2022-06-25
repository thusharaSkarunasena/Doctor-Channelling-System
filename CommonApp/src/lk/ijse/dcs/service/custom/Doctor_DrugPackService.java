package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.DrugPackDTO;
import lk.ijse.dcs.service.SuperService;

import java.util.List;

public interface Doctor_DrugPackService extends SuperService {

    public String generateNextDrugPackCode() throws Exception;

    public boolean saveDrugPack(DrugPackDTO drugPackDTO) throws Exception;

    public boolean updateDrugPack(DrugPackDTO drugPackDTO) throws Exception;

    public boolean deleteDrugPack(String drugPackCode) throws Exception;

    public DrugPackDTO getDrugPack(String drugPackCode) throws Exception;

    public List<DrugPackDTO> getMyAllDrugPacks(String employeeID) throws Exception;

    public List<DrugPackDTO> searchDrugPacks(String employeeID, String searchText) throws Exception;

}
