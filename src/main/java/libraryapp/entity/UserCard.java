package libraryapp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

public class UserCard {
    private User user;
    private List<Book> borrowedBooks;
    private int booksLimit;
    private boolean isClosed;
    private  int maxBooksLimit;

    public UserCard(User user) {
        this.user = user;
        this.borrowedBooks = new ArrayList<>();
        this.booksLimit = 5;
        this.isClosed = false;
    }
    public void borrowBook(Book book) {
            borrowedBooks.add(book);
            booksLimit--;
    }
    public boolean returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            booksLimit++;
            return true;
        } else {
            System.out.println("The book is not borrowed by this reader.");
            return false;
        }
    }

    public void closeCard() {
        borrowedBooks.clear();
        this.isClosed = true;
    }

    public List<Book> getUserBorrowedBooks() {
        return borrowedBooks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getUserBookList() {
        return borrowedBooks;
    }

    public void setUserBookList(List<Book> userBookList) {
        this.borrowedBooks = userBookList;
    }

    public int getBooksLimit() {
        return booksLimit;
    }

    public void setBooksLimit(int booksLimit) {
        this.booksLimit = booksLimit;
    }
    public int getMaxBooksLimit() {
        return 5;
    }

    public void reopenCard() {
        this.borrowedBooks = new ArrayList<>();
        this.isClosed = false;
    }

    public UUID getUserId() {
        return this.user.getUserId();
    }

    @Override
    public String toString() {
        return "UserCard{" +
                user +
                ", borrowedBooks=" + borrowedBooks +
                ", booksLimit=" + booksLimit +
                ", isClosed=" + isClosed +
                '}';
    }
}