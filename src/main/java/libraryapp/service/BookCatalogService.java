package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.repository.BookCatalogRepository;
import libraryapp.repository.CrudRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class BookCatalogService extends Service<CrudRepository, String, BookCatalogService> implements IService<CrudRepository, String, BookCatalogService> {

    public BookCatalogService(HashMap<String, CrudRepository> repository) {
        super(repository);
    }

    public void addBook(String author, String bookTitle, String genre, String publisher) {
        Book book = new Book(author,  bookTitle,  genre,  publisher);
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        repo.put(book);
        System.out.println("Book added: " + book);
    }


    public Book get(UUID bookId ) {
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        return repo.get(bookId);
    }

    public boolean removeBook(UUID bookId) {
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        Book delBook = repo.get(bookId);
        if (delBook != null) {
            repo.remove(delBook);
            return true;
        }
        return false;
    }

    public List<Book> findBookByAuthor(String searchQuery) {
        List<Book> result = new ArrayList<>();
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        for (Book book : repo.values()) {
            if (book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase())) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No books found by this author: " + searchQuery);
        }

        return result;
    }

    public Book findByCatalogNumber(UUID bookId) {
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        Book foundBook = repo.get(bookId);
        if (foundBook == null) {
            System.out.println("Book with this catalog number " + bookId + " not found.");
        }
        return foundBook;
    }
    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        for (Book book : repo.values()) {
            if (book.getBookTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No books found by this title: " + title);
        }
        return result;
    }

    public void printCatalog () {
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        repo.values().forEach(System.out::println);
    }

}
