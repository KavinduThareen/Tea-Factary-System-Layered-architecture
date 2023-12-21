package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.EmployeDAO;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.EmployeeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAOImpl implements EmployeDAO {

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<EmployeeDto> dtoList = new ArrayList<>();
        while (resultSet.next()){

            String uId = resultSet.getString(1);
            String employeeId = resultSet.getString(2);
            String empGender = resultSet.getString(3);
            Date empbd = resultSet.getDate(4);
            String employeeName = resultSet.getString(5);
            String empAddress = resultSet.getString(6);
            String empContac = resultSet.getString(7);

            var dto = new EmployeeDto(uId,employeeId,empGender,empbd,employeeName,empAddress,empContac);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO employee VALUES(?, ?, ?, ?,?,? ,?)",dto.getUId(),dto.getEmployeeId(),dto.getEmpGender(),dto.getEmpbd(),dto.getEmployeeName(),dto.getEmpAddress(),dto.getEmpContac());
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET user_id = ?, emp_gender = ?, emp_bd = ?,employee_name = ?,address = ?,contac = ? WHERE employeeid = ?",dto.getUId(),dto.getEmpGender(),dto.getEmpbd(),dto.getEmployeeName(),dto.getEmpAddress(),dto.getEmpContac(),dto.getEmployeeId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE employeeid= ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT employeeid FROM employee ORDER BY  employeeid DESC LIMIT 1");
        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentCusId) {    //O008
        if (currentCusId != null) {
            String[] split = currentCusId.split("E");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "E00" + id;
        }
        return "E001";
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE employeeid = ?",id);

        EmployeeDto dto = null;

        if(resultSet.next()) {
            String uId = resultSet.getString(1);
            String employeeId = resultSet.getString(2);
            String empGender = resultSet.getString(3);
            Date empbd = resultSet.getDate(4);
            String employeeName = resultSet.getString(5);
            String empAddress = resultSet.getString(6);
            String empContac = resultSet.getString(7);
            //  String complete = resultSet.getString(8);
            dto = new EmployeeDto(uId,employeeId,empGender,empbd,employeeName,empAddress,empContac);
        }
        return dto;
    }

    public int empCount() throws SQLException {
        int rowCount = 0;
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(employeeid) AS row_count FROM employee";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                rowCount = resultSet.getInt("row_count");
                System.out.println("Number of rows: " + rowCount);
            }
        }
        return rowCount;
    }
}