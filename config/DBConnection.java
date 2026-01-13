package con.jdbcconnectivity.BookstoreManagement.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/BookstoreManagement_db",
                        "root",
                        "Anshul@123"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
