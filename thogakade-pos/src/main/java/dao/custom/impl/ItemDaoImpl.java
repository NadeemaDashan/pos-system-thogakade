package dao.custom.impl;

import db.DBConnection;
import dto.ItemDto;
import dao.custom.ItemDao;
import entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
//    @Override
//    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO item VALUES('" + dto.getCode() + "','" + dto.getDesc() + "','" + dto.getUnitPrice() + "'," + dto.getQty() + ")";
//        PreparedStatement statement =DBConnection.getInstance().getConnection().prepareStatement(sql);
//        return statement.executeUpdate()>0;
//    }
//
//    @Override
//    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
//        String sql = "UPDATE item SET code='" + dto.getCode() + "', `description`='" + dto.getDesc() + "', unitPrice='" + dto.getUnitPrice() + "', qty='" + dto.getQty() + "' WHERE code = '" + dto.getCode() + "'";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        return stm.executeUpdate()>0;
//    }
//
//    @Override
//    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
//        String sql = "DELETE FROM item WHERE code='" + code + "'";
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        return pstm.executeUpdate(sql)>0;
//    }
//    @Override
//    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
//        String sql = "SELECT * FROM item";
//        PreparedStatement statement=DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ArrayList<ItemDto> itemDtoArrayList=new ArrayList<>();
//        ResultSet resultSet =statement.executeQuery();
//        while (resultSet.next()){
//            ItemDto itemDto=new ItemDto(resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDouble(3),
//                    resultSet.getInt(4));
//            itemDtoArrayList.add(itemDto);
//        }
//        return itemDtoArrayList;
//    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES('" + entity.getCode() + "','" + entity.getDesc() + "','" + entity.getUnitPrice() + "'," + entity.getQty() + ")";
        PreparedStatement statement =DBConnection.getInstance().getConnection().prepareStatement(sql);
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET code='" + entity.getCode() + "', `description`='" + entity.getDesc() + "', unitPrice='" + entity.getUnitPrice() + "', qty='" + entity.getQty() + "' WHERE code = '" + entity.getCode() + "'";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE code='" + value + "'";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        return pstm.executeUpdate(sql)>0;
    }

    @Override
    public List<Item> GetAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        PreparedStatement statement=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ArrayList<Item> itemArrayList=new ArrayList<>();
        ResultSet resultSet =statement.executeQuery();
        while (resultSet.next()){
            Item item=new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4));
            itemArrayList.add(item);
        }
        return itemArrayList;
    }
}
