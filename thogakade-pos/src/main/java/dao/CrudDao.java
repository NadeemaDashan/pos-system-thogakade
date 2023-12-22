package dao;

import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> extends SuperDao {
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean delete(String value) throws SQLException, ClassNotFoundException;
    List<T> GetAll() throws SQLException, ClassNotFoundException; // to store get All to load table
}
