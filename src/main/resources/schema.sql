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
);

-- Konteeksamen 2020

-- Oppg. 1 a)
CREATE TABLE student(
    sId INTEGER(11) NOT NULL,
    navn VARCHAR (50) NOT NULL,
    PRIMARY KEY(sId)
);

CREATE TABLE fag(
    fId VARCHAR(15) NOT NULL,
    navn VARCHAR(50) NOT NULL,
    PRIMARY KEY (fId)
);

CREATE TABLE studentfag(
    sId INTEGER(11) NOT NULL,
    fId VARCHAR(15) NOT NULL,
    aar INTEGER(11),
    karakter CHAR(1),
    prosent INTEGER(11),
    FOREIGN KEY (sId) REFERENCES student(sId),
    FOREIGN KEY (fId) REFERENCES fag(fId),
    PRIMARY KEY (sId,fId,aar)
);

-- Prøveeksamen 1

CREATE TABLE Melding
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    veistrekning VARCHAR(3) NOT NULL,
    fraSted VARCHAR(255) NOT NULL,
    tilSted VARCHAR(255) NOT NULL,
    fraDatoTid VARCHAR(255) NOT NULL,
    tilDatoTid VARCHAR(255) NOT NULL,
    melding VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

