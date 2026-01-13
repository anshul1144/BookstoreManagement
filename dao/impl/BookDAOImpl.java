package con.jdbcconnectivity.BookstoreManagement.dao.impl;

import con.jdbcconnectivity.BookstoreManagement.config.DBConnection;
import con.jdbcconnectivity.BookstoreManagement.dao.BookDAO;
import con.jdbcconnectivity.BookstoreManagement.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private Connection con = DBConnection.getConnection();

    // ================= ADD BOOK =================
    @Override
    public void addBook(Book book) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO book(book_id, title, price, stock) VALUES (?, ?, ?, ?)"
            );
            ps.setString(1, book.getBookId());
            ps.setString(2, book.getTitle());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getStock());

            ps.executeUpdate();
            System.out.println("✅ Book added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE BOOK =================
    @Override
    public void updateBook(Book book) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE book SET price=?, stock=? WHERE book_id=?"
            );
            ps.setDouble(1, book.getPrice());
            ps.setInt(2, book.getStock());
            ps.setString(3, book.getBookId());

            ps.executeUpdate();
            System.out.println("✅ Book updated successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE BOOK =================
    @Override
    public void deleteBook(String bookId) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM book WHERE book_id=?"
            );
            ps.setString(1, bookId);

            ps.executeUpdate();
            System.out.println("✅ Book deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW ALL BOOKS =================
    @Override
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM book");

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                list.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= UPDATE STOCK AFTER SALE =================
    public void updateStock(String bookId, int quantity) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE book SET stock = stock - ? WHERE book_id=?"
            );
            ps.setInt(1, quantity);
            ps.setString(2, bookId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
