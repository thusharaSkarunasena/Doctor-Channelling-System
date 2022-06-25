package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Doctor_PatientTreatmentBO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.service.custom.Doctor_PatientTreatmentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Doctor_PatientTreatmentServiceImpl extends UnicastRemoteObject implements Doctor_PatientTreatmentService {

    private Doctor_PatientTreatmentBO doctorPatientTreatmentBO =
            (Doctor_PatientTreatmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR_PATIENTTREATMENT);

    public Doctor_PatientTreatmentServiceImpl() throws RemoteException {
    }

    @Override
    public String getReservedAppointmentNO(String currentDate, String EmployeeID, String patientID) throws Exception {
        return doctorPatientTreatmentBO.getReservedAppointmentNO(currentDate, EmployeeID, patientID);
    }

    @Override
    public boolean saveAppointmentAndDetails(String appointmentNO, String doctorsDescription, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception {
        return doctorPatientTreatmentBO.saveAppointmentAndDetails(appointmentNO, doctorsDescription, appointmentDetailsDTOS);
    }
}
