package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Salary;
import lk.ijse.teaFactory.Entity.Suppling;
import lk.ijse.teaFactory.bo.custome.SupOrderBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.SupOrdersDAO;
import lk.ijse.teaFactory.dto.SalaryDto;
import lk.ijse.teaFactory.dto.SupOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.SUPPLING;

public class SupOrderBOImpl implements SupOrderBO {
    SupOrdersDAO supOrdersDAO = (SupOrdersDAO) DAOFactory.getDaoFactory().getDAO(SUPPLING);
    @Override
    public ArrayList<SupOrderDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Suppling> supplings=supOrdersDAO.getAll();
        ArrayList<SupOrderDto> supOrderDtos=new ArrayList<>();
        for (Suppling suppling:supplings) {
            supOrderDtos.add(new SupOrderDto(suppling.getId(),suppling.getSId(),suppling.getDate(),suppling.getWeigth(),suppling.getPayment()));
        }
        return supOrderDtos;
    }

    @Override
    public boolean save(SupOrderDto dto) throws SQLException, ClassNotFoundException {
        return supOrdersDAO.save(new Suppling(dto.getId(),dto.getSId(),dto.getDate(),dto.getWeigth(),dto.getPayment()));
    }

    @Override
    public boolean update(SupOrderDto dto) throws SQLException, ClassNotFoundException {
        return supOrdersDAO.update(new Suppling(dto.getId(),dto.getSId(),dto.getDate(),dto.getWeigth(),dto.getPayment()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supOrdersDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return supOrdersDAO.generateID();
    }

    @Override
    public Suppling search(String id) throws SQLException, ClassNotFoundException {
        return supOrdersDAO.search(id);
    }

    @Override
    public boolean dropid(String id, String weigth) throws SQLException {
        return supOrdersDAO.dropid(id,weigth);
    }
}
