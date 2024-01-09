package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.dao.SuperDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public interface EmployeeAttendensDAO extends SuperDAO {
    boolean markAttendent(String value, LocalDate date, LocalTime time) throws SQLException, ClassNotFoundException;
    boolean isValueExists( String value) throws SQLException, ClassNotFoundException;
    boolean deleteDuplicateValue( String value) throws SQLException, ClassNotFoundException;
    boolean delete() throws SQLException, ClassNotFoundException;
    int empAttendes() throws SQLException, ClassNotFoundException;
}
