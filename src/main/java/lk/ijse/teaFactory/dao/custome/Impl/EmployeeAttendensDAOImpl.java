package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.EmployeeAttendensDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

// notcomplete


public class EmployeeAttendensDAOImpl implements EmployeeAttendensDAO {
    @Override
    public boolean markAttendent(String value, LocalDate date, LocalTime time) throws SQLException, ClassNotFoundException {
        if (isValueExists( value)) {
            System.out.println("Duplicate value found: " + value);

            // Delete the existing duplicate value
            if (deleteDuplicateValue(value)) {
                System.out.println("Existing duplicate value deleted: " + value);
            } else {
                System.out.println("Failed to delete existing duplicate value: " + value);
                return false;  // You may choose to handle this case in your own way
            }
        }

        return SQLUtil.execute("INSERT INTO emp_attendens VALUES (?, ?, ?)", value, date, time);
    }

    @Override
    public boolean isValueExists( String value) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM emp_attendens WHERE attendent = ?",value);
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }
        return false;
    }

    @Override
    public boolean deleteDuplicateValue( String value) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM emp_attendens WHERE attendent = ?",value);
    }

    @Override
    public boolean delete() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM emp_attendens");
    }

    @Override
    public int empAttendes() throws SQLException, ClassNotFoundException {
        int rowCount = 0;
    ResultSet resultSet = SQLUtil.execute("SELECT COUNT(attendent) AS total_attendent FROM emp_attendens");

            if (resultSet.next()) {
                rowCount = resultSet.getInt("total_attendent");
                System.out.println("Number of rows: " + rowCount);
            }

        return rowCount;
    }

}
