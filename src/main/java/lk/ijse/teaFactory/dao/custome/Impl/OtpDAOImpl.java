package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.Entity.Otp;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.SuperDAO;
import lk.ijse.teaFactory.dao.custome.OtpDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtpDAOImpl implements OtpDAO {

    @Override
    public boolean save(Otp entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Otp VALUES(?)",entity.getOtp());
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
