package lk.ijse.dcs.proxy;

import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.SuperService;
import lk.ijse.dcs.service.custom.*;

import java.rmi.Naming;

public class ProxyHandler implements ServiceFactory {

    public static ProxyHandler proxyHandler;

    private ServiceFactory serviceFactory;

    private Window_SessionFactoryService windowSessionFactoryService;
    private Admin_EmployeeService adminEmployeeService;
    private LogIn_AllService logInAllService;
    private Recep_PatientService recepPatientService;
    private Recep_AppointmentService recepAppointmentService;
    private Pharm_DrugService pharmDrugService;
    private Doctor_DrugPackService doctorDrugPackService;
    private Doctor_PatientTreatmentService doctorPatientTreatmentService;
    private Doctor_PatientHistoryService doctorPatientHistoryService;
    private Pharm_IssueDrugsService pharmIssueDrugsService;
    private Cashier_PaymentService cashierPaymentService;
    private Cashier_PaymentHistoryService cashierPaymentHistoryService;
    private Pharm_PreviousSendOutsService pharmPreviousSendOutsService;
    private Doctor_PreviousTreatmentService doctorPreviousTreatmentService;


    public ProxyHandler() throws Exception {
        serviceFactory = (ServiceFactory) Naming.lookup("rmi://127.0.0.1:2018/newPhilipHospitalsDCSServer");
    }

    public static ProxyHandler getInstance() throws Exception {
        if (proxyHandler == null) {
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }

    @Override
    public SuperService getService(ServiceTypes serviceTypes) throws Exception {
        switch (serviceTypes) {
            case SESSION_FACTORY:
                windowSessionFactoryService = (Window_SessionFactoryService) serviceFactory.getService(ServiceTypes.SESSION_FACTORY);
                return windowSessionFactoryService;
            case ADMIN_EMPLOYEE:
                adminEmployeeService = (Admin_EmployeeService) serviceFactory.getService(ServiceTypes.ADMIN_EMPLOYEE);
                return adminEmployeeService;
            case LOGIN_ALL:
                logInAllService = (LogIn_AllService) serviceFactory.getService(ServiceTypes.LOGIN_ALL);
                return logInAllService;
            case RECEP_PATIENT:
                recepPatientService = (Recep_PatientService) serviceFactory.getService(ServiceTypes.RECEP_PATIENT);
                return recepPatientService;
            case RECEP_APPOINTMENT:
                recepAppointmentService = (Recep_AppointmentService) serviceFactory.getService(ServiceTypes.RECEP_APPOINTMENT);
                return recepAppointmentService;
            case PHARM_DRUG:
                pharmDrugService = (Pharm_DrugService) serviceFactory.getService(ServiceTypes.PHARM_DRUG);
                return pharmDrugService;
            case DOCTOR_DRUGPACK:
                doctorDrugPackService = (Doctor_DrugPackService) serviceFactory.getService(ServiceTypes.DOCTOR_DRUGPACK);
                return doctorDrugPackService;
            case DOCTOR_PATIENTTREATMENT:
                doctorPatientTreatmentService = (Doctor_PatientTreatmentService) serviceFactory.getService(ServiceTypes.DOCTOR_PATIENTTREATMENT);
                return doctorPatientTreatmentService;
            case DOCTOR_PATIENTHISTORY:
                doctorPatientHistoryService = (Doctor_PatientHistoryService) serviceFactory.getService(ServiceTypes.DOCTOR_PATIENTHISTORY);
                return doctorPatientHistoryService;
            case PHARM_ISSUEDRUGS:
                pharmIssueDrugsService = (Pharm_IssueDrugsService) serviceFactory.getService(ServiceTypes.PHARM_ISSUEDRUGS);
                return pharmIssueDrugsService;
            case CASHIER_PAYMENT:
                cashierPaymentService= (Cashier_PaymentService) serviceFactory.getService(ServiceTypes.CASHIER_PAYMENT);
                return cashierPaymentService;
            case CASHIER_PAYMENTHISTORY:
                cashierPaymentHistoryService= (Cashier_PaymentHistoryService) serviceFactory.getService(ServiceTypes.CASHIER_PAYMENTHISTORY);
                return cashierPaymentHistoryService;
            case PHARM_PREVIOUSSENDOUT:
                pharmPreviousSendOutsService= (Pharm_PreviousSendOutsService) serviceFactory.getService(ServiceTypes.PHARM_PREVIOUSSENDOUT);
                return pharmPreviousSendOutsService;
            case DOCTOR_PREVIOUSTREATMENT:
                doctorPreviousTreatmentService= (Doctor_PreviousTreatmentService) serviceFactory.getService(ServiceTypes.DOCTOR_PREVIOUSTREATMENT);
                return doctorPreviousTreatmentService;
            default:
                return null;
        }
    }

}
