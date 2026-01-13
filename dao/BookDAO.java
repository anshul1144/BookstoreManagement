package con.jdbcconnectivity.BookstoreManagement.dao;

import java.util.List;

import con.jdbcconnectivity.BookstoreManagement.model.Book;

public interface BookDAO {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String bookId);
    List<Book> getAllBooks();
}
