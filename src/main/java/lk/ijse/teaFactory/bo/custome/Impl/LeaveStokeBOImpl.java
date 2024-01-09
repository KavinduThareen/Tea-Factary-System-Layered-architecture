package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.LeavesStoke;
import lk.ijse.teaFactory.bo.custome.LeaveStokeBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.LeaveStokeDAO;
import lk.ijse.teaFactory.dto.LeavesStokeDto;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.LEAVESTOKE;

public class LeaveStokeBOImpl implements LeaveStokeBO {
    LeaveStokeDAO leaveStokeDAO = (LeaveStokeDAO) DAOFactory.getDaoFactory().getDAO(LEAVESTOKE);
    @Override
    public ArrayList<LeavesStokeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<LeavesStoke> leavesStokes=leaveStokeDAO.getAll();
        ArrayList<LeavesStokeDto> leavesStokeDtos=new ArrayList<>();
        for (LeavesStoke leavesStoke:leavesStokes) {
            leavesStokeDtos.add(new LeavesStokeDto(leavesStoke.getId(),leavesStoke.getWeigth(),leavesStoke.getSDate(),leavesStoke.getEDate()));
        }
        return leavesStokeDtos;
    }

    @Override
    public boolean save(LeavesStokeDto dto) throws SQLException, ClassNotFoundException {
        return leaveStokeDAO.save(new LeavesStoke(dto.getId(),dto.getWeigth(),dto.getSDate(),dto.getEDate()));
    }

    @Override
    public boolean update(LeavesStokeDto dto) throws SQLException, ClassNotFoundException {
        return leaveStokeDAO.update(new LeavesStoke(dto.getId(),dto.getWeigth(),dto.getSDate(),dto.getEDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return leaveStokeDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return leaveStokeDAO.generateID();
    }

    @Override
    public LeavesStoke search(String id) throws SQLException, ClassNotFoundException {
        return leaveStokeDAO.search(id);
    }

    @Override
    public boolean drop(String id, String weigth) throws SQLException {
        return leaveStokeDAO.drop(id,weigth);
    }

    @Override
    public int stokeCount() throws SQLException, ClassNotFoundException {
        return leaveStokeDAO.stokeCount();
    }
}
