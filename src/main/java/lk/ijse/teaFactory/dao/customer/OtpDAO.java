package lk.ijse.teaFactory.dao.customer;

import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dto.OtpDto;

import java.sql.SQLException;

public interface OtpDAO extends CrudDAO<OtpDto> {
    int load() throws SQLException, ClassNotFoundException;
     boolean delete(int id) throws SQLException, ClassNotFoundException;
}