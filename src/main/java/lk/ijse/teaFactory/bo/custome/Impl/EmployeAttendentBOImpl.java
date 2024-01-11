package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.bo.custome.EmployeAttendentBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.EmployeeAttendensDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeAttendentBOImpl implements EmployeAttendentBO {
    EmployeeAttendensDAO employeeAttendensDAO = (EmployeeAttendensDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEEATTENDENTS);
    @Override
    public boolean markAttendent(String value, LocalDate date, LocalTime time) throws SQLException, ClassNotFoundException {
        return employeeAttendensDAO.markAttendent(value,date,time);
    }
    @Override
    public boolean delete() throws SQLException, ClassNotFoundException {
        return employeeAttendensDAO.delete();
    }

    @Override
    public int empAttendes() throws SQLException, ClassNotFoundException {
        return employeeAttendensDAO.empAttendes();
    }
}
