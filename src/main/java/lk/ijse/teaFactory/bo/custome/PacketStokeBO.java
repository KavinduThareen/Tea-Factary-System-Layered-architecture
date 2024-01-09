package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.PacketStoke;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.PacketStokeDto;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PacketStokeBO extends SuperBO {
    public ArrayList<PacketStokeDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(PacketStokeDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(PacketStokeDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public PacketStoke search(String id) throws SQLException, ClassNotFoundException;
    public boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    public int stokeCount() throws SQLException, ClassNotFoundException;
}
