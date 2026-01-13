package con.jdbcconnectivity.BookstoreManagement.dao.impl;

import con.jdbcconnectivity.BookstoreManagement.config.DBConnection;
import con.jdbcconnectivity.BookstoreManagement.dao.CustomerDAO;
import con.jdbcconnectivity.BookstoreManagement.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection con = DBConnection.getConnection();

    public void addCustomer(Customer customer) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO customer VALUES(?,?,?)");
            ps.setString(1, customer.getCustomerId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getPhone());
            ps.executeUpdate();
            System.out.println("Customer Added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                list.add(new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

