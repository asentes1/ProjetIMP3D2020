/**
 * Author:  jean-pierredumas
 * Created: 4 avr. 2020
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
    AmbianceID int(10) NOT NULL,
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

insert into Imprimante3d (Nom, FablabNom, Etat, NbHeuresDeTravail, CoutHoraire, AmbianceID) values
    ("IMP1", "LIVH", "IMPRESSION", 230, 30.0, 3);

/* mot de passe 1234 >>> 81DC9BDB52D04DC2036DBD8313ED055 */
insert into Client (Nom, Prenom, MotDePasse, Mail) values
    ("deodat", "lycée", "81DC9BDB52D04DC2036DBD8313ED055", "deodat@gmail.com"),
    ("livh", "lycée", "81DC9BDB52D04DC2036DBD8313ED055", "livh@gmail.com");

insert into Ambiance (Temperature, Humidite, Datation) values 
    (21, 57,'2020/03/22 07:00:00'),
    (21, 57,'2020/03/22 07:10:00'),
    (20, 58,'2020/03/22 07:20:00'),
    (20, 58,'2020/03/22 07:30:00'),
    (19, 57,'2020/03/22 07:40:00'),
    (19, 56,'2020/03/22 07:50:00');

insert into Inscription (Mail, DateInscription) values 
    ('jpdms@free.fr', '2020/03/22 07:00:00'),
    ('jpdms31@gmail.com', '2020/03/22 07:10:00'),
    ('jpdumas@btslivh.eu', '2020/03/22 07:20:00');

insert into Operateur (Nom, Prenom, MotDePasse, Mail) values
    ("Lepine", "Hervé", "81DC9BDB52D04DC2036DBD8313ED055", "hervelepine@gmail.com");

insert into Job (Nom, FileRef, DateDemande, DateRealisation, Etat, DureeConsommee,
    ResteAFaireEstimee, SupportConsomme, MatiereConsommee, SupportEstime,
    MatiereEstimee, Prix, ClientID, Imprimante3dID) values
    ("porteclef", "imp3d/deodat/porteclef.stl", "2020/01/11", NULL, "REALISE", 
                            NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1),
    ("disque", "imp3d/deodat/disque.stl", "2020/01/13", "2020/01/14", "ENCOURS", 
                            12.5, 600.4, 14, 30, 14, 78, 3.3, 1, 1),
    ("boite", "imp3d/deodat/boite.stl", "2020/01/14", NULL, "AFAIRE", 
                            NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1),
    ("couvercle", "imp3d/livh/couvercle.stl", "2020/01/14", NULL, "AFAIRE", 
                            NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
    ("arbre", "imp3d/deodat/arbre.stl", "2020/01/17", NULL, "AFAIRE", 
                            NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);

insert into Cartouche (Type, EnUtilisation, DateRemplacement, DateFabrication,
    IdentifiantType,  NumeroDeSerie, QuantiteRestante, CoutAuCm3, Imprimante3dID) values
    ("SUPPORT", TRUE, "2019/02/20", "2020/01/11", "12E345", "A12345", 230, 0.12, 1),
    ("MATIERE", TRUE, "2020/01/05", "2018/09/21", "32M345", "M14502", 320, 0.32, 1);


drop user imp3D@localhost;
create user imp3D@localhost identified by 'imp3D31';

grant  select,insert,update,delete on imp3D.* to imp3D@localhost identified by 'imp3D31';
