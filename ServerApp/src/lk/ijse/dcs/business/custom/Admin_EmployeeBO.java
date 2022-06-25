package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.EmployeeDTO;

import java.util.List;

public interface Admin_EmployeeBO extends SuperBO {

    public String generateNextEmployeeID() throws Exception;

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws Exception;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws Exception;

    public boolean deleteEmployee(String employeeID) throws Exception;

    public EmployeeDTO getEmployee(String employeeID) throws Exception;

    public List<EmployeeDTO> findEmployees(String searchText) throws Exception;

    public List<EmployeeDTO> getAllEmployees() throws Exception;

}
