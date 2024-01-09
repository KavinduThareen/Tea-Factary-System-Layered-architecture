package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Employee;
import lk.ijse.teaFactory.bo.custome.EmployeeBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.EmployeDAO;
import lk.ijse.teaFactory.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.EMPLOYEE;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeDAO employeDAO = (EmployeDAO) DAOFactory.getDaoFactory().getDAO(EMPLOYEE);
    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
            ArrayList<Employee> items= employeDAO.getAll();
        ArrayList<EmployeeDto> itemDTOS=new ArrayList<>();
        for (Employee employee:items) {
            itemDTOS.add(new EmployeeDto(employee.getUId(),employee.getEmployeeId(),employee.getEmpGender(),employee.getEmpbd(),employee.getEmployeeName(),employee.getEmpAddress(),employee.getEmpContac()));
        }
        return itemDTOS;
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeDAO.save(new Employee(dto.getUId(),dto.getEmployeeId(),dto.getEmpGender(),dto.getEmpbd(),dto.getEmployeeName(),dto.getEmpAddress(),dto.getEmpContac()));
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeDAO.update(new Employee(dto.getUId(),dto.getEmployeeId(),dto.getEmpGender(),dto.getEmpbd(),dto.getEmployeeName(),dto.getEmpAddress(),dto.getEmpContac()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return employeDAO.generateID();
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return employeDAO.search(id);
    }

    @Override
    public int empCount() throws SQLException {
        return employeDAO.empCount();
    }
}
