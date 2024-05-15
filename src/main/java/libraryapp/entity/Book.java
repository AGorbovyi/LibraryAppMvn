package libraryapp.entity;


public class Book {
    private Integer id;
    private String author;
    private String bookTitle;
    private String genre;
    private String publisher;
    private BookInfo bookInfo;

    public Book(Integer id, String author, String bookTitle, String genre, String publisher, Object bookInfo) {
        this.id = null;
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.publisher = publisher;
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

    public Integer getId() {
        return id;
    }

    public BookInfo getBookInfo() {
        return this.bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + getId() +
                ", author='" + getAuthor() + '\'' +
                ", bookTitle='" + getBookTitle() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", " + (bookInfo == null ? "available" : bookInfo) +
                '}';
    }
}