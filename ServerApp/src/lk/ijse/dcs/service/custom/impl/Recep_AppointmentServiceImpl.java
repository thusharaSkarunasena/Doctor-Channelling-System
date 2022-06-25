package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Recep_AppointmentBO;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.service.custom.Recep_AppointmentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Recep_AppointmentServiceImpl extends UnicastRemoteObject implements Recep_AppointmentService {

    private Recep_AppointmentBO recepAppointmentBO =
            (Recep_AppointmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RECEP_APPOINTMENT);

    public Recep_AppointmentServiceImpl() throws RemoteException {
    }

    @Override
    public String generateNextAppointmentNumber() throws Exception {
        return recepAppointmentBO.generateNextAppointmentNumber();
    }

    @Override
    public String getNextQueueNumber(String date, String doctorID) throws Exception {
        return recepAppointmentBO.getNextQueueNumber(date, doctorID);
    }

    @Override
    public boolean reserveAppointment(AppointmentDTO appointmentDTO) throws Exception {
        return recepAppointmentBO.reserveAppointment(appointmentDTO);
    }

}
