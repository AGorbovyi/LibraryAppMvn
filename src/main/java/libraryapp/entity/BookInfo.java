package libraryapp.entity;

import java.time.LocalDate;

public class BookInfo {
    private Integer bookInfoId;
    private boolean isInLibrary;
    private Integer borrowedTo;
    private LocalDate borrowedDate;
    private Integer borrowedDuration;
    private LocalDate returnDate;
    


    public BookInfo(){
        this.isInLibrary = true;
    }

    public Integer getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(Integer bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public boolean isInLibrary() {
        return isInLibrary;
    }

    public void setInLibrary(boolean isInLibrary) {
        this.isInLibrary = isInLibrary;
    }

    public Integer getBorrowedTo() {
        return borrowedTo;
    }

    public void setBorrowedTo(Integer borrowedTo) {
        this.borrowedTo = borrowedTo;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Integer getBorrowedDuration() {
        return borrowedDuration;
    }

    public void setBorrowedDuration(Integer borrowedDuration) {
        this.borrowedDuration = borrowedDuration;
    }

    public LocalDate getReturnDate() {
        if (borrowedDate != null && borrowedDuration != null) {
            returnDate = borrowedDate.plusDays(borrowedDuration);
        }
        return null;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return
                "isInLibrary=" + isInLibrary +
                ", borrowedTo=" + borrowedTo +
                ", borrowedDate=" + borrowedDate +
                ", borrowedDuration=" + borrowedDuration +
                ", returnDate=" + returnDate;
    }
}