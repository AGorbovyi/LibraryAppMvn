import libraryapp.entity.Book;
import libraryapp.entity.User;
import libraryapp.entity.UserCard;
import libraryapp.repository.CrudRepository;
import libraryapp.service.LibraryService;
import libraryapp.repository.BookCatalogRepository;
import libraryapp.repository.UserCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class TestLibraryService {

        private LibraryService libraryService;
        private UserCardRepository userCardRepository;
        private BookCatalogRepository bookCatalogRepository;

        @BeforeEach
        void setUp() {
            userCardRepository = new UserCardRepository();
            bookCatalogRepository = new BookCatalogRepository();

            Book book = new Book("John Smith", "Java Programming", "Programming", "Publisher");
            bookCatalogRepository.put(book);

            User user = new User("John", " Doe");
            UserCard userCard = new UserCard(user);
            userCardRepository.put(userCard);

            HashMap<String, CrudRepository> repositories = new HashMap<>();
            repositories.put(BookCatalogRepository.class.getSimpleName(), bookCatalogRepository);
            repositories.put(UserCardRepository.class.getSimpleName(), userCardRepository);

            libraryService = new LibraryService(repositories);
        }


        @Test
        @Order(1)
        void testBorrowBookFromLibrary() {
            Integer bookId = bookCatalogRepository.getBookMap().get(0).getId();
            Integer userCardId = userCardRepository.getUserCards().get(0).getUserId();

            libraryService.borrowBookFromLibrary(bookId, userCardId);

            Book borrowedBook = bookCatalogRepository.get(bookId);
            assertFalse(borrowedBook.getBookInfo().isInLibrary());

            assertEquals(userCardId, borrowedBook.getBookInfo().getBorrowedTo());
        }

        @Test
        @Order(2)
        void testReturnBookToLibrary() {
            UUID bookId = bookCatalogRepository.getBookMap().get(0).getId();
            UUID userCardId = userCardRepository.getUserCards().get(0).getUserId();

            libraryService.borrowBookFromLibrary(bookId, userCardId);

            libraryService.returnBookToLibrary(bookId);

            Book returnedBook = bookCatalogRepository.get(bookId);
            assertTrue(returnedBook.getBookInfo().isInLibrary());
        }
    }