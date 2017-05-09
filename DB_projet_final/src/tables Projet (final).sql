DROP TABLE Trajet;
DROP TABLE Maitre_;
DROP TABLE Proprietaire_;
DROP TABLE Animal_;
DROP TABLE Vehicule_;
DROP TABLE Personne_;
DROP TABLE ObjetMobile;
DROP TABLE Lieu_;

CREATE TABLE Lieu_ (
	NomLieu				VARCHAR2(100)	NOT NULL primary key,
	Ville				VARCHAR2(30)	NOT NULL,
	Pays				VARCHAR2(30)	NOT NULL,
	Latitude			NUMBER(10,7)	NOT NULL,
	Longitude			NUMBER(10,7)	NOT NULL,
	Rayon				NUMBER(5)	NOT NULL,
	CHECK(Latitude BETWEEN -90 AND 90),
	CHECK(Longitude BETWEEN -180 AND 180),
	CHECK(Rayon > 0)
);


INSERT INTO Lieu_ VALUES ('Parlement du Canada',				'Ottawa',	'Canada', 45.4251295, -75.7021194, 175);
INSERT INTO Lieu_ VALUES ('Assemblee nationale du Quebec',	'Quebec',	'Canada', 46.8089707, -71.2160001, 80);
INSERT INTO Lieu_ VALUES ('Stade Olympique de Montreal',		'Montreal', 'Canada', 45.5579994, -73.5540703, 200);
INSERT INTO Lieu_ VALUES ('Universite de Montreal',			'Montreal',	'Canada', 45.5056193, -73.6159479, 300);
INSERT INTO Lieu_ VALUES ('Universite McGill',				'Montreal',	'Canada', 45.5047884, -73.5793398, 200);
INSERT INTO Lieu_ VALUES ('Universite du Quebec a Montreal',	'Montreal',	'Canada', 45.5128576, -73.5672661, 175);
INSERT INTO Lieu_ VALUES ('Oratoire Saint-Joseph',			'Montreal',	'Canada', 45.4925772, -73.6205277, 75);
INSERT INTO Lieu_ VALUES ('BAnQ Grande Bibliothèque',		'Montreal',	'Canada', 45.515459, -73.5645016, 75);
INSERT INTO Lieu_ VALUES ('Gare dAutocars de Montreal',		'Montreal',	'Canada', 45.5167516, -73.5659157, 65);
INSERT INTO Lieu_ VALUES ('Port de Montreal',				'Montreal',	'Canada', 45.5860436, -73.5085838, 250);
INSERT INTO Lieu_ VALUES ('Aeroport Pierre-Elliot-Trudeau',	'Montreal',	'Canada', 45.4697382, -73.7449195, 2500);

CREATE TABLE ObjetMobile (
	IDObjet				Number(5)	NOT NULL primary key,
	Typeof				VARCHAR2(30)	NOT NULL
);

INSERT INTO ObjetMobile VALUES (41, 'Personne');
INSERT INTO ObjetMobile VALUES (42, 'Personne');
INSERT INTO ObjetMobile VALUES (43, 'Personne');
INSERT INTO ObjetMobile VALUES (44, 'Personne');
INSERT INTO ObjetMobile VALUES (45, 'Personne');
INSERT INTO ObjetMobile VALUES (11, 'Vehicule');
INSERT INTO ObjetMobile VALUES (13, 'Vehicule');
INSERT INTO ObjetMobile VALUES (14, 'Vehicule');
INSERT INTO ObjetMobile VALUES (15, 'Vehicule');
INSERT INTO ObjetMobile VALUES (25, 'Vehicule');
INSERT INTO ObjetMobile VALUES (61, 'Animal');
INSERT INTO ObjetMobile VALUES (62, 'Animal');
INSERT INTO ObjetMobile VALUES (63, 'Animal');
INSERT INTO ObjetMobile VALUES (64, 'Animal');
INSERT INTO ObjetMobile VALUES (65, 'Animal');

CREATE TABLE Personne_ (
	IDPers				NUMBER(5)	NOT NULL primary key,
	Prenom				VARCHAR2(30)	NOT NULL,
	Nom				VARCHAR2(30)	NOT NULL,
	DateNais			DATE            NOT NULL,
	Occupation			VARCHAR2(100),
	CONSTRAINT FKIDPers FOREIGN KEY (IDPers) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE
);

