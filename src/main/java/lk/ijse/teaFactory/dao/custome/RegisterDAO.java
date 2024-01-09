package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.Register;
import lk.ijse.teaFactory.dao.CrudDAO;

import java.sql.SQLException;

public interface RegisterDAO extends CrudDAO<Register> {

     boolean searchUser(String username, String password) throws SQLException, ClassNotFoundException;
     boolean updatepw(String id, String pw) throws SQLException, ClassNotFoundException;
    String findUserIdByUsername(String username) throws SQLException;
}
