package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.Entity.Supplier;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.SupplierDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contac = resultSet.getString(4);
            //  String isCompleted = resultSet.getString(5);

            var entity = new Supplier(id, name, address, contac);
            dtoList.add(entity);
        }
        return dtoList;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getContac());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET sup_name = ?, sup_address = ?, sup_contac = ? WHERE supplier_id = ?",entity.getName(),entity.getAddress(),entity.getContac(),entity.getId());
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
    public Supplier search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM supplier WHERE supplier_id = ?",id);

        Supplier entity = null;

        if(resultSet.next()) {
            String sid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String cantac = resultSet.getString(4);
            //  String complete = resultSet.getString(5);

            entity = new Supplier(sid, name, address , cantac);
        }
        return entity;
    }
}
