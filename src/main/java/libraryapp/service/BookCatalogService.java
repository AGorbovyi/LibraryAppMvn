package libraryapp.service;

import libraryapp.entity.Book;
import libraryapp.repository.BookCatalogRepository;
import libraryapp.repository.CrudRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        Book book = new Book(rs.getInt("id"), author,  bookTitle,  genre,  publisher, rs.getInt("bookInfo"));
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        repo.save(book);
        System.out.println("Book added: " + book);
    }


    public Book get(Integer bookId ) {
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        return repo.get(bookId);
    }

    public boolean remove(Integer bookId) {
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        Book delBook = repo.get(bookId);
        if (delBook != null) {
            repo.remove(delBook.getId());
            return true;
        }
        return false;
    }

    public List<Book> findBookByAuthor(String searchQuery) {
        List<Book> result = new ArrayList<>();
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        for (Book book : repo.findAll()) {
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
        BookCatalogRepository repo = (BookCatalogRepository) super.getRepository(BookCatalogRepository.class.getSimpleName());
        repo.findAll().forEach(System.out::println);
    }

}
