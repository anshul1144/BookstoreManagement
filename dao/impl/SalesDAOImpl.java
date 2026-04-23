package dao.impl;

import config.DBConnection;
import dao.SalesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesDAOImpl implements SalesDAO {

    private final Connection con = DBConnection.getConnection();

    @Override
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleId;
    }

    @Override
    public void addSaleDetails(int saleId, String bookId, int qty) {
        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO sales_details(sale_id, book_id, quantity) VALUES (?, ?, ?)"
            );
            ps.setInt(1, saleId);
            ps.setString(2, bookId);
            ps.setInt(3, qty);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
