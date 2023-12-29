package lk.ijse.teaFactory.dao;

import lk.ijse.teaFactory.dao.customer.Impl.CustomerDAOImpl;
import lk.ijse.teaFactory.dao.customer.Impl.EmployeDAOImpl;

import javax.swing.plaf.PanelUI;

public class DAOFactory {

    private static DAOFactory daoFactory;
    public DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOType{
        CUSTOMER,EMPLOYEE,LEAVESTOKE,LOGINDETAIL,ORDERDETAIL,OTP,PACKETSTOKE,REGISTER,SALARY,STOKEDETAIL,SUPPLING,SUPPLIER,PUPPLINGDETAIL
    }
/*
    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();

            case EMPLOYEE:
                return new EmployeDAOImpl();

            case ORDER:
                return new OrderDAOImpl();

            case ORDER_DETAIL:
                return new OrderDetailsDAOImpl();

            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

 */

}
