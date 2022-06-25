package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.LogIn_AllBO;
import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.service.custom.LogIn_AllService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LogIn_AllServiceImpl extends UnicastRemoteObject implements LogIn_AllService {

    private LogIn_AllBO logInAllBO = (LogIn_AllBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN_ALL);

    public LogIn_AllServiceImpl() throws RemoteException {
    }

    @Override
    public EmployeeDTO getLoggedEmployeeDetails(String userName, String password) throws Exception {
        return logInAllBO.getLoggedEmployeeDetails(userName, password);
    }

}
