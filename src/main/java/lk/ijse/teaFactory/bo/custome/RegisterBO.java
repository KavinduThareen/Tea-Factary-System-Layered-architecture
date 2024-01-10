package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.Register;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.RegisterDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterBO extends SuperBO {
    public ArrayList<RegisterDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(RegisterDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(RegisterDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public Register search(String id) throws SQLException, ClassNotFoundException;
    public boolean searchUser(String username, String password) throws SQLException, ClassNotFoundException;
    public boolean updatepw(String id, String pw) throws SQLException, ClassNotFoundException;
    public String findUserIdByUsername(String username) throws SQLException;
}
