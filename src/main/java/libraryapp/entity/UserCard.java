package libraryapp.entity;
/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

import java.util.ArrayList;
import java.util.List;

public class UserCard {
    private User user;
    private List<Book> borrowedBooks;
    private int booksLimit;
    private boolean isClosed;

    public UserCard(User user) {
        this.user = user;
        this.borrowedBooks = new ArrayList<>();
        this.isClosed = false;
    }
    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() < booksLimit) {
            borrowedBooks.add(book);
            return true;
        } else {
            System.out.println("You have reached the limit of borrowed books.");
            return false;
        }
    }
    public boolean returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            return true;
        } else {
            System.out.println("The book is not borrowed by this user.");
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

    public void reopenCard() {
        this.borrowedBooks = new ArrayList<>();
        this.isClosed = false;
    }

    public void setClosed(boolean b) {
    }

    public Integer getUserId() {
        return this.user.getUserId();
    }

    @Override
    public String toString() {
        return "UserCard{" +
                "user=" + user +
                ", borrowedBooks=" + borrowedBooks +
                ", booksLimit=" + booksLimit +
                ", isClosed=" + isClosed +
                '}';
    }
}