/**
 * Author:  visual Paradigm
 * Created: 1 avr. 220
 */

drop schema if exists imp3D;
create schema imp3D;
use imp3D;

CREATE TABLE Ambiance (
    ID int(10) NOT NULL AUTO_INCREMENT, 
    Temperature double NOT NULL, 
    Humidite double NOT NULL, 
    Datation datetime UNIQUE NOT NULL, 
    PRIMARY KEY (ID)
);

CREATE TABLE Cartouche (
    ID int(10) NOT NULL AUTO_INCREMENT, 
    Type varchar(255) NOT NULL, 
    EnUtilisation bool NOT NULL, 
    DateRemplacement datetime, 
    DateFabrication datetime, 
    IdentifiantType varchar(255), 
    NumeroDeSerie varchar(255) UNIQUE, 
    QuantiteRestante double, 
    CoutAuCm3 int(10), 
    Imprimante3dID int(10) NOT NULL, 
    PRIMARY KEY (ID)
);

CREATE TABLE Client (
    ID int(10) NOT NULL AUTO_INCREMENT, 
    Nom varchar(255) NOT NULL,  
    Prenom varchar(255), 
    MotDePasse varchar(255) NOT NULL,  
    Mail varchar(255) NOT NULL UNIQUE, 
    PRIMARY KEY (ID)
);

CREATE TABLE Imprimante3d (
    ID int(10) NOT NULL AUTO_INCREMENT, 
    Nom varchar(255) NOT NULL UNIQUE, 
    FablabNom varchar(255) NOT NULL UNIQUE,  
    Etat varchar(255) NOT NULL,  
    NbHeuresDeTravail double NOT NULL, 
    CoutHoraire int(10) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Inscription (
    ID int(10) NOT NULL AUTO_INCREMENT, 
    Mail varchar(255) NOT NULL UNIQUE,
    DateInscription datetime NOT NULL, 
    PRIMARY KEY (ID)
);

CREATE TABLE Job (
    ID int(10) NOT NULL AUTO_INCREMENT, 
    Nom varchar(255) NOT NULL,
    FileRef varchar(255) NOT NULL,
    DateDemande datetime NOT NULL,
    DateRealisation datetime, 
    Etat varchar(255) NOT NULL, 
    DureeConsommee int(10), 
    ResteAFaireEstimee int(10), 
    SupportConsomme double, 
    MatiereConsommee double, 
    SupportEstime double, 
    MatiereEstimee double, 
    Prix int(10), 
    ClientID int(10) NOT NULL, 
    Imprimante3dID int(10) NOT NULL, 
    PRIMARY KEY (ID)
);

CREATE TABLE Operateur (
    ID int(10) NOT NULL AUTO_INCREMENT,
    Nom varchar(255), 
    Prenom varchar(255), 
    MotDePasse varchar(255), 
    Mail varchar(255), 
    PRIMARY KEY (ID)
);

ALTER TABLE Job ADD 
    CONSTRAINT FKJob198856 FOREIGN KEY (Imprimante3dID) REFERENCES Imprimante3d (ID);
ALTER TABLE Job ADD 
    CONSTRAINT FKJob717099 FOREIGN KEY (ClientID) REFERENCES Client (ID);
ALTER TABLE Cartouche ADD 
    CONSTRAINT FKCartouche51050 FOREIGN KEY (Imprimante3dID) REFERENCES Imprimante3d (ID);
