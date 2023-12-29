package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.Entity.Suppling;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.SupOrdersDAO;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.SupOrderDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupOrderDAOImpl implements SupOrdersDAO {
    @Override
    public ArrayList<Suppling> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier_orders");
        ArrayList<Suppling> dtoList = new ArrayList<>();


        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String sId = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            String weigth = resultSet.getString(4);
            double payment = resultSet.getDouble(5);

            var entity = new Suppling(id, sId, date, weigth,payment);
            dtoList.add(entity);
        }
        return dtoList;
    }

    @Override
    public boolean save(Suppling entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier_orders VALUES(?, ?, ?, ?,?)",entity.getId(),entity.getSId(),entity.getDate(),entity.getWeigth(),entity.getPayment());
    }

    @Override
    public boolean update(Suppling entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier_orders SET sup_id = ?, sup_date = ?, sup_stoke_weigth = ?,payment = ? WHERE s_orders_id = ?",entity.getSId(),entity.getDate(),entity.getWeigth(),entity.getPayment(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier_orders WHERE s_orders_id = ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT s_orders_id FROM supplier_orders ORDER BY  s_orders_id DESC LIMIT 1");

        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentCusId) {    //O008
        if (currentCusId != null) {
            String[] split = currentCusId.split("s");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "s00" + id;
        }
        return "s001";
    }

    @Override
    public Suppling search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier_orders WHERE s_orders_id = ?",id);

        Suppling entity = null;

        if(resultSet.next()) {
            String oid = resultSet.getString(1);
            String sid = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            String weight = resultSet.getString(4);
            double payment = resultSet.getDouble(5);
            //   String complete = resultSet.getString(6);
            entity = new Suppling(oid, sid, date , weight,payment);
        }
        return entity;
    }

    @Override
    public boolean dropid(String id, String weigth) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier_orders SET sup_stoke_weigth = sup_stoke_weigth - ? WHERE s_orders_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, weigth);
        pstm.setString(2, id);

        return pstm.executeUpdate() > 0;
    }
}
