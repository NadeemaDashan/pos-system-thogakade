package dao.custom.impl;

import dao.SuperDao;
import dao.custom.util.DaoType;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory!=null?daoFactory:(daoFactory=new DaoFactory());
    }
    public<T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case ORDER:return (T) new OrderDaoImpl();
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case ITEM:return (T) new ItemDaoImpl();
            case ORDER_DETAILS:return (T) new OrderDetailsDaoImpl();
        }
        return null;
    }
}
