package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Pharm_IssueDrugsBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.service.custom.Pharm_IssueDrugsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Pharm_IssueDrugsServiceImpl extends UnicastRemoteObject implements Pharm_IssueDrugsService {

    private Pharm_IssueDrugsBO pharmIssueDrugsBO =
            (Pharm_IssueDrugsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PHARM_ISSUEDRUGS);

    public Pharm_IssueDrugsServiceImpl() throws RemoteException {
    }

    @Override
    public AppointmentDTO getAppointment(String patientID, String date) throws Exception {
        return pharmIssueDrugsBO.getAppointment(patientID, date);
    }

    @Override
    public List<AppointmentDetailsDTO> getAppointmentDetail(String appointmentNO) throws Exception {
        return pharmIssueDrugsBO.getAppointmentDetail(appointmentNO);
    }

    @Override
    public boolean updateAppointmentAndDetails(String pharmacistID, List<AppointmentDetailsDTO> appointmentDetailsDTOS) throws Exception {
        return pharmIssueDrugsBO.updateAppointmentAndDetails(pharmacistID, appointmentDetailsDTOS);
    }
}
