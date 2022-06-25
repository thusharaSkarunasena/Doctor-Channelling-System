package lk.ijse.dcs.repo;

import java.util.List;

public interface CrudRepository<T, ID> extends SuperRepository {

    public void save(T entity) throws Exception;

    public void delete(T entity) throws Exception;

    public void update(T entity) throws Exception;

    public T search(ID id) throws Exception;

    public List<T> getAll() throws Exception;

}
