/**
 * Author:  imp3d group
 * Created: 3 avr. 2020
 */

use imp3D;

insert into Imprimante3d (Nom, FablabNom, Etat, NbHeuresDeTravail, CoutHoraire) values
    ("IMP1", "LIVH", "IMPRESSION", 230, 30.0);

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