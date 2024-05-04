package libraryapp.service;
/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */
import libraryapp.entity.Book;
import libraryapp.repository.BookCatalogRepository;

import java.util.ArrayList;
import java.util.List;

public class BookCatalogService {
    private BookCatalogRepository repository;

    public BookCatalogService(BookCatalogRepository repository) {
        this.repository = repository;
    }

    public int addBook(String author, String bookTitle, String genre, String publisher) {
        int nextCatalogNumber = repository.values().size() + 1;
        Book book = new Book(author,  bookTitle,  genre,  publisher, nextCatalogNumber);
        repository.put(book);
        return nextCatalogNumber;
    }


    public Book get(Integer catalogNumber ) {
        return repository.get(catalogNumber);
    }

    public boolean removeBook(Integer catalogNumber) {
        Book delBook = repository.get(catalogNumber);
        if (delBook != null) {
            repository.remove(catalogNumber);
            return true;
        }
        return false;
    }

    public List<Book> findBookByAuthor(String searchQuery) {
        List<Book> result = new ArrayList<>();

        for (Book book : repository.values()) {
            if (book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase())) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No books found with this author: " + searchQuery);
        }

        return result;
    }

    public Book findByCatalogNumber(int catalogNumber) {
        Book foundBook = repository.get(catalogNumber);
        if (foundBook == null) {
            System.out.println("Book with this catalog number " + catalogNumber + " not found.");
        }
        return foundBook;
    }
    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : repository.values()) {
            if (book.getBookTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No books found with this title: " + title);
        }
        return result;
    }

    public void printCatalog () {
        repository.values().forEach(System.out::println);
    }
}
