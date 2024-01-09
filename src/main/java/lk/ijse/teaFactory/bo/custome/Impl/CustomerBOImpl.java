package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Customer;
import lk.ijse.teaFactory.bo.custome.CustomerBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.CustomerDAO;
import lk.ijse.teaFactory.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.CUSTOMER;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(CUSTOMER);
    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDto> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDto(customer.getCusid(),customer.getEmpid(),customer.getCusname(),customer.getCusAddress(),customer.getCusCantac()));
        }
        return customerDTOS;
    }

    @Override
    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return  customerDAO.save(new Customer(dto.getCusid(),dto.getEmpid(),dto.getCusname(),dto.getCusAddress(),dto.getCusCantac()));
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCusid(),dto.getEmpid(),dto.getCusname(),dto.getCusAddress(),dto.getCusCantac()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateID();
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }


    public List<CustomerDto> loadAllItems() throws SQLException, ClassNotFoundException {
        return null;
    }
}
