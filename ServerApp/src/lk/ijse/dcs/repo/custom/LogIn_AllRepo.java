package lk.ijse.dcs.repo.custom;

import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.repo.SuperRepository;

public interface LogIn_AllRepo extends SuperRepository {

    public Employee getLoggedEmployeeDetails(String userName, String password) throws Exception;

}
