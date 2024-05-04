package libraryapp.repository;

import libraryapp.entity.Book;

import java.util.*;
/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */
public class BookCatalogRepository implements CrudRepository<Integer, Book> {
    private Map<Integer, Book> bookMap;

    public BookCatalogRepository () {
        bookMap = new HashMap<>();
    }

    public Map<Integer, Book> getBookMap() {
        return bookMap;
    }


    @Override
    public void put (Book book) {
        bookMap.put(book.getCatalogNumber(), book);
    }

    @Override
    public Book get(Integer catalogNumber) {
        return bookMap.get(catalogNumber);
    }

    @Override
    public void remove(Integer catalogNumber) {
        bookMap.remove(catalogNumber);
    }

    @Override
    public Collection<Book> values() {
        return bookMap.values();
    }

    public void init() {
        List<Book> books = new ArrayList<>(List.of(
                new Book("Taras Shevchenko", "Kobzar", "Poetry", "Kyiv Old Guard", 1),
                new Book("Ivan Franko", "Za dvoma zaitsiamy", "Drama", "Lviv Printing House", 2),
                new Book("Lesia Ukrainka", "Lisova pisnia", "Poetry", "Kyiv Old Guard", 3),
                new Book("Ivan Nechuy-Levytsky", "Pіznorid", "Novel", "Kyiv Old Guard",  4),
                new Book("Mykhailo Kotsiubynsky", "Intermezzo", "Novel", "Kyiv Old Guard",  5),
                new Book("Panas Myrny", "Pisni smutku", "Novel", "Lviv Printing House",  6),
                new Book("Ivan Franko", "Pisni smіlі", "Poetry", "Kyiv Old Guard",  7),
                new Book("Lesia Ukrainka", "Kaminnyi hospodar", "Drama", "Lviv Printing House",  8),
                new Book("Pavlo Tychyna", "Pluh", "Poetry", "Kyiv Old Guard",  9)
        ));
        books.forEach(book -> put(book));
    }
}