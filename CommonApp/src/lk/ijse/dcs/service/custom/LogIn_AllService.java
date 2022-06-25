package lk.ijse.dcs.service.custom;

import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.service.SuperService;

public interface LogIn_AllService extends SuperService {

    public EmployeeDTO getLoggedEmployeeDetails(String userName, String password) throws Exception;

}
