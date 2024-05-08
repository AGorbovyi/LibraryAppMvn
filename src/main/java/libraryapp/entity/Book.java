package libraryapp.entity;
import java.time.LocalDate;

public class Book {
    private String author;
    private String bookTitle;
    private String genre;
    private String publisher;
    private Integer catalogNumber;
    private BookInfo bookInfo; // Доданий об'єкт BookInfo

    public Book(String author, String bookTitle, String genre, String publisher, Integer catalogNumber) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.publisher = publisher;
        this.catalogNumber = catalogNumber;
        this.bookInfo = new BookInfo(); // Ініціалізуємо об'єкт BookInfo
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(Integer catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", catalogNumber=" + catalogNumber +
                ", bookInfo=" + bookInfo +
                '}';
    }
}