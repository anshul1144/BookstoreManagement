package con.jdbcconnectivity.BookstoreManagement.service.impl;

import con.jdbcconnectivity.BookstoreManagement.dao.impl.CustomerDAOImpl;
import con.jdbcconnectivity.BookstoreManagement.model.Customer;

import java.util.List;

public class CustomerServiceImpl {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    public void addCustomer(Customer customer) {
        dao.addCustomer(customer);
    }

    public List<Customer> viewCustomers() {
        return dao.getAllCustomers();
    }
}

