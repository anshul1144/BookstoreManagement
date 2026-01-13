package con.jdbcconnectivity.BookstoreManagement.model;

public class Book {
    private String bookId;
    private String title;
    private double price;
    private int stock;

    public Book() {}

    public Book(String bookId, String title, double price, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setBookId(String bookId) { this.bookId = bookId; }
    public void setTitle(String title) { this.title = title; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}
