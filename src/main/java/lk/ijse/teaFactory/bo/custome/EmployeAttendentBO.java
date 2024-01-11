package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public interface EmployeAttendentBO extends SuperBO {
    boolean markAttendent(String value, LocalDate date, LocalTime time) throws SQLException, ClassNotFoundException;
    boolean delete() throws SQLException, ClassNotFoundException;
    int empAttendes() throws SQLException, ClassNotFoundException;
}
