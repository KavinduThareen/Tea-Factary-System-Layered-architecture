package lk.ijse.teaFactory.dao;

public class DAOFactory {

    private static DAOFactory daoFactory;
    public DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory = new DAOFactory():daoFactory;
    }


}
