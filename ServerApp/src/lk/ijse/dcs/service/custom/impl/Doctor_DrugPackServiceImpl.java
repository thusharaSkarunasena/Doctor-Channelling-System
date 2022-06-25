package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Doctor_DrugPackBO;
import lk.ijse.dcs.dto.DrugPackDTO;
import lk.ijse.dcs.service.custom.Doctor_DrugPackService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Doctor_DrugPackServiceImpl extends UnicastRemoteObject implements Doctor_DrugPackService {

    private Doctor_DrugPackBO doctorDrugPackBO =
            (Doctor_DrugPackBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR_DRUGPACK);

    public Doctor_DrugPackServiceImpl() throws RemoteException {
    }

    @Override
    public String generateNextDrugPackCode() throws Exception {
        return doctorDrugPackBO.generateNextDrugPackCode();
    }

    @Override
    public boolean saveDrugPack(DrugPackDTO drugPackDTO) throws Exception {
        return doctorDrugPackBO.saveDrugPack(drugPackDTO);
    }

    @Override
    public boolean updateDrugPack(DrugPackDTO drugPackDTO) throws Exception {
        return doctorDrugPackBO.updateDrugPack(drugPackDTO);
    }

    @Override
    public boolean deleteDrugPack(String drugPackCode) throws Exception {
        return doctorDrugPackBO.deleteDrugPack(drugPackCode);
    }

    @Override
    public DrugPackDTO getDrugPack(String drugPackCode) throws Exception {
        return doctorDrugPackBO.getDrugPack(drugPackCode);
    }

    @Override
    public List<DrugPackDTO> getMyAllDrugPacks(String employeeID) throws Exception {
        return doctorDrugPackBO.getMyAllDrugPacks(employeeID);
    }

    @Override
    public List<DrugPackDTO> searchDrugPacks(String employeeID, String searchText) throws Exception {
        return doctorDrugPackBO.searchDrugPacks(employeeID ,searchText);
    }
}
