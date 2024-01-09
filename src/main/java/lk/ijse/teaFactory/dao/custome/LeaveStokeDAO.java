package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.LeavesStoke;
import lk.ijse.teaFactory.dao.CrudDAO;

import java.sql.SQLException;

public interface LeaveStokeDAO extends CrudDAO<LeavesStoke> {
    public  boolean drop(String id, String weigth) throws SQLException;
    public int stokeCount() throws SQLException, ClassNotFoundException;

}
