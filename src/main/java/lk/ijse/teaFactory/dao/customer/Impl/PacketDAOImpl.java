package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.PacketDAO;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.EmployeeDto;
import lk.ijse.teaFactory.dto.PacketStokeDto;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacketDAOImpl implements PacketDAO {
    @Override
    public ArrayList<PacketStokeDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM packet_stoke");

        ArrayList<PacketStokeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new PacketStokeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(PacketStokeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO packet_stoke VALUES(?,?,?,?)",dto.getId(),dto.getCatagory(),dto.getWeigth(),dto.getDate());
    }

    @Override
    public boolean update(PacketStokeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE packet_stoke SET s_catogary = ?, s_weigth = ?, s_expiredate = ? WHERE packet_id = ?",dto.getCatagory(),dto.getWeigth(),dto.getDate(),dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM packet_stoke WHERE packet_id = ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT packet_id FROM packet_stoke ORDER BY packet_id DESC LIMIT 1");

        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentCusId) {
        if (currentCusId != null) {
            String[] split = currentCusId.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            return "P00" + id;
        }
        return "P001";
    }

    @Override
    public PacketStokeDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM packet_stoke WHERE packet_id = ?",id);

        PacketStokeDto dto = null;

        if(resultSet.next()) {
            String lid = resultSet.getString(1);
            String catgary = resultSet.getString(2);
            String weigth = resultSet.getString(3);
            java.util.Date edate = resultSet.getDate(4);


            dto = new PacketStokeDto(lid,catgary,weigth, (Date) edate);
        }
        return dto;
    }

    @Override
    public boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(CartTm tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getItemId(), String.valueOf(tm.getWeigth()))) {
                return false;
            }
        }
        return true;
    }
    public boolean updateQty(String code, String qty) throws SQLException, ClassNotFoundException {
        int rowsAffected = SQLUtil.execute( "UPDATE packet_stoke SET s_weigth = s_weigth - ? WHERE packet_id = ?",code,qty);

        return rowsAffected > 0; //false
    }

    public int stokeCount() throws SQLException {
        int rowCount = 0;
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SUM(s_weigth) AS total_weight FROM packet_stoke";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                rowCount = resultSet.getInt("total_weight");
                System.out.println("Number of rows: " + rowCount);
            }
        }
        return rowCount;
    }

}
