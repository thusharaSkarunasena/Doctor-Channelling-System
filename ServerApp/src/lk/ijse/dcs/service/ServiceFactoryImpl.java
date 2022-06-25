package lk.ijse.dcs.service;

import lk.ijse.dcs.service.custom.impl.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    private ServiceFactoryImpl() throws RemoteException {
    }

    private static ServiceFactoryImpl serviceFactory;

    public static ServiceFactoryImpl getInstance() throws Exception {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }


    @Override
    public SuperService getService(ServiceTypes serviceTypes) throws Exception {
        switch (serviceTypes) {
            case SESSION_FACTORY:
                return new Window_SessionFactoryServiceImpl();
            case ADMIN_EMPLOYEE:
                return new Admin_EmployeeServiceImpl();
            case LOGIN_ALL:
                return new LogIn_AllServiceImpl();
            case RECEP_PATIENT:
                return new Recep_PatientServiceImpl();
            case RECEP_APPOINTMENT:
                return new Recep_AppointmentServiceImpl();
            case PHARM_DRUG:
                return new Pharm_DrugServiceImpl();
            case DOCTOR_DRUGPACK:
                return new Doctor_DrugPackServiceImpl();
            case DOCTOR_PATIENTTREATMENT:
                return new Doctor_PatientTreatmentServiceImpl();
            case DOCTOR_PATIENTHISTORY:
                return new Doctor_PatientHistoryServiceImpl();
            case PHARM_ISSUEDRUGS:
                return new Pharm_IssueDrugsServiceImpl();
            case CASHIER_PAYMENT:
                return new Cashier_PaymentServiceImpl();
            case CASHIER_PAYMENTHISTORY:
                return new Cashier_PaymentHistoryServiceImpl();
            case PHARM_PREVIOUSSENDOUT:
                return new Pharm_PreviousSendOutsServiceImpl();
            case DOCTOR_PREVIOUSTREATMENT:
                return new Doctor_PreviousTreatmentServiceImpl();
            default:
                return null;
        }
    }
}
