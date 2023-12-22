package bo;

import bo.impl.CustomerBoImpl;
import bo.impl.ItemBoImpl;
import bo.impl.PlaceOrderBoImpl;
import dao.custom.util.BoType;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory!=null?boFactory:(boFactory=new BoFactory());
    }
    public<T extends SuperBo>T getBo(BoType type){
        switch (type){
            case ITEM:return (T) new ItemBoImpl();
            case CUSTOMER:return (T) new CustomerBoImpl();
            case ORDER:return (T) new PlaceOrderBoImpl();
            case ORDER_DETAILS:return (T) new PlaceOrderBoImpl();
        }
        return null;
    }

}
