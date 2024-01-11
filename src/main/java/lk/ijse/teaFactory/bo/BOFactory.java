package lk.ijse.teaFactory.bo;

import lk.ijse.teaFactory.bo.custome.Impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory = new BOFactory():boFactory;
    }

    public enum BOType{
        PLASEORDER,CUSTOMER,EMPLOYE,LEAVESTOKE,PACKETSTOKE,PAYMENT,SUPPLIER,SUPPLING,REGISTER,ORDERS,ORDERDETAIL,OTP,SUPPLINGDETAIL,EMPLOYEEATTENEDENT,LOGIN,STOKEDETAIL
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case PLASEORDER:
                return new PlaseOrderBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYE:
                return new EmployeeBOImpl();
            case LEAVESTOKE:
                return new LeaveStokeBOImpl();
            case PACKETSTOKE:
                return new PacketStokeBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLING:
                return new SupOrderBOImpl();
            case REGISTER:
                return new RegisterBOImpl();
            case ORDERS:
                return new OrdersOBImpl();
            case ORDERDETAIL:
                return new OrderDetailBOImpl();
            case OTP:
                return new OtpBOImpl();
            case SUPPLINGDETAIL:
                return new SupplingDetailBOImpl();
            case EMPLOYEEATTENEDENT:
                return new EmployeAttendentBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case STOKEDETAIL:
                return new SupplingDetailBOImpl();
            default:
                return null;
        }

    }
}