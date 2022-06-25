package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.DrugDTO;

import java.util.List;

public interface Pharm_DrugBO extends SuperBO {

    public String generateNextDrugCode() throws Exception;

    public boolean saveDrug(DrugDTO drugDTO) throws Exception;

    public boolean updateDrug(DrugDTO drugDTO) throws Exception;

    public boolean deleteDrug(String drugCode) throws Exception;

    public DrugDTO getDrug(String drugCode) throws Exception;

    public List<DrugDTO> getAllDrugs() throws Exception;

    public List<DrugDTO> searchDrug(String searchText) throws Exception;

}
