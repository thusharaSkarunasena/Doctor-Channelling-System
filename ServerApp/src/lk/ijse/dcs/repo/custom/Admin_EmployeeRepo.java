package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.repo.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface Admin_EmployeeRepo extends CrudRepository<Employee, String> {

    public void setMySession(Session mySession) throws Exception;

    public String generateNextEmployeeID() throws Exception;

    public Employee getEmployee(String employeeID) throws Exception;

    public List<Employee> searchEmployees(String searchText) throws Exception;

}
