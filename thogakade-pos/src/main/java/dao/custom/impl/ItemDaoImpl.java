package dao.custom.impl;

import dao.custom.util.CrudUtil;

import dao.custom.ItemDao;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES('" + entity.getCode() + "','" + entity.getDesc() + "','" + entity.getUnitPrice() + "'," + entity.getQty() + ")";
        return CrudUtil.execute(sql);

    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET code='" + entity.getCode() + "', `description`='" + entity.getDesc() + "', unitPrice='" + entity.getUnitPrice() + "', qty='" + entity.getQty() + "' WHERE code = '" + entity.getCode() + "'";
        return CrudUtil.execute(sql);

    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE code='" + value + "'";
        return CrudUtil.execute(sql);
    }

    @Override
    public List<Item> GetAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        ArrayList<Item> itemArrayList=new ArrayList<>();
        ResultSet resultSet =CrudUtil.execute(sql);
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
