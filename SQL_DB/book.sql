CREATE TABLE book (
    book_id        INTEGER PRIMARY KEY AUTOINCREMENT,
    author         TEXT NOT NULL,
    title          TEXT NOT NULL,
    genre          TEXT,
    publisher      TEXT,
    book_info_id   INTEGER,
    FOREIGN KEY (book_info_id)
       REFERENCES book_info (book_info_id)
           ON DELETE CASCADE
           ON UPDATE NO ACTION 
);