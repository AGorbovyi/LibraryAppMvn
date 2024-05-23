package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.repository.db.BookRepository;
import libraryapp.repository.db.CrudRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class BookService extends Service<CrudRepository, String, BookService> implements IService<CrudRepository, String, BookService> {

    public BookService(HashMap<String, CrudRepository> repository) {
        super(repository);
    }

    public void addBook(String author, String bookTitle, String genre, String publisher) {
        Book book = new Book(author,  bookTitle,  genre,  publisher);
        BookRepository repo = (BookRepository) super.getRepository(BookRepository.class.getSimpleName());
        repo.save(book);
        System.out.println("Book added: " + book);
    }


    public Book get(Integer bookId ) {
        BookRepository repository = (BookRepository) super.getRepository(BookRepository.class.getSimpleName());
        return repository.get(bookId);
    }

    public boolean removeBook(Integer bookId) {
        BookRepository repository = (BookRepository) super.getRepository(BookRepository.class.getSimpleName());
        Book delBook = repository.get(bookId);
        if (delBook != null) {
            repository.remove(delBook.getBookId());
            return true;
        }
        return false;
    }

    public List<Book> findBookByAuthor(String searchQuery) {
        List<Book> result = new ArrayList<>();
        BookRepository repository = (BookRepository) super.getRepository(BookRepository.class.getSimpleName());
        for (Book book : repository.findAll()) {
            if (book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase())) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No books found by this author: " + searchQuery);
        }

        return result;
    }

    public Book findByCatalogNumber(Integer bookId) {
        BookRepository repo = (BookRepository) super.getRepository(BookRepository.class.getSimpleName());
        Book foundBook = repo.get(bookId);
        if (foundBook == null) {
            System.out.println("Book with this catalog number " + bookId + " not found.");
        }
        return foundBook;
    }
    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        BookRepository repo = (BookRepository) super.getRepository(BookRepository.class.getSimpleName());
        for (Book book : repo.findAll()) {
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
        BookRepository repo = (BookRepository) super.getRepository(BookRepository.class.getSimpleName());
        repo.findAll().forEach(System.out::println);
    }

}
