package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.Entity.PacketStoke;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.PacketDAO;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacketDAOImpl implements PacketDAO
{
    @Override
    public ArrayList<PacketStoke> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM packet_stoke");

        ArrayList<PacketStoke> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var entity = new PacketStoke(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)
            );
            dtoList.add(entity);
        }
        return dtoList;
    }

    @Override
    public boolean save(PacketStoke entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO packet_stoke VALUES(?,?,?,?)",entity.getId(),entity.getCatagory(),entity.getWeigth(),entity.getDate());
    }

    @Override
    public boolean update(PacketStoke entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE packet_stoke SET s_catogary = ?, s_weigth = ?, s_expiredate = ? WHERE packet_id = ?",entity.getCatagory(),entity.getWeigth(),entity.getDate(),entity.getId());
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
    public PacketStoke search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM packet_stoke WHERE packet_id = ?",id);

        PacketStoke entity = null;

        if(resultSet.next()) {
            String lid = resultSet.getString(1);
            String catgary = resultSet.getString(2);
            String weigth = resultSet.getString(3);
            java.util.Date edate = resultSet.getDate(4);


            entity = new PacketStoke(lid,catgary,weigth, (Date) edate);
        }
        return entity;
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
        return SQLUtil.execute("UPDATE packet_stoke SET s_weigth = s_weigth - ? WHERE packet_id = ?",qty,code);
    }
@Override
    public int stokeCount() throws SQLException, ClassNotFoundException {
        int rowCount = 0;
     //   Connection connection = DbConnection.getInstance().getConnection();

     //   String sql = "SELECT SUM(s_weigth) AS total_weight FROM packet_stoke";

      //  try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = SQLUtil.execute("SELECT SUM(s_weigth) AS total_weight FROM packet_stoke");

            if (resultSet.next()) {
                rowCount = resultSet.getInt("total_weight");
                System.out.println("Number of rows: " + rowCount);
            }

        return rowCount;
    }

}
