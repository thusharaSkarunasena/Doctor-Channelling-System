package lk.ijse.dcs.observer;

import java.rmi.Remote;

public interface Observable extends Remote {

    public void register(Observer observer) throws Exception;

    public void unregister(Observer observer) throws Exception;

    public void notifyAllObservers(Observer observer, String primaryKey, String status) throws Exception;

}
