package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.entity.BookInfo;
import libraryapp.entity.User;
import libraryapp.repository.BookCatalogRepository;
import libraryapp.repository.UserCardRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class LibraryService {
    private final BookCatalogRepository repository;
    private final UserCardRepository userCardRepository;
    private final Map<Integer, List<Integer>> userBorrowedBooksMap;

    public LibraryService(BookCatalogRepository repository, UserCardRepository userCardRepository) {
        this.repository = repository;
        this.userCardRepository = userCardRepository;
        this.userBorrowedBooksMap = new HashMap<>();
    }

    public boolean borrowBookFromLibrary(Integer catalogNumber, int userCardNo) {
        Book book = repository.get(catalogNumber);
        if (book != null) {
            BookInfo bookInfo = book.getBookInfo();
            if (!bookInfo.isInLibrary()) {
                if (bookInfo.getBorrowedTo() == userCardNo)
                    System.out.println("This book is already borrowed to the same reader.");
                else
                    System.out.println("This book is already borrowed to another reader.");
                return false;
            } else {
                bookInfo.setInLibrary(false);
                bookInfo.setBorrowedTo(userCardNo);
                userBorrowedBooksMap.computeIfAbsent(userCardNo, k -> new ArrayList<>()).add(catalogNumber);
                User user = userCardRepository.get(userCardNo).getUser();
                System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been borrowed by " + user.getName() + ".");
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
            userBorrowedBooksMap.values().forEach(list -> list.remove(catalogNumber));
            System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been returned.");
        } else {
            System.out.println("Book with catalog number " + catalogNumber + " is not available in the library.");
        }
    }
}

