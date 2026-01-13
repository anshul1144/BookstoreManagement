package con.jdbcconnectivity.BookstoreManagement.dao.impl;

import con.jdbcconnectivity.BookstoreManagement.config.DBConnection;

import java.sql.*;

public class SalesDAOImpl {

    private Connection con = DBConnection.getConnection();

    // CREATE SALE
    public int createSale(String customerId) {
        int saleId = 0;
        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO sales(customer_id, sale_date) VALUES (?, CURDATE())",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, customerId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                saleId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleId;
    }

    // ADD SALE DETAILS
    public void addSaleDetails(int saleId, String bookId, int qty) {
        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO sales_details VALUES (?, ?, ?)"
            );
            ps.setInt(1, saleId);
            ps.setString(2, bookId);
            ps.setInt(3, qty);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
