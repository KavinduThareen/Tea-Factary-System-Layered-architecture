package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.bo.custome.SupplingDetailBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.SupplingDetailDAO;

import java.sql.Date;
import java.sql.SQLException;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.SUPPLINGDETAIL;

public class SupplingDetailBOImpl implements SupplingDetailBO {
    SupplingDetailDAO supplingDetailDAO = (SupplingDetailDAO) DAOFactory.getDaoFactory().getDAO(SUPPLINGDETAIL);
    @Override
    public boolean detail(String sid, String id, Date sDate) throws SQLException, ClassNotFoundException {
        return supplingDetailDAO.detail(sid,id,sDate);
    }
}
