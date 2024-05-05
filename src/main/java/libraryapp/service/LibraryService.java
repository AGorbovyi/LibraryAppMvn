package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.repository.BookCatalogRepository;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class LibraryService {
    private final BookCatalogRepository repository;

    public LibraryService(BookCatalogRepository repository) {
        this.repository = repository;
    }


    public boolean borrowBookFromLibrary(Integer catalogNumber, int userCardNo, String userName) {
        Book book = repository.get(catalogNumber);
        if (book != null) {
            if (!book.isInLibrary()) {
                if (book.getBorrowedTo() == userCardNo)
                    System.out.println("This book is already borrowed to the same reader.");
                else
                    System.out.println("This book is already borrowed to another reader.");
                return false;
            } else {
                book.setNotInLibrary(userCardNo);
                System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been borrowed by" + userName + ".");
                return true;
            }
        } else {
            System.out.println("Book with catalog number " + catalogNumber + " is not available in the library.");
            return false;
        }
    }

    public void returnBookToLibrary(Integer catalogNumber) {
        Book book = repository.get(catalogNumber);
        if (book != null) {
            book.setInLibrary();
            System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been returned.");
        } else {
            System.out.println("Book with catalog number " + catalogNumber + " is not available in the library.");
        }
    }
}

