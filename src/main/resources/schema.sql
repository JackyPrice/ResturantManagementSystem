CREATE TABLE IF NOT EXISTS reservations
(
    id VARCHAR(11) NOT NULL
        PRIMARY KEY ,
    first_name VARCHAR(255) NULL,
    last_name VARCHAR(255) NULL,
    reservation_time DATETIME NULL,
    number_of_guests INT Null
);
