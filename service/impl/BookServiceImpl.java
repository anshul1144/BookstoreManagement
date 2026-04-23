package service.impl;
import dao.impl.BookDAOImpl;
import model.Book;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAOImpl dao = new BookDAOImpl();

    public void addBook(Book book) { dao.addBook(book); }
    public void updateBook(Book book) { dao.updateBook(book); }
    public void deleteBook(String bookId) { dao.deleteBook(bookId); }
    public List<Book> viewBooks() { return dao.getAllBooks(); }
}

