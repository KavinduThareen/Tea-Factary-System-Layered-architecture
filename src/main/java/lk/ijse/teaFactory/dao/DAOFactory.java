package lk.ijse.teaFactory.dao;

import lk.ijse.teaFactory.dao.custome.Impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    public DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOType{
        CUSORDERS,CUSTOMER,EMPLOYEE,LEAVESTOKE,LOGINDETAIL,ORDERDETAIL,OTP,PACKETSTOKE,REGISTER,SALARY,STOKEDETAIL,SUPPLING,SUPPLIER,SUPPLINGDETAIL,EMPLOYEEATTENDENTS
    }

    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();

            case CUSORDERS:
                return new CusOrdersDAOImpl();

            case EMPLOYEE:
                return new EmployeDAOImpl();

            case LEAVESTOKE:
                return new LeavesStokeDAOImpl();

            case LOGINDETAIL:
                return new LoginDetailDAOImpl();

            case ORDERDETAIL:
                return new OrderDetailDAOImpl();

            case OTP:
                return new OtpDAOImpl();

            case PACKETSTOKE:
                return new PacketDAOImpl();

            case REGISTER:
                return new RegisterDAOImpl();

            case SALARY:
                return new SalaryDAOImpl();

            case STOKEDETAIL:
                return new StokeDetailDAOImpl();

            case SUPPLING:
                return new SupOrderDAOImpl();

            case SUPPLIER:
                return new SupplierDAOImpl();

            case SUPPLINGDETAIL:
                return new SupplingDetailDAOImpl();

            case EMPLOYEEATTENDENTS:
                return new EmployeeAttendensDAOImpl();

            default:
                return null;
        }


    }





}
