package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Customer;
import lk.ijse.teaFactory.Entity.PacketStoke;
import lk.ijse.teaFactory.bo.BOFactory;
import lk.ijse.teaFactory.bo.custome.PacketStokeBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.PacketDAO;
import lk.ijse.teaFactory.dto.CustomerDto;
import lk.ijse.teaFactory.dto.PacketStokeDto;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.PACKETSTOKE;


public class PacketStokeBOImpl implements PacketStokeBO {
 PacketDAO packetDAO = (PacketDAO) DAOFactory.getDaoFactory().getDAO(PACKETSTOKE);
    @Override
    public ArrayList<PacketStokeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PacketStoke> packetStokes=packetDAO.getAll();
        ArrayList<PacketStokeDto> packetStokeDtos=new ArrayList<>();
        for (PacketStoke packetStoke:packetStokes) {
            packetStokeDtos.add(new PacketStokeDto(packetStoke.getId(),packetStoke.getCatagory(),packetStoke.getWeigth(),packetStoke.getDate()));
        }
        return packetStokeDtos;
    }

    @Override
    public boolean save(PacketStokeDto dto) throws SQLException, ClassNotFoundException {
        return packetDAO.save(new PacketStoke(dto.getId(),dto.getCatagory(),dto.getWeigth(),dto.getDate()));
    }

    @Override
    public boolean update(PacketStokeDto dto) throws SQLException, ClassNotFoundException {
        return packetDAO.update(new PacketStoke(dto.getId(),dto.getCatagory(),dto.getWeigth(),dto.getDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return packetDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return packetDAO.generateID();
    }

    @Override
    public PacketStoke search(String id) throws SQLException, ClassNotFoundException {
        return packetDAO.search(id);
    }

    @Override
    public boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        return packetDAO.updateItem(cartTmList);
    }

    @Override
    public int stokeCount() throws SQLException, ClassNotFoundException {
        return packetDAO.stokeCount();
    }
}
