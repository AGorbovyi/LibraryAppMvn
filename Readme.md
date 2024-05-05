# LibraryApp - групповой проект команды №2

===================================================
Short description
===================================================

LibraryApp - приложение управления библиотекой. Приложением пользуется администратор библиотеки, который:
- управляет каталогом книг: добавляет, удаляет, ищет
- администрирует карточки читателей: создает карточку нового клиента, просматривает её и может "закрыть карточку" - доступ к каталогу библиотеки. 
- выдает книги и оформляет их возврат в библиотеку. 


===================================================
Сущности (entity)
===================================================

   **Book**
   - `author`: String
   - `bookTitle`: String
   - `genre`: String
   - `publisher`: String
   - `catalogNumber`: int - уникальный идентификатор записи
   - `isInLibrary`: boolean - флаг, который показывает книга в хранилище или на руках
   - `borrowedTo`:

   **User**
   - `name`: String
   - `surname`: String
   - `userId`: int

   **UserCard**
   - `user`: User
   - `userBookList`: List<Book>
   - `booksLimit`: int
   - `findUserCardByName`
   - `checkBooksLimit`: void
   - `borrowBook(Book book)`: boolean
   - `returnBook(Book book)`: boolean
   - `closeCard()`: void
   - `reopenUserCard()`: void

===================================================
Репозитории (repository)
===================================================

   **BookCatalogRepository**
   - `books`: Map<Book> (поиск по `catalogNumber`, `bookTitle`)
   - `addBook(Book book)`: void
   - `removeBookByCatalogNumber(int catalogNumber)`: boolean
   - `findBookById(String searchQuery, String type)`: Map<Book>
   - `findBookByAuthor(String searchQuery, String type)`: Map<Book>
   - `findBookByBookTitle(String searchQuery, String type)`: Map<Book>
   - `printCatalog()`: void

   **CrudRepository**
   - `UserInput`

   **UserCardRepository**
   - `UserInput`

===================================================
Методы (service)
===================================================

   **BookCatalogService**
   - `books`: Map<Book> (поиск по `catalogNumber`, `bookTitle`)
   - `addBook(Book book)`: void
   - `removeBookByCatalogNumber(int catalogNumber)`: boolean
   - `findBookById(String searchQuery, String type)`: Map<Book>
   - `findBookByAuthor(String searchQuery, String type)`: Map<Book>
   - `findBookByBookTitle(String searchQuery, String type)`: Map<Book>
   - `printCatalog()`: void

   **LibraryService**
   - `addNewUserCard(User user, int limit)`: void
   - `closeUserCard(int userId)`: boolea
   - `reopenUserCard(int userId)`: boolean

   **UserCardService**
   - `addNewUserCard(User user, int limit)`: void
   - `closeUserCard(int userId)`: boolean
   - `reopenUserCard(int userId)`: boolean

===================================================
Util
===================================================


===================================================
UserInterface (ui)
===================================================

   **LibraryApp**
   - `bookCatalog`: BookCatalog
   - `userCards`: Map<Integer, UserCard>
   - `borrowBookFromLibrary(int userId, int catalogNumber)`: boolean
   - `returnBookToLibrary(int userId, int catalogNumber)`: boolean
   - `exitMenu`: void

   **UserMenu**
   - `UserMenu`

===================================================
(опционально) Страница авторизации администратора
===================================================

При запуске программы LibraryApp в консоли выводится текст:
"Please enter your login details"
"Username:"
Вводим с клавиатуры Username. После ввода программа запрашивает пароль.

"Password:"
Вводим с клавиатуры Password.

В случае ошибки при вводе пароля и логина выводится сообщение: "Invalid Password! Please try again!" или "Invalid Username! Please try again!", или "Invalid Username & Password! Please try again!"
После чего администратор возвращается на шаг ввода данных Username.

Если логин и пароль соответствует заранее заданным переменным, то выводится сообщение:"Welcome back!"
И в консоли выводится главное меню приложения.
  
  
===================================================
Главное меню приложения.
===================================================