INSERT INTO Personne_ VALUES (41, 'Jean',	'Gagnon',	TO_DATE('06/04/1950', 'DD/MM/YYYY'), 'Fonctionnaire');
INSERT INTO Personne_ VALUES (42, 'Éric',	'Bertrand',	TO_DATE('12/07/1992', 'DD/MM/YYYY'), 'Étudiant');
INSERT INTO Personne_ VALUES (43, 'Martine',	'Cartier',	TO_DATE('16/11/1987', 'DD/MM/YYYY'), 'Étudiant');
INSERT INTO Personne_ VALUES (44, 'Micheline',	'Rivard',	TO_DATE('30/04/1967', 'DD/MM/YYYY'), 'Professeur');
INSERT INTO Personne_ VALUES (45, 'Anne',	'Sauve',	TO_DATE('02/03/1951', 'DD/MM/YYYY'), 'Camioneur');

CREATE TABLE Vehicule_ (
	IDVehicule			NUMBER(5)			NOT NULL primary key,
	NbPassagers			NUMBER(5)			NOT NULL,
	Typeof				VARCHAR2(100)	NOT NULL,
	Immatriculation		        CHAR(7),
	CONSTRAINT FKIDVehicule FOREIGN KEY (IDVehicule) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE,
	CHECK(NbPassagers > 0)
);

INSERT INTO Vehicule_ VALUES (11, 5, 'Automobile',		'GAR 884');
INSERT INTO Vehicule_ VALUES (13, 1, 'Bicyclette',		NULL);
INSERT INTO Vehicule_ VALUES (14, 1, 'Motocyclette',		'981 039');
INSERT INTO Vehicule_ VALUES (15, 4, 'Automobile',		'XAC 103');
INSERT INTO Vehicule_ VALUES (25, 2, 'Semi-remorque',	'R398911');

CREATE TABLE Animal_ (
	IDAnimal			NUMBER(5)	NOT NULL primary key,
	NomEspece			VARCHAR2(100)	NOT NULL,
	Nom				VARCHAR2(100),
	DateNais			DATE,
	CONSTRAINT FKIDAnimal FOREIGN KEY (IDAnimal) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE
);

INSERT INTO Animal_ VALUES (61, 'Chien', 'Cafe',		TO_DATE('03/04/2015', 'DD/MM/YYYY'));
INSERT INTO Animal_ VALUES (62, 'Chien', 'Fido',		TO_DATE('22/11/2008', 'DD/MM/YYYY'));
INSERT INTO Animal_ VALUES (63, 'Chat', 'Caramel',	NULL);
INSERT INTO Animal_ VALUES (64, 'Chat', NULL,		NULL);
INSERT INTO Animal_ VALUES (65, 'Chat', NULL,		NULL);

CREATE TABLE Proprietaire_ (
	IDPers		NUMBER(5)		NOT NULL,
	IDVehiculeP	NUMBER(5)		NOT NULL,
	DateDebut	DATE		 NOT NULL,
	DateFin		DATE,
	CONSTRAINT PKProprietaire PRIMARY KEY (IDPers, IDVehiculeP),
	CONSTRAINT FKIDPersProp FOREIGN KEY (IDPers) REFERENCES Personne_(IDPers) ON DELETE CASCADE,
	CONSTRAINT FKIDVehiculeProp FOREIGN KEY (IDVehiculeP) REFERENCES Vehicule_(IDVehicule) ON DELETE CASCADE
);

INSERT INTO Proprietaire_ VALUES (41, 11, TO_DATE('22/08/2010', 'DD/MM/YYYY'), NULL);
INSERT INTO Proprietaire_ VALUES (43, 13, TO_DATE('17/04/2013', 'DD/MM/YYYY'), NULL);
INSERT INTO Proprietaire_ VALUES (44, 14, TO_DATE('06/04/2012', 'DD/MM/YYYY'), NULL);
INSERT INTO Proprietaire_ VALUES (45, 15, TO_DATE('19/11/2009', 'DD/MM/YYYY'), NULL);
INSERT INTO Proprietaire_ VALUES (45, 25, TO_DATE('09/06/2012', 'DD/MM/YYYY'), NULL);



