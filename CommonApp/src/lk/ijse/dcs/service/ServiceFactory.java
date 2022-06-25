package lk.ijse.dcs.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {

    public enum ServiceTypes{
        SESSION_FACTORY, ADMIN_EMPLOYEE, LOGIN_ALL, RECEP_PATIENT, RECEP_APPOINTMENT, PHARM_DRUG, DOCTOR_DRUGPACK,
        DOCTOR_PATIENTTREATMENT, DOCTOR_PATIENTHISTORY, PHARM_ISSUEDRUGS, CASHIER_PAYMENT, CASHIER_PAYMENTHISTORY,
        PHARM_PREVIOUSSENDOUT, DOCTOR_PREVIOUSTREATMENT
    }

    public SuperService getService(ServiceTypes serviceTypes) throws Exception;

}
