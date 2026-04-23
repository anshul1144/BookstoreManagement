package service.impl;

import dao.impl.CustomerDAOImpl;
import model.Customer;
import service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAOImpl dao = new CustomerDAOImpl();

    @Override
    public void addCustomer(Customer customer) {
        dao.addCustomer(customer);
    }

    @Override
    public List<Customer> viewCustomers() {
        return dao.getAllCustomers();
    }
}
