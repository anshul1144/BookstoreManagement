package dao;

import java.util.List;

import model.Customer;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
}

