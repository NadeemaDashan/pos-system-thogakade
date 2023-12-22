package dao.custom.impl;

import dao.custom.util.CrudUtil;
import db.DBConnection;
import dto.OrderDetailsDto;
import dao.custom.OrderDetailsDao;
import entity.OrderDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (OrderDetailsDto orderDetails:list) {
            String sql = "INSERT INTO orderdetail VALUES(?,?,?,?)";
            return CrudUtil.execute(sql,orderDetails.getOrderId(),orderDetails.getItemCode(),
                    orderDetails.getQty(),orderDetails.getUnitPrice());
        }
        return false;
    }

    @Override
    public boolean save(OrderDetails entity) {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) {
        return false;
    }

    @Override
    public boolean delete(String value) {
        return false;
    }

    @Override
    public List<OrderDetails> GetAll() {
        return null;
    }
}
