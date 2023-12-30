package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.Entity.Otp;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.SuperDAO;
import lk.ijse.teaFactory.dao.customer.OtpDAO;
import lk.ijse.teaFactory.dto.OtpDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtpDAOImpl implements OtpDAO, SuperDAO {
    @Override
    public ArrayList<Otp> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Otp entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Otp VALUES(?)",entity.getOtp());
    }

    @Override
    public boolean update(Otp entity) throws SQLException, ClassNotFoundException {
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
    public Otp search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int load() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Otp";
        PreparedStatement pstm = SQLUtil.execute(sql);

        ResultSet resultSet = pstm.executeQuery();
        int otp = 0;

        while (resultSet.next()) {
            otp = resultSet.getInt(1);
        }
        return otp;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Otp WHERE otp = ?",id);
    }
}
