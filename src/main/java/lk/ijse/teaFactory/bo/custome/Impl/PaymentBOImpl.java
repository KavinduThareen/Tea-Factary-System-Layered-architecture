package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Customer;
import lk.ijse.teaFactory.Entity.Salary;
import lk.ijse.teaFactory.bo.BOFactory;
import lk.ijse.teaFactory.bo.custome.PaymentBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.SalaryDAO;
import lk.ijse.teaFactory.dto.CustomerDto;
import lk.ijse.teaFactory.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.SALARY;

public class PaymentBOImpl implements PaymentBO {
        SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(SALARY);
    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> salaries=salaryDAO.getAll();
        ArrayList<SalaryDto> salaryDtos=new ArrayList<>();
        for (Salary salary:salaries) {
            salaryDtos.add(new SalaryDto(salary.getId(),salary.getEmpId(),salary.getDate(),salary.getCount()));
        }
        return salaryDtos;
    }

    @Override
    public boolean save(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(dto.getId(),dto.getEmpId(),dto.getDate(),dto.getCount()));
    }

    @Override
    public boolean update(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(dto.getId(),dto.getEmpId(),dto.getDate(),dto.getCount()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateID();
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.search(id);
    }
}
