package con.jdbcconnectivity.BookstoreManagement.service.impl;

import con.jdbcconnectivity.BookstoreManagement.config.DBConnection;

import java.sql.*;

public class ReportServiceImpl {

    private Connection con = DBConnection.getConnection();

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
}
