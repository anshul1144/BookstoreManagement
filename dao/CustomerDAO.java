package con.jdbcconnectivity.BookstoreManagement.dao;

import java.util.List;

import con.jdbcconnectivity.BookstoreManagement.model.Customer;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
}

