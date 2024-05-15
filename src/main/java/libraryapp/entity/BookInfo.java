package libraryapp.entity;

import java.time.LocalDate;

public class BookInfo {
    private Integer id; // додане поле id
    private boolean isInLibrary;
    private Integer borrowedTo;
    private LocalDate borrowedDate;
    private Integer borrowedDuration;
    private LocalDate returnDate;

    public BookInfo(){
        this.isInLibrary = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", isInLibrary=" + isInLibrary +
                        ", borrowedTo=" + borrowedTo +
                        ", borrowedDate=" + borrowedDate +
                        ", borrowedDuration=" + borrowedDuration +
                        ", returnDate=" + returnDate;
    }
}