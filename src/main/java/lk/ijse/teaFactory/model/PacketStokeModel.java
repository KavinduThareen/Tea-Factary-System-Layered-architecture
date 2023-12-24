
package lk.ijse.teaFactory.model;

import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.LeavesStokeDto;
import lk.ijse.teaFactory.dto.PacketStokeDto;
import lk.ijse.teaFactory.dto.tm.CartTm;
import lk.ijse.teaFactory.dto.tm.CusOrderTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacketStokeModel {


    public boolean updateItem(List<CartTm> cartTmList) throws SQLException {
        for(CartTm tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getItemId(), String.valueOf(tm.getWeigth()))) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(String code, String qty) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE packet_stoke SET s_weigth = s_weigth - ? WHERE packet_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0; //false
    }





}
