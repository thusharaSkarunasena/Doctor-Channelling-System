package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.repo.CrudRepositoryImpl;
import lk.ijse.dcs.repo.custom.Admin_EmployeeRepo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Admin_EmployeeRepoImpl extends CrudRepositoryImpl<Employee, String> implements Admin_EmployeeRepo {

    private Session session;

    @Override
    public void setMySession(Session mySession) throws Exception {
        this.session = mySession;
    }

    @Override
    public String generateNextEmployeeID() throws Exception {
        String lastEmpID = null;
        Integer NpartDCount = 0;
        ArrayList<String> ids =(ArrayList<String>) session.createNativeQuery("select employeeID from Employee" +
                " order by 1 desc limit 1").list();

        for (String lastID : ids) {
            lastEmpID = lastID;
        }
        session.getTransaction().commit();

        if (lastEmpID != null) {

            String[] output = lastEmpID.split("-");

            Integer Npart = Integer.parseInt(output[1]);
            Npart = Npart + 1;
            Integer testNpart = Npart;


            while (testNpart != 0) {
                testNpart = testNpart / 10;
                NpartDCount++;
            }

            String nextEmpId = "EMP-";

            Integer rounds = 3 - NpartDCount;

            while (rounds != 0) {
                nextEmpId = nextEmpId + "0";
                rounds--;
            }

            nextEmpId = nextEmpId + "" + Npart;

            return nextEmpId;
        } else {
            return "EMP-001";
        }
    }

    @Override
    public Employee getEmployee(String employeeID) throws Exception {
        Employee employee=null;
        ArrayList<Employee> employees = (ArrayList<Employee>) session.createQuery("from Employee").list();
        for(Employee emp:employees){
            if (emp.getEmployeeID().equals(employeeID)){
                employee=emp;
                break;
            }
        }
        return employee;
    }

    @Override
    public List<Employee> searchEmployees(String searchText) throws Exception {
        String newSearchText="%"+searchText+"%";
        return session.createQuery("from Employee where employeeID like :searchText or " +
                "name like :searchText or employment like :searchText or " +
                "nic like :searchText").setParameter("searchText", newSearchText).list();
    }
}
