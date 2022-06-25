package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Doctor_PatientHistoryBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.service.custom.Doctor_PatientHistoryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Doctor_PatientHistoryServiceImpl extends UnicastRemoteObject implements Doctor_PatientHistoryService {

    private Doctor_PatientHistoryBO doctorPatientHistoryBO =
            (Doctor_PatientHistoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR_PATIENTHISTORY);

    public Doctor_PatientHistoryServiceImpl() throws RemoteException {
    }

    @Override
    public List<AppointmentDTO> getAllPatientHistory(String patientID) throws Exception {
        return doctorPatientHistoryBO.getAllPatientHistory(patientID);
    }

    @Override
    public List<AppointmentDTO> searchAllPatientHistory(String patientID, String searchText) throws Exception {
        return doctorPatientHistoryBO.searchAllPatientHistory(patientID, searchText);
    }

    @Override
    public AppointmentDTO getPatientHistory(String appointmentNO) throws Exception {
        return doctorPatientHistoryBO.getPatientHistory(appointmentNO);
    }

    @Override
    public List<AppointmentDetailsDTO> getPatientHistoryDetails(String appointmentNO) throws Exception {
        return doctorPatientHistoryBO.getPatientHistoryDetails(appointmentNO);
    }
}
