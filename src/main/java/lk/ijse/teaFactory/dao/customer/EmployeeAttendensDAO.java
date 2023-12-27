package lk.ijse.teaFactory.dao.customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public interface EmployeeAttendensDAO {
    boolean markAttendent(String value, LocalDate date, LocalTime time) throws SQLException;
    boolean isValueExists(Connection connection, String value) throws SQLException;
    boolean deleteDuplicateValue(Connection connection, String value) throws SQLException;
    boolean delete() throws SQLException;
    int empAttendes() throws SQLException;
}
