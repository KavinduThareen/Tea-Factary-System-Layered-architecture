package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.Otp;
import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dao.SuperDAO;

import java.sql.SQLException;

public interface OtpDAO extends SuperDAO {
    public boolean save(Otp entity) throws SQLException, ClassNotFoundException;
    int load() throws SQLException, ClassNotFoundException;
     boolean delete(int id) throws SQLException, ClassNotFoundException;
}
