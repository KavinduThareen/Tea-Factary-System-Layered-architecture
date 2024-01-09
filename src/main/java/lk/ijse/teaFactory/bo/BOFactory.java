package lk.ijse.teaFactory.bo;

import lk.ijse.teaFactory.bo.custome.Impl.CustomerBOImpl;
import lk.ijse.teaFactory.bo.custome.Impl.EmployeeBOImpl;
import lk.ijse.teaFactory.bo.custome.Impl.LeaveStokeBOImpl;
import lk.ijse.teaFactory.bo.custome.Impl.PlaseOrderBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory = new BOFactory():boFactory;
    }

    public enum BOType{
        PLASEORDER,CUSTOMER,EMPLOYE,LEAVESTOKE
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
            default:
                return null;
        }

    }
}