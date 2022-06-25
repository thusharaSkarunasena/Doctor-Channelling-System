package lk.ijse.dcs.business;

import lk.ijse.dcs.business.custom.impl.*;

public class BOFactory {

    public enum BOTypes {
        ADMIN_EMPLOYEE, LOGIN_ALL, RECEP_PATIENT, RECEP_APPOINTMENT, PHARM_DRUG, DOCTOR_DRUGPACK,
        DOCTOR_PATIENTTREATMENT, DOCTOR_PATIENTHISTORY, PHARM_ISSUEDRUGS, CASHIER_PAYMENT, CASHIER_PAYMENTHISTORY,
        PHARM_PREVIOUSSENDOUT, DOCTOR_PREVIOUSTREATMENT
    }

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ADMIN_EMPLOYEE:
                return new Admin_EmployeeBOImpl();
            case LOGIN_ALL:
                return new LogIn_AllBOImpl();
            case RECEP_PATIENT:
                return new RecepPatientBOImpl();
            case RECEP_APPOINTMENT:
                return new Recep_AppointmentBOImpl();
            case PHARM_DRUG:
                return new Pharm_DrugBOmpl();
            case DOCTOR_DRUGPACK:
                return new Doctor_DrugPackBOImpl();
            case DOCTOR_PATIENTTREATMENT:
                return new Doctor_PatientTreatmentBOImpl();
            case DOCTOR_PATIENTHISTORY:
                return new Doctor_PatientHistoryBOImpl();
            case PHARM_ISSUEDRUGS:
                return new Pharm_IssueDrugsBOImpl();
            case CASHIER_PAYMENT:
                return new Cashier_PaymentBOImpl();
            case CASHIER_PAYMENTHISTORY:
                return new Cashier_PaymentBOImpl();
            case PHARM_PREVIOUSSENDOUT:
                return new Pharm_PreviousSendOutsBOImpl();
            case DOCTOR_PREVIOUSTREATMENT:
                return new Doctor_PreviousTreatmentBOImpl();
            default:
                return null;
        }
    }

}
