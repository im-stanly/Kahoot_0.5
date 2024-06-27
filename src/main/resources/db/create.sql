begin;

DROP TABLE IF EXISTS Users CASCADE;

CREATE TABLE Users (
    id          SERIAL          PRIMARY KEY,
    first_name  VARCHAR(20)     NOT NULL,
    last_name   VARCHAR(40)     NOT NULL,
    address     VARCHAR(200),
    city        VARCHAR(40),
    telephone   VARCHAR(20),
    email       VARCHAR(60)     UNIQUE
);

INSERT INTO Users(first_name, last_name, address, city) VALUES('Admin', 'Admin', 'tak', 'Krakow');

commit;
