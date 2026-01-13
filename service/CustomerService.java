package con.jdbcconnectivity.BookstoreManagement.service;
import con.jdbcconnectivity.BookstoreManagement.dao.CustomerDAOImpl;
import con.jdbcconnectivity.BookstoreManagement.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    public void addCustomer(Customer customer) {
        dao.addCustomer(customer);
    }

    public List<Customer> viewCustomers() {
        return dao.getAllCustomers();
    }
}


