CREATE TABLE Motorvognregister(
    personnr VARCHAR(100) NOT NULL,
    navn VARCHAR(100) NOT NULL,
    adresse VARCHAR(100) NOT NULL,
    kjennetegn VARCHAR(100) NOT NULL,
    merke VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    PRIMARY KEY (personnr)
);

CREATE TABLE Bilregister(
    id INTEGER AUTO_INCREMENT NOT NULL,
    merke VARCHAR(255) NOT NULL,
    type  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Brukerregister(
    id INTEGER AUTO_INCREMENT NOT NULL,
    brukernavn VARCHAR(100) NOT NULL,
    passord VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- Eksamen 2020

CREATE TABLE utover(
    id INTEGER AUTO_INCREMENT NOT NULL,
    fornavn VARCHAR(255) NOT NULL,
    etternavn VARCHAR(255) NOT NULL,
    klubb VARCHAR(255) NOT NULL,
    epost VARCHAR(255) NOT NULL,
    passord VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
)

