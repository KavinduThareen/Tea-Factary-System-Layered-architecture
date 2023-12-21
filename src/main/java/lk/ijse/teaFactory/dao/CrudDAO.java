package lk.ijse.teaFactory.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException ;
    boolean update(T dto) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateID() throws SQLException, ClassNotFoundException ;
    public T search(String id) throws SQLException, ClassNotFoundException;
}
