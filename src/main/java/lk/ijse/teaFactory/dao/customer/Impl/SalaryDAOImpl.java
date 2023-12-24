package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.SalaryDAO;
import lk.ijse.teaFactory.dto.SalaryDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM salory");

        ArrayList<SalaryDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String empId = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            String count = resultSet.getString(4);
            //  String delete = resultSet.getString(5);

            var dto = new SalaryDto(id,empId,date,count);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salory VALUES(?, ?, ?, ?)",dto.getId(),dto.getEmpId(),dto.getDate(),dto.getCount());
    }

    @Override
    public boolean update(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE salory SET e_id = ?, Date = ?, s_count = ?  WHERE salory_id = ?",dto.getEmpId(),dto.getDate(),dto.getCount(),dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM salory WHERE salory_id = ?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT salory_id FROM salory ORDER BY salory_id DESC LIMIT 1");

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
    public SalaryDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM salory WHERE salory_id = ?");

        SalaryDto dto = null;

        if (resultSet.next()) {
            String resultId = resultSet.getString(1);  // Use a different variable name
            String empid = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            String contac = resultSet.getString(4);


            dto = new SalaryDto(resultId, empid, date, contac);
        }
        return dto;
    }
}
