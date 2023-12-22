package bo.impl;

import bo.PLaceOrderBo;
import dao.custom.OrderDetailsDao;
import dao.custom.impl.DaoFactory;
import dao.custom.impl.OrderDetailsDaoImpl;
import dao.custom.util.CrudUtil;
import dao.custom.util.DaoType;

import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBoImpl implements PLaceOrderBo {
    OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAILS);
    @Override
    public boolean saveOrderDetails(List list) {
        try {
            return orderDetailsDao.saveOrderDetails(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
