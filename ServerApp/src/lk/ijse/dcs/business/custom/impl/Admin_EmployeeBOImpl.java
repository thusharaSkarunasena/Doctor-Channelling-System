package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.Admin_EmployeeBO;
import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.Admin_EmployeeRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Admin_EmployeeBOImpl implements Admin_EmployeeBO {

    private Admin_EmployeeRepo adminEmployeeRepo =
            (Admin_EmployeeRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ADMIN_EMPLOYEE);

    @Override
    public String generateNextEmployeeID() throws Exception {
        String nextEmployeeID=null;
        try (Session session= HibernateUtil.getSessionFactory().openSession()){
            adminEmployeeRepo.setMySession(session);
            session.getTransaction().begin();
            nextEmployeeID= adminEmployeeRepo.generateNextEmployeeID();
            if(session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
        }
        return nextEmployeeID;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws Exception {
        try {
            try (Session session=HibernateUtil.getSessionFactory().openSession()){
                adminEmployeeRepo.setSession(session);
                session.getTransaction().begin();
                adminEmployeeRepo.save(new Employee(
                        employeeDTO.getEmployeeID(),
                        employeeDTO.getName(),
                        employeeDTO.getEmployment(),
                        employeeDTO.getAddress(),
                        employeeDTO.getNic(),
                        employeeDTO.getCn_home(),
                        employeeDTO.getCn_mobile(),
                        employeeDTO.getOtherDetails(),
                        employeeDTO.getUserName(),
                        employeeDTO.getPassword()
                ));
                if(session.getTransaction().isActive()){
                    session.getTransaction().commit();
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws Exception {
        try {
            try (Session session=HibernateUtil.getSessionFactory().openSession()){
                adminEmployeeRepo.setSession(session);
                session.getTransaction().begin();
                adminEmployeeRepo.update(new Employee(
                        employeeDTO.getEmployeeID(),
                        employeeDTO.getName(),
                        employeeDTO.getEmployment(),
                        employeeDTO.getAddress(),
                        employeeDTO.getNic(),
                        employeeDTO.getCn_home(),
                        employeeDTO.getCn_mobile(),
                        employeeDTO.getOtherDetails(),
                        employeeDTO.getUserName(),
                        employeeDTO.getPassword()
                ));
                if(session.getTransaction().isActive()){
                    session.getTransaction().commit();
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(String employeeID) throws Exception {
        if(!employeeID.equals("EMP-001")){
            try {
                try (Session session=HibernateUtil.getSessionFactory().openSession()){
                    adminEmployeeRepo.setSession(session);
                    adminEmployeeRepo.setMySession(session);
                    session.getTransaction().begin();
                    Employee employee=adminEmployeeRepo.getEmployee(employeeID);
                    adminEmployeeRepo.delete(employee);
                    if(session.getTransaction().isActive()){
                        session.getTransaction().commit();
                    }
                }
                return true;
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public EmployeeDTO getEmployee(String employeeID) throws Exception {
        Employee employee=null;
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            adminEmployeeRepo.setMySession(session);
            session.getTransaction().begin();
            employee= adminEmployeeRepo.getEmployee(employeeID);
            if(session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
        }
        return new EmployeeDTO(
                employee.getEmployeeID(),
                employee.getName(),
                employee.getEmployment(),
                employee.getAddress(),
                employee.getNic(),
                employee.getCn_home(),
                employee.getCn_mobile(),
                employee.getOtherDetails(),
                employee.getUserName(),
                employee.getPassword()
        );
    }

    @Override
    public List<EmployeeDTO> findEmployees(String searchText) throws Exception {
        List<Employee> employees=null;
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            adminEmployeeRepo.setMySession(session);
            session.getTransaction().begin();
            employees= adminEmployeeRepo.searchEmployees(searchText);
            if(session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
        }
        List<EmployeeDTO> employeeDTOS=new ArrayList<>();
        for(Employee employee:employees){
            if(!employee.getEmployeeID().equals("EMP-000")){
                employeeDTOS.add(new EmployeeDTO(
                        employee.getEmployeeID(),
                        employee.getName(),
                        employee.getEmployment(),
                        employee.getAddress(),
                        employee.getNic(),
                        employee.getCn_home(),
                        employee.getCn_mobile(),
                        employee.getOtherDetails(),
                        employee.getUserName(),
                        employee.getPassword()
                ));
            }
        }
        return employeeDTOS;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws Exception {
        List<Employee> employees=null;
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            adminEmployeeRepo.setSession(session);
            session.getTransaction().begin();
            employees= adminEmployeeRepo.getAll();
            if(session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
        }
        List<EmployeeDTO> employeeDTOS=new ArrayList<>();
        for(Employee employee:employees){
            if(!employee.getEmployeeID().equals("EMP-000")){
                employeeDTOS.add(new EmployeeDTO(
                        employee.getEmployeeID(),
                        employee.getName(),
                        employee.getEmployment(),
                        employee.getAddress(),
                        employee.getNic(),
                        employee.getCn_home(),
                        employee.getCn_mobile(),
                        employee.getOtherDetails(),
                        employee.getUserName(),
                        employee.getPassword()
                ));
            }
        }
        return employeeDTOS;
    }
}