CREATE TABLE Maitre_ (
	IDPers				NUMBER(5)		NOT NULL,
	IDAnimal			NUMBER(5)		NOT NULL,
	DateDebut			DATE			NOT NULL,
	DateFin				DATE,
	CONSTRAINT PKMaitre PRIMARY KEY (IDPers, IDAnimal),
	CONSTRAINT FKIDPersMaitre FOREIGN KEY (IDPers) REFERENCES Personne_(IDPers) ON DELETE CASCADE,
	CONSTRAINT FKIDAnimalMaitre FOREIGN KEY (IDAnimal) REFERENCES Animal_(IDAnimal) ON DELETE CASCADE
);

INSERT INTO Maitre_ VALUES (43, 61, TO_DATE('16/04/2015', 'DD/MM/YYYY'), NULL);
INSERT INTO Maitre_ VALUES (44, 62, TO_DATE('08/06/2010', 'DD/MM/YYYY'), NULL);
INSERT INTO Maitre_ VALUES (42, 63, TO_DATE('04/12/2013', 'DD/MM/YYYY'), NULL);

CREATE TABLE Trajet(
	IDObjettrajet		NUMBER(5)		NOT NULL,
	LieuDepart			VARCHAR2(100)	NOT NULL,
	LieuArrivee			VARCHAR2(100)	NOT NULL,
	Date_Time			TIMESTAMP		NOT NULL,
	Duree				NUMBER(5)       NOT NULL,
	CONSTRAINT PKTrajet PRIMARY KEY (idobjettrajet, LieuDepart, LieuArrivee, Date_Time),
	CHECK(LieuDepart != LieuArrivee)
);


