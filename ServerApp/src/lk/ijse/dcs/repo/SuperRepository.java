package lk.ijse.dcs.repo;

import org.hibernate.Session;

public interface SuperRepository {
    public void setSession(Session session) throws Exception;
}
