package model.impl;

import dto.OrderDetailsDto;
import model.OrderDetailsModel;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsModelImpl implements OrderDetailsModel {
    @Override
    public boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException {
        return false;
    }
}
