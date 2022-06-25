package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.observer.Observable;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.reservation.Reservation;
import lk.ijse.dcs.service.SuperService;

import java.util.List;

public interface Admin_EmployeeService extends SuperService, Observable, Reservation {

    public String generateNextEmployeeID() throws Exception;

    public boolean saveEmployee(Observer observer, EmployeeDTO employeeDTO) throws Exception;

    public boolean updateEmployee(Observer observer, EmployeeDTO employeeDTO) throws Exception;

    public boolean deleteEmployee(Observer observer, String employeeID) throws Exception;

    public EmployeeDTO getEmployee(String employeeID) throws Exception;

    public List<EmployeeDTO> findEmployees(String searchText) throws Exception;

    public List<EmployeeDTO> getAllEmployees() throws Exception;

}
