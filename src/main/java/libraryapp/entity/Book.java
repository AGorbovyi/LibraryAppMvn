package libraryapp.entity;


public class Book {
    private Integer id;
    private String author;
    private String bookTitle;
    private String genre;
    private String publisher;
    private BookInfo bookInfo;

    public Book(Integer id, String author, String bookTitle, String genre, String publisher, BookInfo bookInfo) {
        this.id = id;
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.publisher = publisher;
        this.bookInfo = bookInfo;
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

    public void setId(Integer id) {
        this.id = id;
        if (this.bookInfo != null) {
            this.bookInfo.setId(id); // Синхронізація id з bookInfo
        }
    }

    public BookInfo getBookInfo() {
        return this.bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
        if (this.id != null) {
            this.bookInfo.setId(this.id); // Синхронізація id з bookInfo
        }
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