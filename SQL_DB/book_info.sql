CREATE TABLE book_info(
   book_info_id      INTEGER PRIMARY KEY AUTOINCREMENT,
   is_in_library     BOOLEAN NOT NULL,
   borrowed_to       INTEGER,
   borrowed_date     DATE
   borrowed_duration INTEGER,
   return_date       DATE
);