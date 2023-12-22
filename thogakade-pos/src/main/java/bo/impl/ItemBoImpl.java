package bo.impl;

import bo.ItemBo;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.DaoFactory;
import dao.custom.impl.ItemDaoImpl;
import dao.custom.util.DaoType;
import dto.ItemDto;
import entity.Item;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo<ItemDto> {
    ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                dto.getCode(),
                dto.getDesc(),
                dto.getUnitPrice(),
                dto.getQty()
        ));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(
                dto.getCode(),
                dto.getDesc(),
                dto.getUnitPrice(),
                dto.getQty()
        ));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.delete(code);
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<Item>item=itemDao.GetAll();
        List<ItemDto> itemDtoList=new ArrayList<>();
        for (Item item1:item) {
            itemDtoList.add(new ItemDto(
                    item1.getCode(),
                    item1.getDesc(),
                    item1.getUnitPrice(),
                    item1.getQty()
            ));
        }
        return itemDtoList;
    }
}
