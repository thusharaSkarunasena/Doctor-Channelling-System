package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.DrugDTO;
import lk.ijse.dcs.observer.Observable;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.reservation.Reservation;
import lk.ijse.dcs.service.SuperService;

import java.util.List;

public interface Pharm_DrugService extends SuperService, Observable, Reservation {

    public String generateNextDrugCode() throws Exception;

    public boolean saveDrug(Observer observer, DrugDTO drugDTO) throws Exception;

    public boolean updateDrug(Observer observer, DrugDTO drugDTO) throws Exception;

    public boolean deleteDrug(Observer observer, String drugCode) throws Exception;

    public DrugDTO getDrug(String drugCode) throws Exception;

    public List<DrugDTO> getAllDrugs() throws Exception;

    public List<DrugDTO> searchDrug(String searchText) throws Exception;

}
