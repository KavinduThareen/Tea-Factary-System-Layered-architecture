package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeDAOImpl implements CrudDAO<EmployeeDto> {

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO employee VALUES(?, ?, ?, ?,?,? ,?)",dto.getUId(),dto.getEmployeeId(),dto.getEmpGender(),dto.getEmpbd(),dto.getEmployeeName(),dto.getEmpAddress(),dto.getEmpContac());
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
