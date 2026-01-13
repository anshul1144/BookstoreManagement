package con.jdbcconnectivity.BookstoreManagement.service;



import java.util.List;

import con.jdbcconnectivity.BookstoreManagement.model.Book;

public interface BookService {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String bookId);
    List<Book> viewBooks();
}