INSERT INTO Trajet VALUES(41, 'Assemblee nationale du Quebec',    'Parlement du Canada',         TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),265);
INSERT INTO Trajet VALUES(41, 'Parlement du Canada',    'Universite de Montreal',                TO_TIMESTAMP('07/04/2017 07:12:00', 'DD/MM/YYYY HH24:MI:SS'),128);
INSERT INTO Trajet VALUES(41, 'Universite de Montreal',    'Universite du Quebec a Montreal',    TO_TIMESTAMP('07/04/2017 10:38:00', 'DD/MM/YYYY HH24:MI:SS'),23);
INSERT INTO Trajet VALUES(41, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('07/04/2017 12:05:00', 'DD/MM/YYYY HH24:MI:SS'),12);
INSERT INTO Trajet VALUES(41, 'Universite McGill',    'Assemblee nationale du Quebec',           TO_TIMESTAMP('07/04/2017 13:33:00', 'DD/MM/YYYY HH24:MI:SS'),194);
INSERT INTO Trajet VALUES(11, 'Assemblee nationale du Quebec',    'Parlement du Canada',         TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),265);
INSERT INTO Trajet VALUES(11, 'Parlement du Canada',    'Universite de Montreal',                TO_TIMESTAMP('07/04/2017 07:12:00', 'DD/MM/YYYY HH24:MI:SS'),128);
INSERT INTO Trajet VALUES(11, 'Universite de Montreal',    'Universite du Quebec a Montreal',    TO_TIMESTAMP('07/04/2017 10:38:00', 'DD/MM/YYYY HH24:MI:SS'),23);
INSERT INTO Trajet VALUES(11, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('07/04/2017 12:05:00', 'DD/MM/YYYY HH24:MI:SS'),12);
INSERT INTO Trajet VALUES(11, 'Universite McGill',    'Assemblee nationale du Quebec',           TO_TIMESTAMP('07/04/2017 13:33:00', 'DD/MM/YYYY HH24:MI:SS'),194);
INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('03/04/2017 08:43:00', 'DD/MM/YYYY HH24:MI:SS'),8);
INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'BAnQ Grande Bibliothèque',  TO_TIMESTAMP('03/04/2017 14:12:00', 'DD/MM/YYYY HH24:MI:SS'),5);
INSERT INTO Trajet VALUES(42, 'BAnQ Grande Bibliothèque',    'Gare dAutocars de Montreal',       TO_TIMESTAMP('03/04/2017 15:49:00', 'DD/MM/YYYY HH24:MI:SS'),4);
INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('04/04/2017 08:54:00', 'DD/MM/YYYY HH24:MI:SS'),7);
INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'Gare dAutocars de Montreal',TO_TIMESTAMP('04/04/2017 15:03:00', 'DD/MM/YYYY HH24:MI:SS'),6);
INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('05/04/2017 09:50:00', 'DD/MM/YYYY HH24:MI:SS'),7);
INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'Gare dAutocars de Montreal',TO_TIMESTAMP('05/04/2017 12:03:00', 'DD/MM/YYYY HH24:MI:SS'),8);
INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('06/04/2017 09:41:00', 'DD/MM/YYYY HH24:MI:SS'),8);
INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'BAnQ Grande Bibliothèque',  TO_TIMESTAMP('06/04/2017 14:24:00', 'DD/MM/YYYY HH24:MI:SS'),3);
INSERT INTO Trajet VALUES(42, 'BAnQ Grande Bibliothèque',    'Gare dAutocars de Montreal',       TO_TIMESTAMP('06/04/2017 16:53:00', 'DD/MM/YYYY HH24:MI:SS'),5);
INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('07/04/2017 09:33:00', 'DD/MM/YYYY HH24:MI:SS'),9);
INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'Gare dAutocars de Montreal',TO_TIMESTAMP('07/04/2017 14:11:00', 'DD/MM/YYYY HH24:MI:SS'),9);
INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('03/04/2017 08:05:00', 'DD/MM/YYYY HH24:MI:SS'),55);
INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('03/04/2017 15:01:00', 'DD/MM/YYYY HH24:MI:SS'),50);
INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'BAnQ Grande Bibliothèque',      TO_TIMESTAMP('04/04/2017 10:32:00', 'DD/MM/YYYY HH24:MI:SS'),40);
INSERT INTO Trajet VALUES(43, 'BAnQ Grande Bibliothèque',    'Stade Olympique de Montreal',      TO_TIMESTAMP('04/04/2017 11:29:00', 'DD/MM/YYYY HH24:MI:SS'),46);
INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),52);
INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('05/04/2017 11:59:00', 'DD/MM/YYYY HH24:MI:SS'),9);
INSERT INTO Trajet VALUES(43, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('05/04/2017 12:48:00', 'DD/MM/YYYY HH24:MI:SS'),11);
INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('05/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),48);
INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('07/04/2017 09:08:00', 'DD/MM/YYYY HH24:MI:SS'),54);
INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('07/04/2017 13:12:00', 'DD/MM/YYYY HH24:MI:SS'),51);
INSERT INTO Trajet VALUES(13, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('03/04/2017 08:05:00', 'DD/MM/YYYY HH24:MI:SS'),55);
INSERT INTO Trajet VALUES(13, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('03/04/2017 15:01:00', 'DD/MM/YYYY HH24:MI:SS'),50);
INSERT INTO Trajet VALUES(13, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),52);
INSERT INTO Trajet VALUES(13, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('05/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),48);
INSERT INTO Trajet VALUES(13, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('07/04/2017 09:08:00', 'DD/MM/YYYY HH24:MI:SS'),54);
INSERT INTO Trajet VALUES(13, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('07/04/2017 13:12:00', 'DD/MM/YYYY HH24:MI:SS'),51);
INSERT INTO Trajet VALUES(61, 'Stade Olympique de Montreal',    'BAnQ Grande Bibliothèque',      TO_TIMESTAMP('04/04/2017 10:32:00', 'DD/MM/YYYY HH24:MI:SS'),40);
INSERT INTO Trajet VALUES(61, 'BAnQ Grande Bibliothèque',    'Stade Olympique de Montreal',      TO_TIMESTAMP('04/04/2017 11:29:00', 'DD/MM/YYYY HH24:MI:SS'),46);
INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('03/04/2017 08:01:00', 'DD/MM/YYYY HH24:MI:SS'),20);
INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('03/04/2017 16:42:00', 'DD/MM/YYYY HH24:MI:SS'),25);
INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('03/04/2017 18:41:00', 'DD/MM/YYYY HH24:MI:SS'),25);
INSERT INTO Trajet VALUES(44, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('03/04/2017 19:22:00', 'DD/MM/YYYY HH24:MI:SS'),26);
INSERT INTO Trajet VALUES(62, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('03/04/2017 18:41:00', 'DD/MM/YYYY HH24:MI:SS'),25);
INSERT INTO Trajet VALUES(62, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('03/04/2017 19:22:00', 'DD/MM/YYYY HH24:MI:SS'),26);
INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('05/04/2017 09:13:00', 'DD/MM/YYYY HH24:MI:SS'),18);
INSERT INTO Trajet VALUES(44, 'Universite McGill',    'BAnQ Grande Bibliothèque',                TO_TIMESTAMP('05/04/2017 11:39:00', 'DD/MM/YYYY HH24:MI:SS'),32);
INSERT INTO Trajet VALUES(44, 'BAnQ Grande Bibliothèque',    'Universite McGill',                TO_TIMESTAMP('05/04/2017 13:58:00', 'DD/MM/YYYY HH24:MI:SS'),29);
INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('05/04/2017 17:02:00', 'DD/MM/YYYY HH24:MI:SS'),26);
INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('06/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),28);
INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 18:17:00', 'DD/MM/YYYY HH24:MI:SS'),32);
INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),21);
INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('07/04/2017 13:31:00', 'DD/MM/YYYY HH24:MI:SS'),19);
INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('03/04/2017 08:01:00', 'DD/MM/YYYY HH24:MI:SS'),20);
INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('03/04/2017 16:42:00', 'DD/MM/YYYY HH24:MI:SS'),25);
INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('05/04/2017 09:13:00', 'DD/MM/YYYY HH24:MI:SS'),18);
INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('05/04/2017 17:02:00', 'DD/MM/YYYY HH24:MI:SS'),26);
INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('06/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),28);
INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 18:17:00', 'DD/MM/YYYY HH24:MI:SS'),32);
INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 19:14:00', 'DD/MM/YYYY HH24:MI:SS'),14);
INSERT INTO Trajet VALUES(44, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 20:02:00', 'DD/MM/YYYY HH24:MI:SS'),3);
INSERT INTO Trajet VALUES(62, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 19:14:00', 'DD/MM/YYYY HH24:MI:SS'),14);
INSERT INTO Trajet VALUES(62, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 20:02:00', 'DD/MM/YYYY HH24:MI:SS'),23);
INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),21);
INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('07/04/2017 13:31:00', 'DD/MM/YYYY HH24:MI:SS'),19);
INSERT INTO Trajet VALUES(45, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('06/04/2017 07:22:00', 'DD/MM/YYYY HH24:MI:SS'),16);
INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Universite du Quebec a Montreal',          TO_TIMESTAMP('06/04/2017 07:45:00', 'DD/MM/YYYY HH24:MI:SS'),28);
INSERT INTO Trajet VALUES(45, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('06/04/2017 08:34:00', 'DD/MM/YYYY HH24:MI:SS'),17);
INSERT INTO Trajet VALUES(45, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 09:38:00', 'DD/MM/YYYY HH24:MI:SS'),21);
INSERT INTO Trajet VALUES(45, 'Universite de Montreal',    'Aeroport Pierre-Elliot-Trudeau',     TO_TIMESTAMP('06/04/2017 10:51:00', 'DD/MM/YYYY HH24:MI:SS'),46);
INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('06/04/2017 13:59:00', 'DD/MM/YYYY HH24:MI:SS'),59);
INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('06/04/2017 15:29:00', 'DD/MM/YYYY HH24:MI:SS'),17);
INSERT INTO Trajet VALUES(45, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('07/04/2017 07:11:00', 'DD/MM/YYYY HH24:MI:SS'),14);
INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 07:42:00', 'DD/MM/YYYY HH24:MI:SS'),74);
INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),69);
INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 10:57:00', 'DD/MM/YYYY HH24:MI:SS'),72);
INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 13:01:00', 'DD/MM/YYYY HH24:MI:SS'),67);
INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),59);
INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 16:13:00', 'DD/MM/YYYY HH24:MI:SS'),59);
INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('07/04/2017 18:07:00', 'DD/MM/YYYY HH24:MI:SS'),13);
INSERT INTO Trajet VALUES(15, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('06/04/2017 07:22:00', 'DD/MM/YYYY HH24:MI:SS'),14);
INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Universite du Quebec a Montreal',          TO_TIMESTAMP('06/04/2017 07:45:00', 'DD/MM/YYYY HH24:MI:SS'),28);
INSERT INTO Trajet VALUES(25, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('06/04/2017 08:34:00', 'DD/MM/YYYY HH24:MI:SS'),17);
INSERT INTO Trajet VALUES(25, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 09:38:00', 'DD/MM/YYYY HH24:MI:SS'),21);
INSERT INTO Trajet VALUES(25, 'Universite de Montreal',    'Aeroport Pierre-Elliot-Trudeau',     TO_TIMESTAMP('06/04/2017 10:51:00', 'DD/MM/YYYY HH24:MI:SS'),46);
INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('06/04/2017 13:59:00', 'DD/MM/YYYY HH24:MI:SS'),59);
INSERT INTO Trajet VALUES(15, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('06/04/2017 15:29:00', 'DD/MM/YYYY HH24:MI:SS'),13);
INSERT INTO Trajet VALUES(15, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('07/04/2017 07:11:00', 'DD/MM/YYYY HH24:MI:SS'),14);
INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 07:42:00', 'DD/MM/YYYY HH24:MI:SS'),74);
INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),69);
INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 10:57:00', 'DD/MM/YYYY HH24:MI:SS'),72);
INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 13:01:00', 'DD/MM/YYYY HH24:MI:SS'),67);
INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),59);
INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 16:13:00', 'DD/MM/YYYY HH24:MI:SS'),59);
INSERT INTO Trajet VALUES(15, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('07/04/2017 18:07:00', 'DD/MM/YYYY HH24:MI:SS'),13);
INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('04/04/2017 09:40:00', 'DD/MM/YYYY HH24:MI:SS'),20);
INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 10:11:00', 'DD/MM/YYYY HH24:MI:SS'),17);
INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('04/04/2017 10:30:00', 'DD/MM/YYYY HH24:MI:SS'),15);
INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 10:59:00', 'DD/MM/YYYY HH24:MI:SS'),14);
INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('04/04/2017 13:13:00', 'DD/MM/YYYY HH24:MI:SS'),14);
INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 14:28:00', 'DD/MM/YYYY HH24:MI:SS'),17);
INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 09:56:00', 'DD/MM/YYYY HH24:MI:SS'),29);
INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 10:11:00', 'DD/MM/YYYY HH24:MI:SS'),13);
INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 11:23:00', 'DD/MM/YYYY HH24:MI:SS'),23);
INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 11:58:00', 'DD/MM/YYYY HH24:MI:SS'),12);
INSERT INTO Trajet VALUES(64, 'Universite du Quebec a Montreal',    'BAnQ Grande Bibliothèque',  TO_TIMESTAMP('02/04/2017 13:21:00', 'DD/MM/YYYY HH24:MI:SS'),32);
INSERT INTO Trajet VALUES(64, 'BAnQ Grande Bibliothèque',    'Universite du Quebec a Montreal',  TO_TIMESTAMP('02/04/2017 15:01:00', 'DD/MM/YYYY HH24:MI:SS'),9);
INSERT INTO Trajet VALUES(64, 'Universite du Quebec à Montreal',    'Univeriste McGill',         TO_TIMESTAMP('03/04/2017 08:09:00', 'DD/MM/YYYY HH24:MI:SS'),48);
INSERT INTO Trajet VALUES(64, 'Universite McGill',    'BAnQ Grande Bibliothèque',                TO_TIMESTAMP('04/04/2017 17:35:00', 'DD/MM/YYYY HH24:MI:SS'),57);
INSERT INTO Trajet VALUES(64, 'BAnQ Grande Bibliothèque',    'Stade Olympique de Montreal',      TO_TIMESTAMP('05/04/2017 10:48:00', 'DD/MM/YYYY HH24:MI:SS'),143);
INSERT INTO Trajet VALUES(65, 'Universite du Quebec à Montreal',    'Oratoire Saint-Joseph',     TO_TIMESTAMP('04/04/2017 13:14:00', 'DD/MM/YYYY HH24:MI:SS'),121);
INSERT INTO Trajet VALUES(65, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 16:38:00', 'DD/MM/YYYY HH24:MI:SS'),12);
INSERT INTO Trajet VALUES(65, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('04/04/2017 19:46:00', 'DD/MM/YYYY HH24:MI:SS'),156);
INSERT INTO Trajet VALUES(65, 'Universite McGill',    'BAnQ Grande Bibliothèque',                TO_TIMESTAMP('05/04/2017 02:22:00', 'DD/MM/YYYY HH24:MI:SS'),53);
INSERT INTO Trajet VALUES(65, 'BAnQ Grande Bibliothèque',    'Universite McGill',                TO_TIMESTAMP('05/04/2017 15:44:00', 'DD/MM/YYYY HH24:MI:SS'),41);


--5 plus longs trajets de véhicules
select LieuDepart,LieuArrivee, Duree from trajet,vehicule_ where idobjettrajet = idvehicule order by duree desc FETCH NEXT 5 ROWS ONLY;

--Durées moyennes des trajets par type de véhicule pour les personnes possédant plus d'un véhicule
select IDPers, typeof, avg(duree) from trajet,vehicule_, Proprietaire_ where idvehiculep in (select idvehiculep from Proprietaire_ where idpers in (select IDPers from Proprietaire_ group by IDPers having count(*) > 1)) and idobjettrajet=idvehiculep and idvehicule=idvehiculep  group by IDPers,typeof; 

--Lieux visités par les propriétaires d'automobiles
select DISTINCT LieuArrivee from trajet where idobjettrajet in (select idpers from Proprietaire_ where idvehiculep in (select idvehicule from vehicule_ where typeof='Automobile'));

--Trajets effectués par des maitres promenant leur animal     
select distinct LieuDepart, LieuArrivee from trajet where idobjettrajet in (select idpers from Maitre_ where idanimal in (select idanimal from Animal_ where NomEspece='Chien'));

--Durée moyenne des trajets effectués par des animaux qui ont un maître
select IDAnimal, avg(duree) from animal_,trajet where IDAnimal in (select idanimal from Maitre_) and idobjettrajet=idanimal group by idanimal; 

--Durée moyenne des trajets selon le type d'objet mobile
select typeof, avg(duree) from trajet,ObjetMobile where idobjettrajet=idobjet group by typeof; 

--Liste des trajets effectués par des personnes, triée par durée moyenne des trajets
select distinct LieuDepart, LieuArrivee, avg(duree) from trajet,Personne_ where idobjettrajet=IDPers group by LieuDepart, LieuArrivee order by avg(duree) desc;

--Liste des trajets effectués par des étudiants et des professeurs
select distinct LieuDepart, LieuArrivee from trajet where idobjettrajet in (select idpers from Personne_ where Occupation='Étudiant' or Occupation = 'Professeur');

--Coordonnées des 3 destinations les plus fréquentées par les maîtres de chiens
SELECT NomLieu AS Destination, Latitude, Longitude, Frequence
FROM Lieu_, (SELECT LieuArrivee, COUNT(*) AS Frequence
			 FROM Trajet
			 WHERE IDObjetTrajet IN (SELECT IDPers
									 FROM Maitre_
									 WHERE IDAnimal IN (SELECT IDAnimal
														FROM Animal_
														WHERE NomEspece = 'Chien'))
			 GROUP BY LieuArrivee);
WHERE NomLieu = LieuArrivee
ORDER BY Frequence DESC
FETCH FIRST 3 ROWS ONLY;

--Trajet le plus fréquent de chaque personne
WITH TRAJETS AS (SELECT IDPers, LieuDepart, LieuArrivee, COUNT(*) AS Freq
                 FROM Trajet, Personne_
                 WHERE IDObjetTrajet = IDPers
                 GROUP BY IDPers, LieuDepart, LieuArrivee)
SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, NbParcours
FROM Personne_, (SELECT TRAJETS.IDPers AS IDPers_, LieuDepart, LieuArrivee, NbParcours
                 FROM TRAJETS, (SELECT IDPers, MAX(Freq) AS NbParcours
                                FROM TRAJETS
                                GROUP BY IDPers) HIGHEST_FREQ
                 WHERE TRAJETS.IDPers = HIGHEST_FREQ.IDPers AND Freq = NbParcours)
WHERE Personne_.IDPers = IDPers_
ORDER BY NbParcours DESC, Prenom, Nom, LieuDepart, LieuArrivee;

--select table_name from user_tables;

SET TERMOUT ON
PROMPT creation terminee
SET TERMOUT OFF