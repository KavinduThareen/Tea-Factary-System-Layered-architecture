package lk.ijse.teaFactory.bo;

import lk.ijse.teaFactory.bo.customer.Impl.PlaseOrderBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getDaoFactory(){
        return (boFactory==null)?boFactory = new BOFactory():boFactory;
    }

    public enum BOType{
        PLASEORDER
    }

    public SuperBO getBO(BOType boType){

        switch (boType){
            case PLASEORDER:
                return new PlaseOrderBOImpl();
            default:
                return null;
        }

    }
}