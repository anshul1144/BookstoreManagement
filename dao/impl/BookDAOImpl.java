package dao.impl;

import config.DBConnection;
import dao.BookDAO;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private final Connection con = DBConnection.getConnection();

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
            System.out.println("Book added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            System.out.println("Book updated successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(String bookId) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM book WHERE book_id=?"
            );
            ps.setString(1, bookId);

            ps.executeUpdate();
            System.out.println("Book deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT book_id, title, price, stock FROM book ORDER BY book_id");

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

    public boolean reduceStockIfAvailable(String bookId, int quantity) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE book SET stock = stock - ? WHERE book_id=? AND stock >= ?"
            );
            ps.setInt(1, quantity);
            ps.setString(2, bookId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
