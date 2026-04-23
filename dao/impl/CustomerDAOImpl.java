package dao.impl;

import config.DBConnection;
import dao.CustomerDAO;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private final Connection con = DBConnection.getConnection();

    @Override
    public void addCustomer(Customer customer) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO customer(customer_id, name, phone) VALUES(?,?,?)"
            );
            ps.setString(1, customer.getCustomerId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getPhone());
            ps.executeUpdate();
            System.out.println("Customer added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT customer_id, name, phone FROM customer ORDER BY customer_id"
            );
            while (rs.next()) {
                list.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean existsCustomer(String customerId) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT 1 FROM customer WHERE customer_id = ?"
            );
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
