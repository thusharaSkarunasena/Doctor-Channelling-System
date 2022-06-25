package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Recep_PatientBO;
import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.reservations.Reservations;
import lk.ijse.dcs.service.custom.Recep_PatientService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Recep_PatientServiceImpl extends UnicastRemoteObject implements Recep_PatientService {

    private Recep_PatientBO recepPatientBO =
            (Recep_PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RECEP_PATIENT);

    private static ArrayList<Observer> allPatientObservers = new ArrayList<>();
    private static Reservations<Recep_PatientService> patientReservations = new Reservations();


    public Recep_PatientServiceImpl() throws RemoteException {
    }

    @Override
    public String generateNextPatientID() throws Exception {
        return recepPatientBO.generateNextPatientID();
    }

    @Override
    public boolean savePatient(Observer observer, PatientDTO patientDTO) throws Exception {
        boolean result = recepPatientBO.savePatient(patientDTO);
        if (result) {
            notifyAllObservers(observer, patientDTO.getPatientID(), "Saved");
            return result;
        } else {
            return result;
        }
    }

    @Override
    public boolean updatePatient(Observer observer, PatientDTO patientDTO) throws Exception {
        boolean result = recepPatientBO.updatePatient(patientDTO);
        if (result) {
            notifyAllObservers(observer, patientDTO.getPatientID(), "Updated");
            return result;
        } else {
            return result;
        }
    }

    @Override
    public boolean deletePatient(Observer observer, String patientID) throws Exception {
        boolean result = recepPatientBO.deletePatient(patientID);
        if (result) {
            notifyAllObservers(observer, patientID, "Deleted");
            return result;
        }
        return result;
    }

    @Override
    public PatientDTO getPatient(String patientID) throws Exception {
        return recepPatientBO.getPatient(patientID);
    }

    @Override
    public List<PatientDTO> getAllPatients() throws Exception {
        return recepPatientBO.getAllPatients();
    }

    @Override
    public List<PatientDTO> searchPatients(String searchText) throws Exception {
        return recepPatientBO.searchPatients(searchText);
    }

    @Override
    public void register(Observer observer) throws Exception {
        allPatientObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allPatientObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(Observer observer, String primaryKey, String status) throws Exception {
        for (Observer obsvr : allPatientObservers) {
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
        return patientReservations.reserve((String) id, this);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return patientReservations.release((String) id, this);
    }
}
