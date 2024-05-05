# ToDo list

## Date: 01.05.2024
## Deadline till: 05.05.2024

### Bugs

=================================================== 
Bug №1 (in Jira)
===================================================

**Steps to Reproduce:**
- In `App menu` open `[1] Book menu` --> `[1] Add new book`
- enter `Author` name

**Expected Result:**
- `Author` is requested
- User can enter Author's name in the same line with `Author` parameter.

**Instead:**
- User receives error message before it was possible to type Author's name:
`Error: empty or too short value`

**Solution:**
- Validation check is triggered before data entry via the console occurs. 
- UserInput (service --> util) method should be fixed.

**Assignee:**
Anton

===================================================
Bug №2 (in Jira)
===================================================

**Steps to Reproduce:**
- In `App menu` open `[2] Reader menu` --> `[4] Find User Card by Name`
- enter name in `Get User Name:`

**Expected Result:**
- `User Name` is requested
- User can enter User's name in the same line with `Get User Name:` parameter.

**Instead:**
- User receives error message before it was possible to type User's name:
  `Error: empty or too short value`

**Solution:**
- Validation check is triggered before data entry via the console occurs.
- UserInput (service --> util) method should be fixed.
- The same problem as in Bug №1

**Assignee:**
Anton

===================================================
Bug №3
===================================================

**Steps to Reproduce:**
- In `App menu` open `[2] Reader menu` --> `[1] Add User Card`
- enter User ID in `Get User ID:`
- enter User's name in `Get User Name:`

**Expected Result:**
- `User Name` is requested
- User can enter User's name in the same line with `Get User Name:` parameter.

**Instead:**
- User receives error message before it was possible to type User's name:
  `Error: empty or too short value`

**Solution:**
- Validation check is triggered before data entry via the console occurs.
- UserInput (service --> util) method should be fixed.
- The same problem as in Bug №1 and №2

**Assignee:**
Anton

===================================================
Bug №4
===================================================

**Steps to Reproduce:**
- In `App menu` open `[2] Reader menu` --> `[1] Add User Card`
- enter User ID in `Get User ID:`
- enter User's name in `Get User Name:`

**Expected Result:**
- the program checks whether the entered User ID already exists in the User list (repository --> UserCardRepository --> unit)
- if everything fine, then new UserCard is created and added to User repository.

**Instead:**
- there is no validation of User ID
- as a result we can rewrite existing UserCard instead of adding a new UserCard

**Solution:**
- the program should not request User ID to create a new UserCard.
- User ID should be assigned automatically after UserCard is created, as it has already been implemented in the `Add new book` menu
- `addNewUserCard` method (service --> UserCardService) should request int and return UserId. Please check `addBook` method (service --> BookCatalogService)

**Assignee:**
Larysa

===================================================
Bug №5 (complex)
===================================================

**Steps to Reproduce:**
- In `App menu` open `[3] Library menu` --> `[1] Borrow Book`
- enter `Book catalog number to be borrowed to the reader:`
- enter `Card ID of the user who borrows a book:`

**Expected Result:**
- Book is not in library anymore, `isInLibrary=false`
- Book added to `List<Book> borrowedBooks` inside of UserCard
- `booksLimit` inside the UserCard must be changed (deducted) when book is borrowed. 
- `booksLimit` must be preset for all users (5 books). Once `bookLimit` = 0, books can no longer be borrowed as long as `bookLimit` > 0
- Book can't be borrowed, if `isClosed=true` inside the UserCard

**Instead:**
- Books are not added to `List<Book> borrowedBooks` inside of UserCard
- `booksLimit` = 0 and doesn't change if user borrow or return a book to the library
- Book can be borrowed even if `isClosed=true`

**Assignee:**
Yaroslav

===================================================
Bug №6 (complex)
===================================================

**Steps to Reproduce:**
- In `App menu` open `[3] Library menu` --> `[2] Return Book`
- enter `Book catalog number for return to the library:`

**Expected Result:**
- Book returns to the library, `isInLibrary=true`
- Book removed from `List<Book> borrowedBooks` inside of UserCard
- `booksLimit` inside the UserCard must be changed (+1) when book is returned.
- Book can be returned, if `isClosed=true` inside the UserCard

**Instead:**
- Books are not removed from `List<Book> borrowedBooks` inside of UserCard
- `booksLimit` = 0 and doesn't change if user borrow or return a book to the library

**Assignee:**
Yaroslav

===================================================

### Feature

===================================================
Feature №1
===================================================

- Add menu `FindAll UserCards` inside of `[2] Reader menu`
- new `FindAll` method should be added to UserCardService
- `FindAll` method should display a list of all Users on the screen

**Assignee:**
Larysa

===================================================
Feature №2 (in jira)
===================================================

- Add `Back` button in the third level menu

**Assignee:**
Anton

===================================================
Feature №3
===================================================

- Save information about the User who borrowed a book, inside the Book. entity --> Book --> borrowedTo

**Assignee:**
Yaroslav

===================================================

### Improvement

===================================================
Improvement №1 
===================================================

- The console screen should be cleared when Admin moving to the next menu level

**Assignee:**
Anton

===================================================
Improvement №2
===================================================

- Add User's name inside text of `borrowBookFromLibrary` method (service --> LibraryService):
`System.out.println("Book '" + book.getBookTitle() + "' by " + book.getAuthor() + " has been borrowed.")`

- Follow steps:
  - In `App menu` open `[3] Library menu` --> `[1] Borrow Book`
  - enter `Book ID that need to borrow to Reader:`
  - enter `Reader ID that wish this book to be borrowed:`
  - confirmation will appear that book has been successfully borrowed

**Assignee:**
Larysa


===================================================
Improvement №3
===================================================

- Review and rename menu items

**Assignee:**
Anton

===================================================
Improvement №4
===================================================

- [2] Reader menu --> [1] Add User Card
- Add Validation check of User. If User already exists, then Admin can't add this User again.

**Assignee:**
Larysa

===================================================
Improvement №5
===================================================

- [2] Reader menu --> [1] Add User Card
- Add method updateUserCard inside UserCardService, so that we can rename User if needed.

**Assignee:**
Larysa