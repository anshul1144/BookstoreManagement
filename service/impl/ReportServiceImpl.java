package service.impl;

import config.DBConnection;
import service.ReportService;

import java.sql.*;

public class ReportServiceImpl implements ReportService {

    private Connection con = DBConnection.getConnection();

    @Override
    public void salesReport() {
        try {
            String sql =
                "SELECT s.sale_id, s.sale_date, s.customer_id, " +
                "b.title, sd.quantity, b.price, (sd.quantity * b.price) AS total " +
                "FROM sales s " +
                "JOIN sales_details sd ON s.sale_id = sd.sale_id " +
                "JOIN book b ON sd.book_id = b.book_id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nSALE_ID | DATE | CUSTOMER | BOOK | QTY | PRICE | TOTAL");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("sale_id") + " | " +
                        rs.getDate("sale_date") + " | " +
                        rs.getString("customer_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getInt("quantity") + " | " +
                        rs.getDouble("price") + " | " +
                        rs.getDouble("total")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stockReport() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT book_id, title, stock FROM book");

            System.out.println("\nBOOK_ID | TITLE | STOCK");
            while (rs.next()) {
                System.out.println(
                        rs.getString("book_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lowStockReport(int threshold) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT book_id, title, stock FROM book WHERE stock < ?"
            );
            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nLOW STOCK BOOKS (Below " + threshold + ")");
            System.out.println("BOOK_ID | TITLE | STOCK");
            while (rs.next()) {
                System.out.println(
                        rs.getString("book_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
