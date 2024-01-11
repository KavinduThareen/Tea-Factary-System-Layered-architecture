package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.bo.custome.StokeDetailBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.StokeDetailDAO;

import java.sql.Date;
import java.sql.SQLException;

public class StokeDetailBOImpl implements StokeDetailBO {
    StokeDetailDAO stokeDetailDAO = (StokeDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STOKEDETAIL);
    @Override
    public boolean detail(String pid, String lid, Date date) throws SQLException, ClassNotFoundException {
        return stokeDetailDAO.detail(pid,lid,date);
    }
}
