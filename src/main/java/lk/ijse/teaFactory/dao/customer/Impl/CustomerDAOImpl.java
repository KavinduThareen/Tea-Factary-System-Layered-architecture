package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.CustomerDAO;
import lk.ijse.teaFactory.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDto> allCustomer = new ArrayList<>();

        while (resultSet.next()) {
            String cusid = resultSet.getString(1);
            String empId = resultSet.getString(2);
            String cusname = resultSet.getString(3);
            String cusaddress = resultSet.getString(4);
            String contac = resultSet.getString(5);
            // String isCompleted = resultSet.getString(6);

            var dto = new CustomerDto(cusid,empId,cusname,cusaddress,contac);
            allCustomer.add(dto);
        }
        return allCustomer;

    }

    @Override
    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?)",dto.getCusid(),dto.getEmpid(),dto.getCusname(),dto.getCusAddress(),dto.getCusCantac());
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET emp_id = ?, cus_name = ?, cus_address = ? ,cus_cantac = ?  WHERE customer_id = ?",dto.getCusid(),dto.getEmpid(),dto.getCusname(),dto.getCusAddress(),dto.getCusCantac());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM customer WHERE customer_id = ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT customer_id FROM customer ORDER BY  customer_id DESC LIMIT 1"   );
        if (rst.next()) {
            String id = rst.getString("customer_id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "C001";
        }
    }

    @Override
    public CustomerDto search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id = ?",id);

        CustomerDto dto = null;

        if(resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String empid = resultSet.getString(2);
            String name = resultSet.getString(3);
            String address = resultSet.getString(4);
            String cantac = resultSet.getString(5);
            dto = new CustomerDto(cus_id, empid, name , address,cantac);
        }
        return dto;
    }

    public int cusCount() throws SQLException, ClassNotFoundException {
        int rowCount = 0;

             ResultSet resultSet = SQLUtil.execute("SELECT COUNT(customer_id) AS row_count FROM customer");

            if (resultSet.next()) {
                rowCount = resultSet.getInt("row_count");
                System.out.println("Number of rows: " + rowCount);
            }

            return rowCount;
    }

    public static List<CustomerDto> loadAllItems() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        List<CustomerDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );

            dtoList.add(dto);
        }
        return dtoList;
    }

}
