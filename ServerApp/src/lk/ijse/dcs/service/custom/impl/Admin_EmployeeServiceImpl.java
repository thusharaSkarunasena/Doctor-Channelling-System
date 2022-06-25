package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.business.BOFactory;
import lk.ijse.dcs.business.custom.Admin_EmployeeBO;
import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.reservations.Reservations;
import lk.ijse.dcs.service.custom.Admin_EmployeeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Admin_EmployeeServiceImpl extends UnicastRemoteObject implements Admin_EmployeeService {

    private Admin_EmployeeBO adminEmployeeBO =
            (Admin_EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN_EMPLOYEE);

    private static ArrayList<Observer> allEmployeeObservers = new ArrayList<>();
    private static Reservations<Admin_EmployeeService> employeeReservations = new Reservations();


    public Admin_EmployeeServiceImpl() throws RemoteException {
    }

    @Override
    public String generateNextEmployeeID() throws Exception {
        return adminEmployeeBO.generateNextEmployeeID();
    }

    @Override
    public boolean saveEmployee(Observer observer, EmployeeDTO employeeDTO) throws Exception {
        boolean result = adminEmployeeBO.saveEmployee(employeeDTO);
        if (result) {
            notifyAllObservers(observer, employeeDTO.getEmployeeID(), "Saved");
            return result;
        }
        return result;
    }

    @Override
    public boolean updateEmployee(Observer observer, EmployeeDTO employeeDTO) throws Exception {
        boolean result = adminEmployeeBO.updateEmployee(employeeDTO);
        if (result) {
            notifyAllObservers(observer, employeeDTO.getEmployeeID(), "Updated");
            return result;
        } else {
            return result;
        }
    }

    @Override
    public boolean deleteEmployee(Observer observer, String employeeID) throws Exception {
        boolean result = adminEmployeeBO.deleteEmployee(employeeID);
        if (result) {
            notifyAllObservers(observer, employeeID, "Deleted");
            return result;
        } else {
            return result;
        }
    }

    @Override
    public EmployeeDTO getEmployee(String employeeID) throws Exception {
        return adminEmployeeBO.getEmployee(employeeID);
    }

    @Override
    public List<EmployeeDTO> findEmployees(String searchText) throws Exception {
        return adminEmployeeBO.findEmployees(searchText);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws Exception {
        return adminEmployeeBO.getAllEmployees();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allEmployeeObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allEmployeeObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(Observer observer, String primaryKey, String status) throws Exception {
        for (Observer obsvr : allEmployeeObservers) {
            new Thread(() -> {
                if (!obsvr.equals(observer)) {
                    try {
                        obsvr.informDBUpdate(primaryKey, status);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ).start();
        }
    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return employeeReservations.reserve((String) id, this);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return employeeReservations.release((String) id, this);
    }
}
