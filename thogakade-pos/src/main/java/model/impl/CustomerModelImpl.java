package model.impl;

import db.DBConnection;
import dto.CustomerDto;
import model.CustomerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModelImpl implements CustomerModel {

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement =DBConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,dto.getId());
        preparedStatement.setString(2,dto.getName());
        preparedStatement.setString(3,dto.getAddress());
        preparedStatement.setString(4, String.valueOf(dto.getSalary()));

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from customer WHERE id='" + id + "'";
        PreparedStatement preparedStatement =DBConnection.getInstance().getConnection().prepareStatement(sql);
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";
        List <CustomerDto> list = new ArrayList<>();
        PreparedStatement preparedStatement=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet =preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return list;
    }
}
