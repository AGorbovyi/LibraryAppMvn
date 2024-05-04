package libraryapp.entity;

public class Book {

    private String author;
    private String bookTitle;
    private String genre;
    private String publisher;
    private Integer catalogNumber;
    private boolean isInLibrary;
    private Integer borrowedTo;

    public Book(String author, String bookTitle, String genre, String publisher, Integer catalogNumber) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.publisher = publisher;
        this.catalogNumber = catalogNumber;
        this.isInLibrary = true;
        this.borrowedTo = -1;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }

    public Integer getCatalogNumber() {
        return this.catalogNumber;
    }

    public int getBorrowedTo()  {
        return borrowedTo;
    }

    public boolean isInLibrary() {
        return isInLibrary;
    }

    public void setInLibrary() {
        this.isInLibrary = true;
        this.borrowedTo = -1;
    }

    public void setNotInLibrary(int borrowedTo) {
        this.isInLibrary = false;
        this.borrowedTo = borrowedTo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + this.author + '\'' +
                ", bookTitle='" + this.bookTitle + '\'' +
                ", genre='" + this.genre + '\'' +
                ", publisher='" + this.publisher + '\'' +
                ", catalogNumber=" + this.catalogNumber +
                ", isInLibrary=" + this.isInLibrary +
                '}';
    }
}
