package controller;

import model.Book;
import service.impl.BookServiceImpl;
import util.InputUtil;

import java.util.Scanner;

public class BookController {

    private final Scanner sc;
    private final BookServiceImpl service = new BookServiceImpl();

    public BookController(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Back");

            int ch = InputUtil.readInt(sc, "Enter choice: ");

            switch (ch) {
                case 1:
                    add();
                    break;

                case 2:
                    view();
                    break;

                case 3:
                    update();
                    break;

                case 4:
                    delete();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void add() {
        String id = InputUtil.readText(sc, "Book ID: ");
        String title = InputUtil.readText(sc, "Title: ");
        double price = InputUtil.readDouble(sc, "Price: ");
        int stock = InputUtil.readInt(sc, "Stock: ");

        service.addBook(new Book(id, title, price, stock));
    }

    private void view() {
        for (Book b : service.viewBooks()) {
            System.out.println(
                    b.getBookId() + " | " +
                    b.getTitle() + " | " +
                    b.getPrice() + " | " +
                    b.getStock()
            );
        }
    }

    private void update() {
        String id = InputUtil.readText(sc, "Book ID: ");
        double price = InputUtil.readDouble(sc, "New Price: ");
        int stock = InputUtil.readInt(sc, "New Stock: ");

        Book b = new Book();
        b.setBookId(id);
        b.setPrice(price);
        b.setStock(stock);

        service.updateBook(b);
    }

    private void delete() {
        service.deleteBook(InputUtil.readText(sc, "Book ID: "));
    }
}
