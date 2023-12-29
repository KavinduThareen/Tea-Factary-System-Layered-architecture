package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.Entity.Register;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.RegisterDAO;
import lk.ijse.teaFactory.dto.RegisterDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// not complited

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public ArrayList<Register> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");

        ArrayList<Register> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var entity = new Register(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            dtoList.add(entity);
        }
        return dtoList;
    }

    @Override
    public boolean save(Register entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user VALUES(?, ?, ?, ?)",entity.getUserid(),entity.getUsername(),entity.getContac(),entity.getPassword());
    }

    @Override
    public boolean update(Register entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET username = ?, contac = ?, password = ?   WHERE userid = ?",entity.getUsername(),entity.getContac(),entity.getPassword(),entity.getUserid());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM user WHERE userid = ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT userid FROM user ORDER BY userid DESC LIMIT 1");

        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }
    private static String splitOrderId(String currentCusId) {    //O008
        if (currentCusId != null) {
            String[] split = currentCusId.split("U");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "U00" + id;
        }
        return "U001";
    }

    @Override
    public Register search(String id) throws SQLException, ClassNotFoundException {
       return null;
    }

    @Override
    public boolean searchUser(String username, String password) throws SQLException, ClassNotFoundException {
       ResultSet resultSet= SQLUtil.execute( "SELECT * FROM user WHERE username = ? AND password = ?",username,password);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updatepw(String id, String pw) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm  = SQLUtil.execute( "UPDATE user SET password = ?  WHERE userid = ?");
        pstm.setString(1, pw);
        pstm.setString(2, id);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public String findUserIdByUsername(String username) throws SQLException {
            try (ResultSet resultSet = SQLUtil.execute("SELECT userid FROM user WHERE username = ?",username)) {
                if (resultSet.next()) {
                    return resultSet.getString("userid");
                } else {
                    // Handle the case when the username is not found
                    return null; // You can return a special value or throw an exception
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }
}

