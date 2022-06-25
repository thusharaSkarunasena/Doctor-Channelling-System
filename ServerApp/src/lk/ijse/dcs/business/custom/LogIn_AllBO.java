package lk.ijse.dcs.business.custom;

import lk.ijse.dcs.business.SuperBO;
import lk.ijse.dcs.dto.EmployeeDTO;

public interface LogIn_AllBO extends SuperBO {

    public EmployeeDTO getLoggedEmployeeDetails(String userName, String password) throws Exception;

}
