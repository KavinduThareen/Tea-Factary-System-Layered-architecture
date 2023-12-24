package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.SupplierDAO;
import lk.ijse.teaFactory.dto.SupplierDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?)",dto.getId(),dto.getName(),dto.getAddress(),dto.getContac());
    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET sup_name = ?, sup_address = ?, sup_contac = ? WHERE supplier_id = ?",dto.getName(),dto.getAddress(),dto.getContac(),dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supplier_id= ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_id FROM supplier ORDER BY  supplier_id DESC LIMIT 1");

        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }
    private static String splitOrderId(String currentCusId) {    //O008
        if (currentCusId != null) {
            String[] split = currentCusId.split("S");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "S00" + id;
        }
        return "S001";
    }

    @Override
    public SupplierDto search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM supplier WHERE supplier_id = ?",id);

        SupplierDto dto = null;

        if(resultSet.next()) {
            String sid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String cantac = resultSet.getString(4);
            //  String complete = resultSet.getString(5);

            dto = new SupplierDto(sid, name, address , cantac);
        }
        return dto;
    }
}
