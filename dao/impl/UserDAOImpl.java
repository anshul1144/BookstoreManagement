package con.jdbcconnectivity.BookstoreManagement.dao.impl;

import con.jdbcconnectivity.BookstoreManagement.config.DBConnection;
import con.jdbcconnectivity.BookstoreManagement.model.User;

import java.sql.*;

public class UserDAOImpl {

    private Connection con = DBConnection.getConnection();

    public boolean register(User user) {
        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username, password) VALUES (?, ?)"
            );
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Username already exists!");
        } catch (SQLException e) {
            e.printStackTrace(); // show REAL error
        }
        return false;
    }

    public boolean login(String username, String password) {
        try {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
