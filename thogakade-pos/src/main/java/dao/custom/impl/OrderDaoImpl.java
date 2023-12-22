package dao.custom.impl;

import db.DBConnection;
import dto.OrderDto;
import dao.custom.OrderDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders ORDER BY id DESC LIMIT 1";
        PreparedStatement preparedStatement =DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet =preparedStatement.executeQuery();
        while (resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    null
            );
        }
        return null;
    }
}
