package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.customer.EmployeeAttendensDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

// notcomplete


public class EmployeeAttendensDAOImpl implements EmployeeAttendensDAO {
    @Override
    public boolean markAttendent(String value, LocalDate date, LocalTime time) throws SQLException {

        return false;
    }

    @Override
    public boolean isValueExists(Connection connection, String value) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteDuplicateValue(Connection connection, String value) throws SQLException {
        return false;
    }

    @Override
    public boolean delete() throws SQLException {
        return false;
    }

    @Override
    public int empAttendes() throws SQLException {
        return 0;
    }
}
