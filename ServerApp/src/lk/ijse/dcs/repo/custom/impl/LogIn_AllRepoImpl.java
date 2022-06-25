package lk.ijse.dcs.repo.custom.impl;

import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.repo.custom.LogIn_AllRepo;
import org.hibernate.Session;

import java.util.ArrayList;

public class LogIn_AllRepoImpl implements LogIn_AllRepo {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public Employee getLoggedEmployeeDetails(String userName, String password) throws Exception {
        Employee employee=null;
        ArrayList<Employee> employees = (ArrayList<Employee>) session.createQuery("from Employee").list();
        boolean isExist=false;
        for(Employee emp:employees){
            if (emp.getUserName().equals(userName) && emp.getPassword().equals(password) ){
                isExist=true;
                employee=emp;
                break;
            }
        }

        if(!isExist){
            employee=new Employee(
                    "EMP-000",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null"
            );
        }
        return employee;
    }

}
