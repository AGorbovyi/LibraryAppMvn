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
    private final Map<Integer, Book> bookMap;

    public BookCatalogRepository () {
        bookMap = new HashMap<>();
    }

    public Map<Integer, Book> getBookMap() {
        return bookMap;
    }


    @Override
    public void put (Book book) {
        Integer id = bookMap.size() + 1;
        book.setBookId(id);
        bookMap.put(id, book);
    }

    @Override
    public Book get(Integer bookId) {
        for (var book : bookMap.values()){
            if (book.getBookId().equals(bookId)){
                return book;
            }
        }
        return null;
    }

    @Override
    public void remove(Book book) {
        System.out.println("The number of books in the library catalog is " + bookMap.size());
        if (bookMap.containsValue(book)) {
            bookMap.remove(book.getBookId());
        }
        System.out.println("The number of books in the library catalog is after removing a book is " + bookMap.size());
    }

    @Override
    public Iterable<Book> values() {
        System.out.println("The size of repo is " + bookMap.size());
        return bookMap.values();
    }
    @Override
    public void init() {
        List<Book> books = new ArrayList<>(List.of(
                new Book("Taras Shevchenko", "Kobzar", "Poetry", "Kyiv Old Guard"),
                new Book("Ivan Franko", "Za dvoma zaitsiamy", "Drama", "Lviv Printing House"),
                new Book("Lesia Ukrainka", "Lisova pisnia", "Poetry", "Kyiv Old Guard"),
                new Book("Ivan Nechuy-Levytsky", "Pіznorid", "Novel", "Kyiv Old Guard"),
                new Book("Mykhailo Kotsiubynsky", "Intermezzo", "Novel", "Kyiv Old Guard"),
                new Book("Panas Myrny", "Pisni smutku", "Novel", "Lviv Printing House"),
                new Book("Ivan Franko", "Pisni smіlі", "Poetry", "Kyiv Old Guard"),
                new Book("Lesia Ukrainka", "Kaminnyi hospodar", "Drama", "Lviv Printing House"),
                new Book("Pavlo Tychyna", "Pluh", "Poetry", "Kyiv Old Guard")
        ));
        books.forEach(this::put);
    }
}