package lk.ijse.dcs.repo;

import lk.ijse.dcs.repo.custom.impl.*;

public class RepoFactory {

    public enum RepoTypes {
        ADMIN_EMPLOYEE, LOGIN_ALL, RECEP_PATIENT, RECEP_APPOINTMENT, PHARM_DRUG, DOCTOR_DRUGPACK,
        DOCTOR_DRUGPACKDETAILS, DOCTOR_PATIENTTREATMENT, DOCTOR_APPOINTMENTDETAILS, DOCTOR_PATIENTHISTORY,
        PHARM_ISSUEDRUGS, CASHIER_PAYMENT, CASHIER_PAYMENTHISTORY, PHARM_PREVIOUSSENDOUT, DOCTOR_PREVIOUSTREATMENT
    }

    private static RepoFactory repoFactory;

    private RepoFactory() {
    }

    public static RepoFactory getInstance() {
        if (repoFactory == null) {
            repoFactory = new RepoFactory();
        }
        return repoFactory;
    }

    public SuperRepository getRepo(RepoTypes repoTypes) {
        switch (repoTypes) {
            case ADMIN_EMPLOYEE:
                return new Admin_EmployeeRepoImpl();
            case LOGIN_ALL:
                return new LogIn_AllRepoImpl();
            case RECEP_PATIENT:
                return new Recep_PatientRepoImpl();
            case RECEP_APPOINTMENT:
                return new Recep_AppointmentRepoImpl();
            case PHARM_DRUG:
                return new Pharm_DrugRepoImpl();
            case DOCTOR_DRUGPACK:
                return new Doctor_DrugPackRepoImpl();
            case DOCTOR_DRUGPACKDETAILS:
                return new Doctor_DrugPackDetailsRepoImpl();
            case DOCTOR_PATIENTTREATMENT:
                return new Doctor_PatientTreatmentRepoImpl();
            case DOCTOR_APPOINTMENTDETAILS:
                return new Doctor_AppointmentDetailsRepoImpl();
            case DOCTOR_PATIENTHISTORY:
                return new Doctor_PatientHistoryRepoImpl();
            case PHARM_ISSUEDRUGS:
                return new Pharm_IssueDrugsRepoImpl();
            case CASHIER_PAYMENT:
                return new Cashier_PaymentRepoImpl();
            case CASHIER_PAYMENTHISTORY:
                return new Cashier_PaymentHistoryRepoImpl();
            case PHARM_PREVIOUSSENDOUT:
                return new Pharm_PreviousSendOutsRepoImpl();
            case DOCTOR_PREVIOUSTREATMENT:
                return new Doctor_PreviousTreatmentRepoImpl();
            default:
                return null;
        }
    }

}
