package libraryapp.entity;

import java.time.LocalDate;
import java.util.UUID;

public class BookInfo {
    private boolean isInLibrary;
    private UUID borrowedTo;
    private LocalDate borrowedDate;
    private Integer borrowedDuration;
    private LocalDate returnDate;
    

    public BookInfo(){
        this.isInLibrary = true;
    }

    public boolean isInLibrary() {
        return isInLibrary;
    }

    public void setInLibrary(boolean isInLibrary) {
        this.isInLibrary = isInLibrary;
    }

    public UUID getBorrowedTo() {
        return borrowedTo;
    }

    public void setBorrowedTo(UUID borrowedTo) {
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
        return returnDate;
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