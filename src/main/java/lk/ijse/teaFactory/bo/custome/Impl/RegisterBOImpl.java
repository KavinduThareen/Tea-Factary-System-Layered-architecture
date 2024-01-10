package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Customer;
import lk.ijse.teaFactory.Entity.Register;
import lk.ijse.teaFactory.bo.custome.RegisterBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.RegisterDAO;
import lk.ijse.teaFactory.dto.CustomerDto;
import lk.ijse.teaFactory.dto.RegisterDto;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.REGISTER;

public class RegisterBOImpl implements RegisterBO {
    RegisterDAO registerDAO= (RegisterDAO) DAOFactory.getDaoFactory().getDAO(REGISTER);
    @Override
    public ArrayList<RegisterDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Register> registers=registerDAO.getAll();
        ArrayList<RegisterDto> registerDtos=new ArrayList<>();
        for (Register register:registers) {
            registerDtos.add(new RegisterDto(register.getUserid(),register.getUsername(),register.getContac(),register.getPassword()));
        }
        return registerDtos;
    }

    @Override
    public boolean save(RegisterDto dto) throws SQLException, ClassNotFoundException {
        return registerDAO.save(new Register(dto.getUserid(),dto.getUsername(),dto.getContac(),dto.getPassword()));
    }

    @Override
    public boolean update(RegisterDto dto) throws SQLException, ClassNotFoundException {
        return registerDAO.update(new Register(dto.getUserid(),dto.getUsername(),dto.getContac(),dto.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return registerDAO.generateID();
    }

    @Override
    public Register search(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.search(id);
    }

    @Override
    public boolean searchUser(String username, String password) throws SQLException, ClassNotFoundException {
        return registerDAO.searchUser(username,password);
    }

    @Override
    public boolean updatepw(String id, String pw) throws SQLException, ClassNotFoundException {
        return registerDAO.updatepw(id,pw);
    }

    @Override
    public String findUserIdByUsername(String username) throws SQLException {
        return registerDAO.findUserIdByUsername(username);
    }
}
