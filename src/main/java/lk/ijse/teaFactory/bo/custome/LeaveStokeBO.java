package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.LeavesStoke;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.LeavesStokeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LeaveStokeBO extends SuperBO {
    public ArrayList<LeavesStokeDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(LeavesStokeDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(LeavesStokeDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public LeavesStoke search(String id) throws SQLException, ClassNotFoundException;
    public  boolean drop(String id, String weigth) throws SQLException;
    public int stokeCount() throws SQLException, ClassNotFoundException;
}
