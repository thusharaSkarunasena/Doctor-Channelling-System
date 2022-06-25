package lk.ijse.dcs.service.custom.impl;

import lk.ijse.dcs.resources.HibernateUtil;
import lk.ijse.dcs.service.custom.Window_SessionFactoryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Window_SessionFactoryServiceImpl extends UnicastRemoteObject implements Window_SessionFactoryService {

    public Window_SessionFactoryServiceImpl() throws RemoteException {
    }

    @Override
    public void closeSessionFactory() throws Exception {
//        HibernateUtil.getSessionFactory().close();
    }

}
