package lk.ijse.dcs.business.custom.impl;

import lk.ijse.dcs.business.custom.LogIn_AllBO;
import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.entity.Employee;
import lk.ijse.dcs.repo.RepoFactory;
import lk.ijse.dcs.repo.custom.LogIn_AllRepo;
import lk.ijse.dcs.resources.HibernateUtil;
import org.hibernate.Session;

public class LogIn_AllBOImpl implements LogIn_AllBO {

    private LogIn_AllRepo logInAllRepo =
            (LogIn_AllRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.LOGIN_ALL);

    @Override
    public EmployeeDTO getLoggedEmployeeDetails(String userName, String password) throws Exception {
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            logInAllRepo.setSession(session);
            session.getTransaction().begin();
            employee = logInAllRepo.getLoggedEmployeeDetails(userName, password);
            if (session.getTransaction().isActive()) {
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

}
