package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Pharm_DrugBO;
import lk.ijse.dcs.dto.DrugDTO;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.reservations.Reservations;
import lk.ijse.dcs.service.custom.Pharm_DrugService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Pharm_DrugServiceImpl extends UnicastRemoteObject implements Pharm_DrugService {

    private Pharm_DrugBO pharmDrugBO = (Pharm_DrugBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PHARM_DRUG);

    private static ArrayList<Observer> allDrugObservers = new ArrayList<>();
    private static Reservations<Pharm_DrugService> drugReservations = new Reservations();

    public Pharm_DrugServiceImpl() throws RemoteException {
    }

    @Override
    public String generateNextDrugCode() throws Exception {
        return pharmDrugBO.generateNextDrugCode();
    }

    @Override
    public boolean saveDrug(Observer observer, DrugDTO drugDTO) throws Exception {
        boolean result = pharmDrugBO.saveDrug(drugDTO);
        if (result) {
            notifyAllObservers(observer, drugDTO.getDrugCode(), "Saved");
            return result;
        } else {
            return result;
        }
    }

    @Override
    public boolean updateDrug(Observer observer, DrugDTO drugDTO) throws Exception {
        boolean result = pharmDrugBO.updateDrug(drugDTO);
        if (result) {
            notifyAllObservers(observer, drugDTO.getDrugCode(), "Updated");
            return result;
        } else {
            return result;
        }
    }

    @Override
    public boolean deleteDrug(Observer observer, String drugCode) throws Exception {
        boolean result = pharmDrugBO.deleteDrug(drugCode);
        if (result) {
            notifyAllObservers(observer, drugCode, "Deleted");
            return result;
        } else {
            return result;
        }
    }

    @Override
    public DrugDTO getDrug(String drugCode) throws Exception {
        return pharmDrugBO.getDrug(drugCode);
    }

    @Override
    public List<DrugDTO> getAllDrugs() throws Exception {
        return pharmDrugBO.getAllDrugs();
    }

    @Override
    public List<DrugDTO> searchDrug(String searchText) throws Exception {
        return pharmDrugBO.searchDrug(searchText);
    }

    @Override
    public void register(Observer observer) throws Exception {
        allDrugObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allDrugObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(Observer observer, String primaryKey, String status) throws Exception {
        for (Observer obsvr : allDrugObservers) {
            new Thread(() -> {
                if (!obsvr.equals(observer)) {
                    try {
                        obsvr.informDBUpdate(primaryKey, status);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ).start();
        }
    }


    @Override
    public boolean reserve(Object id) throws Exception {
        return drugReservations.reserve((String) id,this);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return drugReservations.release((String) id,this);
    }
}
