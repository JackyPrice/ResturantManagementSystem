CREATE TABLE IF NOT EXISTS reservations
(
    id VARCHAR(11) NOT NULL
        PRIMARY KEY ,
    firstName VARCHAR(255) NULL,
    lastName VARCHAR(255) NULL,
    reservationTime DATETIME NULL,
    numberOfGuests INT Null
);
