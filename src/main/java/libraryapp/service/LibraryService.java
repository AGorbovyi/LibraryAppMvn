package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.entity.User;
import libraryapp.repository.BookCatalogRepository;
import libraryapp.repository.UserCardRepository;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class LibraryService {
    private final BookCatalogRepository repository;
    private UserCardRepository userCardRepository;

    public LibraryService(BookCatalogRepository repository) {
        this.repository = repository;
    }


    public void borrowBookFromLibrary(Integer catalogNumber, int userCardNo) {
        Book book = repository.get(catalogNumber);
        User userName = userCardRepository.get(userCardNo).getUser();
        if (book != null) {
            if (!book.isInLibrary()) {
                if (book.getBorrowedTo() == userCardNo)
                    System.out.println("This book is already borrowed to the same reader.");
                else
                    System.out.println("This book is already borrowed to another reader.");
            } else {
                book.setNotInLibrary(userCardNo);
                System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been borrowed by" + userName + ".");
            }
        } else {
            System.out.println("Book with catalog number " + catalogNumber + " is not available in the library.");
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

