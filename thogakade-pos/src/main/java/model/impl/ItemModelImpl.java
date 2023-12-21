package model.impl;

import db.DBConnection;
import dto.ItemDto;
import model.ItemModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModelImpl implements ItemModel {
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES('" + dto.getCode() + "','" + dto.getDesc() + "','" + dto.getUnitPrice() + "'," + dto.getQty() + ")";
        PreparedStatement statement =DBConnection.getInstance().getConnection().prepareStatement(sql);
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE code='" + code + "'";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        //executeUpdate changes the database
        return pstm.executeUpdate(sql)>0;
    }

    @Override
    public boolean searchItem(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ItemDto getItem(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        PreparedStatement statement=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ArrayList<ItemDto> itemDtoArrayList=new ArrayList<>();
        ResultSet resultSet =statement.executeQuery();
        while (resultSet.next()){
            ItemDto itemDto=new ItemDto(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4));
            itemDtoArrayList.add(itemDto);
        }
        return itemDtoArrayList;
    }
}
