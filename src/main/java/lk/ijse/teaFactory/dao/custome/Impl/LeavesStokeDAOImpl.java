package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.Entity.LeavesStoke;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.LeaveStokeDAO;
import lk.ijse.teaFactory.db.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class LeavesStokeDAOImpl implements LeaveStokeDAO {
    @Override
    public ArrayList<LeavesStoke> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM leaves_stoke");
        ArrayList<LeavesStoke> dtoList = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String weigth = resultSet.getString(2);
            Date sDate = resultSet.getDate(3);
            Date eDate = resultSet.getDate(4);
            //   String isCompleted = resultSet.getString(5);

            var entity = new LeavesStoke(id,weigth,sDate,eDate);
            dtoList.add(entity);
        }

        return dtoList;
    }

    @Override
    public boolean save(LeavesStoke entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO leaves_stoke VALUES(?, ?, ?, ?)",entity.getId(),entity.getWeigth(),entity.getSDate(),entity.getEDate());
    }

    @Override
    public boolean update(LeavesStoke entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE leaves_stoke SET l_weigth = ?, l_suppli_date = ?, l_s_expiredate = ? WHERE leaves_s_id = ?",entity.getWeigth(),entity.getSDate(),entity.getEDate(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM leaves_stoke WHERE leaves_s_id = ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT leaves_s_id FROM leaves_stoke ORDER BY  leaves_s_id DESC LIMIT 1");

        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentCusId) {    //O008
        if (currentCusId != null) {
            String[] split = currentCusId.split("L");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "L00" + id;
        }
        return "L001";
    }

    @Override
    public LeavesStoke search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM leaves_stoke WHERE leaves_s_id = ?",id);
        LeavesStoke entity = null;
        if(resultSet.next()) {
            String lid = resultSet.getString(1);
            String weigth = resultSet.getString(2);
            Date sdate = resultSet.getDate(3);
            Date edate = resultSet.getDate(4);
            //   String complete = resultSet.getString(5);
            entity = new LeavesStoke(lid,weigth,sdate,edate);
        }
        return entity;
    }
@Override
    public  boolean drop(String id, String weigth) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE leaves_stoke SET l_weigth = l_weigth - ? WHERE leaves_s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, weigth);
        pstm.setString(2, id);

        return pstm.executeUpdate() > 0;
    }
@Override
    public int stokeCount() throws SQLException, ClassNotFoundException {
        int rowCount = 0;
             ResultSet resultSet = SQLUtil.execute("SELECT SUM(l_weigth) AS total_weight FROM leaves_stoke");
            if (resultSet.next()) {
                rowCount = resultSet.getInt("total_weight");
                System.out.println("Number of rows: " + rowCount);
            }
        return rowCount;
    }

}
