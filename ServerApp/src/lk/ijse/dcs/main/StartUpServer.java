package lk.ijse.dcs.main;

import lk.ijse.dcs.entity.Drug;
import lk.ijse.dcs.entity.DrugPack;
import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.resources.HibernateUtil;
import lk.ijse.dcs.service.ServiceFactoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class StartUpServer {

    public static void main(String[] args) {
        try {
            System.out.println("\nServer Starting...");

            Registry registry = LocateRegistry.createRegistry(2018);
            registry.rebind("newPhilipHospitalsDCSServer", ServiceFactoryImpl.getInstance());

            System.out.println("\nServer has been Started as 'newPhilipHospitalsDCSServer' with Port '2018' ...");
            System.out.println("\n\nCreating & Updating Database >>>");

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                ArrayList<Employee> employees = (ArrayList<Employee>) session.createQuery("from Employee").list();
                ArrayList<Drug> drugs = (ArrayList<Drug>) session.createQuery("from Drug").list();
                ArrayList<DrugPack> drugPacks = (ArrayList<DrugPack>) session.createQuery("from DrugPack").list();

                boolean isExist1 = false;
                for (Employee employee : employees) {
                    if (employee.getEmployeeID().equals("EMP-001")) {
                        isExist1 = true;
                        break;
                    }
                }
                if (!isExist1) {
                    Employee employee1 = new Employee(
                            "EMP-001",
                            "Thushara - Main Admin",
                            "Admin",
                            "address",
                            "nic",
                            "cn_home",
                            "cn_mobile",
                            "otherDetails",
                            "admin",
                            "admin"
                    );
                    session.persist(employee1);
                }

                boolean isExist2 = false;
                for (Employee employee : employees) {
                    if (employee.getEmployeeID().equals("EMP-000")) {
                        isExist2 = true;
                        break;
                    }
                }
                if (!isExist2) {
                    Employee employee2 = new Employee(
                            "EMP-000",
                            "Demo_Employee",
                            "Demo_Admin",
                            "Demo_Address",
                            "Demo_NIC",
                            "Demo_cn_home",
                            "Demo_cn_mobile",
                            "Demo_OtherDetails",
                            "demo_emp",
                            "demo_emp"
                    );
                    session.persist(employee2);
                }

                if (session.getTransaction().isActive()) {
                    session.getTransaction().commit();
                }
            }

            System.out.println("Database has been loaded Successfully >>>");
            System.out.println("\n\nServer is Running...!\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
