package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.entity.BookInfo;
import libraryapp.repository.BookCatalogRepository;


import java.util.List;



/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class BookCatalogService {

    private BookCatalogRepository repository;

    public BookCatalogService(BookCatalogRepository repository) {
        this.repository = repository;
    }

    public void addBook(String author, String bookTitle, String genre, String publisher) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setInLibrary(true);

        Book book = new Book(null, author, bookTitle, genre, publisher, bookInfo);

        repository.save(book);

        System.out.println("Book added: " + book);
    }

    public Book get(Integer bookId) {
        return repository.get(bookId);
    }

    public boolean removeBook(Integer bookId) {
        return repository.remove(bookId);
    }

    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    public Book findByCatalogNumber(Integer bookId) {
        return repository.get(bookId);
    }

    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public void printCatalog() {
        repository.findAll().forEach(System.out::println);
    }
}