package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.Suppling;
import lk.ijse.teaFactory.dao.CrudDAO;

import java.sql.SQLException;

public interface SupOrdersDAO extends CrudDAO<Suppling> {
    boolean dropid(String id, String weigth) throws SQLException;
}
