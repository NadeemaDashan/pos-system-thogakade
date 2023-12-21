package model.impl;

import dto.OrderDto;
import model.OrderModel;

import java.sql.SQLException;

public class OrderModelImpl implements OrderModel {

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        return null;
    }
}
