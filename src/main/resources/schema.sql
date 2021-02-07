DROP TABLE IF EXISTS reservations;

CREATE TABLE reservations
(
    id               VARCHAR(11)  NOT NULL,
    first_name       VARCHAR(255) NULL,
    last_name        VARCHAR(255) NULL,
    reservation_time DATETIME     NULL,
    number_of_guests INT          NULL
);

