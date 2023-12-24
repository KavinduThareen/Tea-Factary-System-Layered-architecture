package lk.ijse.teaFactory.dao.customer;

import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dto.SupOrderDto;

import java.sql.SQLException;

public interface SupOrdersDAO extends CrudDAO<SupOrderDto> {
    boolean dropid(String id, String weigth) throws SQLException;
}
