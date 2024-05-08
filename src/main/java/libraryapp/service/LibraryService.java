package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.entity.BookInfo;
import libraryapp.repository.BookCatalogRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class LibraryService {
    private BookCatalogRepository repository;
    private Map<Integer, Integer> borrowedBooksMap;

    public LibraryService(BookCatalogRepository repository) {
        this.repository = repository;
        this.borrowedBooksMap = new HashMap<>();
    }

    public boolean borrowBookFromLibrary(Integer catalogNumber, int userCardNo) {
        Book book = repository.get(catalogNumber);
        if (book != null) {
            BookInfo bookInfo = book.getBookInfo();
            if (!bookInfo.isInLibrary()) {
                if (borrowedBooksMap.containsKey(catalogNumber)) {
                    int currentBorrower = borrowedBooksMap.get(catalogNumber);
                    if (currentBorrower == userCardNo) {
                        System.out.println("This book is already borrowed to the same reader.");
                    } else {
                        System.out.println("This book is already borrowed to another reader.");
                    }
                }
                return false;
            } else {
                bookInfo.setInLibrary(false);
                bookInfo.setBorrowedTo(userCardNo);
                borrowedBooksMap.put(catalogNumber, userCardNo);
                System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been borrowed.");
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
            BookInfo bookInfo = book.getBookInfo();
            bookInfo.setInLibrary(true);
            System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been returned.");
            borrowedBooksMap.remove(catalogNumber);
        } else {
            System.out.println("Book with catalog number " + catalogNumber + " is not available in the library.");
        }
    }
}

