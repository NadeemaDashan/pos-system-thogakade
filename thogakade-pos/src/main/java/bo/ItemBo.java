package bo;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo<T> extends SuperBo{
     boolean saveItem(T dto) throws SQLException, ClassNotFoundException;
     boolean updateItem(T dto) throws SQLException, ClassNotFoundException;
     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
     List<T> allItems() throws SQLException, ClassNotFoundException;
}
