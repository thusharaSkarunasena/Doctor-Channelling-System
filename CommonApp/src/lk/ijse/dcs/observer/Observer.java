package lk.ijse.dcs.observer;

import java.rmi.Remote;

public interface Observer extends Remote {

    public void informDBUpdate(String primaryKey, String status) throws Exception;

}
