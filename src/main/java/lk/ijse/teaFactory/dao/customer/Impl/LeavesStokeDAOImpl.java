package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.LeaveStokeDAO;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.EmployeeDto;
import lk.ijse.teaFactory.dto.LeavesStokeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeavesStokeDAOImpl implements LeaveStokeDAO {
    @Override
    public ArrayList<LeavesStokeDto> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM leaves_stoke");
        ArrayList<LeavesStokeDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String weigth = resultSet.getString(2);
            Date sDate = resultSet.getDate(3);
            Date eDate = resultSet.getDate(4);
            //   String isCompleted = resultSet.getString(5);

            var dto = new LeavesStokeDto(id,weigth,sDate,eDate);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public boolean save(LeavesStokeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO leaves_stoke VALUES(?, ?, ?, ?)",dto.getId(),dto.getWeigth(),dto.getSDate(),dto.getEDate());
    }

    @Override
    public boolean update(LeavesStokeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE leaves_stoke SET l_weigth = ?, l_suppli_date = ?, l_s_expiredate = ? WHERE leaves_s_id = ?",dto.getWeigth(),dto.getSDate(),dto.getEDate(),dto.getId());
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
    public LeavesStokeDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM leaves_stoke WHERE leaves_s_id = ?",id);

        LeavesStokeDto dto = null;

        if(resultSet.next()) {
            String lid = resultSet.getString(1);
            String weigth = resultSet.getString(2);
            Date sdate = resultSet.getDate(3);
            Date edate = resultSet.getDate(4);
            //   String complete = resultSet.getString(5);

            dto = new LeavesStokeDto(lid,weigth,sdate,edate);
        }
        return dto;
    }

    public  boolean drop(String id, String weigth) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE leaves_stoke SET l_weigth = l_weigth - ? WHERE leaves_s_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, weigth);
        pstm.setString(2, id);

        return pstm.executeUpdate() > 0;
    }

    public int stokeCount() throws SQLException {
        int rowCount = 0;
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SUM(l_weigth) AS total_weight FROM leaves_stoke";

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
