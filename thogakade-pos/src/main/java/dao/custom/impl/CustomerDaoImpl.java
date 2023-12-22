package dao.custom.impl;

import dao.custom.util.CrudUtil;
import db.DBConnection;
import dto.CustomerDto;
import dao.custom.CustomerDao;
import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {


    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO customer VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,entity.getId(),entity.getName(),entity.getAddress(),entity.getSalary());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
        PreparedStatement preparedStatement=DBConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getAddress());
        preparedStatement.setDouble(3, entity.getSalary());
        preparedStatement.setString(4, entity.getId());

        return CrudUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getSalary(),entity.getId());
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from customer WHERE id='" + value + "'";
        return CrudUtil.execute(sql);
    }

    @Override
    public List<Customer> GetAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";
        List <Customer> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            list.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return list;
    }
}
