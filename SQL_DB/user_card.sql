CREATE TABLE user_card (
    user_card_id   INTEGER PRIMARY KEY AUTOINCREMENT,
    books_limit    INTEGER NOT NULL,
    is_closed      BOOLEAN NOT NULL,
    user_id        INTEGER NOT NULL,
    FOREIGN KEY (user_id)
       REFERENCES user (user_id)
           ON DELETE CASCADE
           ON UPDATE NO ACTION 
);