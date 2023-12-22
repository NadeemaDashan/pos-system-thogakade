package model.impl;

import db.DBConnection;
import dto.OrderDetailsDto;
import model.OrderDetailsModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsModelImpl implements OrderDetailsModel {
    @Override
    public boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (OrderDetailsDto orderDetails:list) {
            String sql = "INSERT INTO orderdetail VALUES(?,?,?,?)";
            PreparedStatement preparedStatement =DBConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, orderDetails.getOrderId());
            preparedStatement.setString(2, orderDetails.getItemCode());
            preparedStatement.setInt(3,orderDetails.getQty());
            preparedStatement.setDouble(4,orderDetails.getUnitPrice());

            i=preparedStatement.executeUpdate();
        }
        return i>0;
    }
}
