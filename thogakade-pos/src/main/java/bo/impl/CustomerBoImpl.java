package bo.impl;

import bo.CustomerBo;
import bo.SuperBo;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.DaoFactory;
import dao.custom.util.DaoType;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo<CustomerDto>,SuperBo {
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                dto.getId(),dto.getName(), dto.getAddress(), dto.getSalary()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(
                dto.getId(),dto.getName(), dto.getAddress(), dto.getSalary()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> entityList=customerDao.GetAll();
        List<CustomerDto> customerDtoList=new ArrayList<>();
        for (Customer customer:entityList) {
            customerDtoList.add(new CustomerDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return customerDtoList;
    }

}