App menu:
[1] Add new book
[2] View book catalog
[3] Find a book by catalog number
[4] Find a book by author (BookCatalog)
[5] Find a book by book title
[6] Create a new reader card
[7] View reader card
[8] Close reader card
[9] Reopen reader card
[10] Borrow a book from the library
[11] Return the book to the library
[12] Remove book from catalog
[13] Exit App

Please select menu item:

===================================================

Описание пунктов меню:

"1. Add new book"- запускает метод addBook, добавляет новую книгу в список BookCatalog через UserInput
"2. View book catalog" - запускает метод printCatalog - показать список книг
"3. Find a book by catalog number" - запускает метод findBookById - поиск книги по номеру в каталоге (BookCatalog).
"4. Find a book by author" - запускает метод findBookByAuthor - поиск книги по автору в каталоге (BookCatalog).
"5. Find a book by book title" - запускает метод findBookByBookTitle - поиск книги по номеру в каталоге (BookCatalog).
"6. Create a new reader card" - запускает метод addNewUserCard - создать карточку нового читателя.
"7. View reader card" - запускает метод findUserCardByName - выводит данные карточки: имя читателя, его userId, список книг (userBookList), которые были взяты в библиотеке и которые вернули. (на втором этапе можно добавить дату, когда книга была выдана и когда её вернули).
"8. Close reader card" - запускает метод CloseUserCard - закрыть доступ пользователю в библиотеку.
"9. Reopen reader card" - запускает метод reopenUserCard - звозобновить доступ пользователю в библиотеку.
"10. Borrow a book from library"- запускает метод borrowBookFromLibrary - выдать книгу читателю User, при условии, что книга существует в каталоге книг (BookCatalog), есть в наличии в библиотеке (isInLibrary=true) и у пользователя не превышен лимит книг (booksLimit).
"11. Return the book to the library"- returnBookToLibrary - вернуть книгу в библиотеку.
"12. Remove book from catalog"- запускает метод removeBookByCatalogNumber - удаляет книгу из каталога (BookCatalog).
"13. Exit App" - запускает метод exitMenu - выход из приложения.


===================================================
Описание методов
===================================================  

   - `login(String username, String password)` - метод аутентификация администратора. Ввод логина и пароля, проверка корректности ввода данных
   - `addBook(Book book)`: void - создает новый объект Book и добавляет его в BookCatalog  
   - `removeBookByCatalogNumber(int catalogNumber)`: boolean - метод удаляет указанный элемент из BookCatalog  
   - `findBookById(String searchQuery, String type)`: Map<Book> -  
   - `findBookByAuthor(String searchQuery, String type)`: Map<Book>
   - `findBookByBookTitle(String searchQuery, String type)`: Map<Book>
   - `printCatalog()`: void - показать список всех книг из bookCatalog   
   - `checkBooksLimit`: void - метод проверяет не превышен ли допустимый лимит книг, которые может взять читатель User.
   - `login(String username, String password)`: boolean
   - `addNewUserCard(User user, int limit)`: void - создать новый объект User и UserCard и добавить их в список карт читателей. 
   - `findUserCardByName` - выводит данные карточки: имя читателя, его userId, список книг, которые были взяты в библиотеке и которые вернули. (на втором этапе можно добавить дату, когда книга была выдана и когда её вернули).
   - `closeUserCard(int userId)`: boolean - закрыть доступ пользователю в библиотеку
   - `reopenUserCard(int userId)`: boolean - возобновить доступ пользователю в библиотеку
   - `borrowBookFromLibrary(int userId, int catalogNumber)`: boolean - выдать книгу читателю User и внести её в userBookList, при условии, что книга существует в каталоге книг (BookCatalog), есть в наличии в библиотеке (isInLibrary=true) и у пользователя не превышен лимит книг (booksLimit).
   - `returnBookToLibrary(int userId, int catalogNumber)`: boolean - вернуть книгу в библиотеку. Параметр isInLibrary внутри Book меняется на true. Вносим изменения в UserBookList и booksLimit внутри UserCard
   - `exitMenu`: void - выход из программы
   - `UserInput` - получение данных, введенных администратором с консоли


===================================================
