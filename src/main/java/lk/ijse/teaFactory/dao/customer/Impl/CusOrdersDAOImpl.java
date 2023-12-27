package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.CusOrdersDAO;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.CusOrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CusOrdersDAOImpl implements CusOrdersDAO {
    @Override
    public ArrayList<CusOrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CusOrderDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?, ?, ?,?,?)",dto.getId(),dto.getCId(),dto.getCatagary(),dto.getWeigth(),dto.getDate(),dto.getDescreption(),dto.getPayment());
    }

    @Override
    public boolean update(CusOrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");

        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentCusId) {    //O008
        if (currentCusId != null) {
            String[] split = currentCusId.split("O");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "O00" + id;
        }
        return "O001";
    }

    @Override
    public CusOrderDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int cusCount() throws SQLException {
        int rowCount = 0;
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(order_id) AS row_count FROM orders";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                rowCount = resultSet.getInt("row_count");
                System.out.println("Number of rows: " + rowCount);
            }
        }
        return rowCount;
    }

    @Override
    public boolean saveOrder(String orderId, String customerId, String catagary, double weigth, LocalDate date, String descreption, String payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?, ?, ?,?,?)",orderId,customerId,catagary,weigth,date,descreption,payment);
    }

}
