package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.Entity.Employee;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.EmployeDAO;
import lk.ijse.teaFactory.db.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class EmployeDAOImpl implements EmployeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> dtoList = new ArrayList<>();
        while (resultSet.next()){

            String uId = resultSet.getString(1);
            String employeeId = resultSet.getString(2);
            String empGender = resultSet.getString(3);
            Date empbd = resultSet.getDate(4);
            String employeeName = resultSet.getString(5);
            String empAddress = resultSet.getString(6);
            String empContac = resultSet.getString(7);

            var dto = new Employee(uId,employeeId,empGender,empbd,employeeName,empAddress,empContac);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO employee VALUES(?, ?, ?, ?,?,? ,?)",entity.getUId(),entity.getEmployeeId(),entity.getEmpGender(),entity.getEmpbd(),entity.getEmployeeName(),entity.getEmpAddress(),entity.getEmpContac());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET user_id = ?, emp_gender = ?, emp_bd = ?,employee_name = ?,address = ?,contac = ? WHERE employeeid = ?",entity.getUId(),entity.getEmpGender(),entity.getEmpbd(),entity.getEmployeeName(),entity.getEmpAddress(),entity.getEmpContac(),entity.getEmployeeId());
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
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE employeeid = ?",id);

        Employee entity = null;

        if(resultSet.next()) {
            String uId = resultSet.getString(1);
            String employeeId = resultSet.getString(2);
            String empGender = resultSet.getString(3);
            Date empbd = resultSet.getDate(4);
            String employeeName = resultSet.getString(5);
            String empAddress = resultSet.getString(6);
            String empContac = resultSet.getString(7);
            //  String complete = resultSet.getString(8);
            entity = new Employee(uId,employeeId,empGender,empbd,employeeName,empAddress,empContac);
        }
        return entity;
    }
@Override
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
