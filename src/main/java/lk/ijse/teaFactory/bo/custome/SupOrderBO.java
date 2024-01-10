package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.Suppling;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.SupOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupOrderBO extends SuperBO {
    public ArrayList<SupOrderDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(SupOrderDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(SupOrderDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public Suppling search(String id) throws SQLException, ClassNotFoundException;
    public boolean dropid(String id, String weigth) throws SQLException;
}
