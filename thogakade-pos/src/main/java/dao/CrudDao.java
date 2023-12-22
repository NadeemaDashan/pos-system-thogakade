package dao;

import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> {
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(String value);
    List<T> GetAll(); // to store get All to load table
}
