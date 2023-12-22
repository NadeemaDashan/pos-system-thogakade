package dao.custom.impl;

import dao.custom.util.CrudUtil;
import db.DBConnection;
import dto.OrderDto;
import dao.custom.OrderDao;
import entity.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orderdetail ORDER BY id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
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

    @Override
    public boolean save(Orders entity) {
        return false;
    }

    @Override
    public boolean update(Orders entity) {
        return false;
    }

    @Override
    public boolean delete(String value) {
        return false;
    }

    @Override
    public List<Orders> GetAll() {
        return null;
    }
}
