/**
 * Created by farlyprj on 17-04-10.
 */

import oracle.jdbc.driver.OracleDriver;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.*;

public class Main {


    public static void main(String[] args)
    {
        initializeBD();
        MainWindow window = new MainWindow();
    }

    private static void initializeBD(){

        BD.execute("DROP TABLE 'Lieu';\n" +
                "DROP TABLE 'ObjetMobile';\n" +
                "DROP TABLE 'Personne';\n" +
                "DROP TABLE 'Vehicule';\n" +
                "DROP TABLE 'Animal';\n" +
                "DROP TABLE 'Proprietaire';\n" +
                "DROP TABLE 'Maitre';\n" +
                "DROP TABLE 'Trajet';\n" +
                "\n" +
                "CREATE TABLE Lieu (\n" +
                "\tNomLieu\t\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tVille\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tPays\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tLatitude\t\t\tNUMERIC(9,7)\tNOT NULL,\n" +
                "\tLongitude\t\t\tNUMERIC(9,7)\tNOT NULL,\n" +
                "\tRayon\t\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tCONSTRAINT PKNomLieu PRIMARY KEY (NomLieu),\n" +
                "\tCHECK(Latitude BETWEEN -90 AND 90),\n" +
                "\tCHECK(Longitude BETWEEN -180 AND 180),\n" +
                "\tCHECK(Rayon > 0)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE ObjetMobile (\n" +
                "\tIDObjet\t\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tType\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tCONSTRAINT PKIDObjet PRIMARY KEY (IDObjet)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Personne (\n" +
                "\tIDPers\t\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tPrenom\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tNom\t\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tDateNais\t\t\tDATE\t\t\tNOT NULL,\n" +
                "\tOccupation\t\t\tVARCHAR2(100),\n" +
                "\tCONSTRAINT PKIDPers PRIMARY KEY (IDPers),\n" +
                "\tCONSTRAINT FKIDPers FOREIGN KEY (IDPers) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE,\n" +
                "\tCHECK(DateNais BETWEEN '1900-01-01' AND CURRENT_DATE)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Vehicule (\n" +
                "\tIDVehicule\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tNbPassagers\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tType\t\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tImmatriculation\t\tCHAR(7),\n" +
                "\tCONSTRAINT PKIDVehicule PRIMARY KEY (IDVehicule),\n" +
                "\tCONSTRAINT FKIDVehicule FOREIGN KEY (IDVehicule) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE,\n" +
                "\tCHECK(NbPassagers > 0)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Animal (\n" +
                "\tIDAnimal\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tNomEspece\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tNom\t\t\t\t\tVARCHAR2(100),\n" +
                "\tDateNais\t\t\tDATE,\n" +
                "\tCONSTRAINT PKIDAnimal PRIMARY KEY (IDAnimal),\n" +
                "\tCONSTRAINT FKIDAnimal FOREIGN KEY (IDAnimal) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE,\n" +
                "\tCHECK(DateNais BETWEEN '1900-01-01' AND CURRENT_DATE)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Proprietaire (\n" +
                "\tIDPers\t\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tIDVehicule\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tDateDebut\t\t\tDATE\t\t\tNOT NULL,\n" +
                "\tDateFin\t\t\t\tDATE,\n" +
                "\tCONSTRAINT PKProprietaire PRIMARY KEY (IDPers, IDVehicule),\n" +
                "\tCONSTRAINT FKIDPers FOREIGN KEY (IDPers) REFERENCES Personne(IDPers) ON DELETE CASCADE,\n" +
                "\tCONSTRAINT FKIDVehicule FOREIGN KEY (IDVehicule) REFERENCES Vehicule(IDVehicule) ON DELETE CASCADE,\n" +
                "\tCHECK(DateDebut <= DateFin)\n" +
                "\tCHECK(DateDebut BETWEEN '1900-01-01' AND CURRENT_DATE),\n" +
                "\tCHECK(DateFin BETWEEN '1900-01-01' AND CURRENT_DATE)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Maitre (\n" +
                "\tIDPers\t\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tIDAnimal\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tDateDebut\t\t\tDATE\t\t\tNOT NULL,\n" +
                "\tDateFin\t\t\t\tDATE,\n" +
                "\tCONSTRAINT PKMaitre PRIMARY KEY (IDPers, IDAnimal),\n" +
                "\tCONSTRAINT FKIDPers FOREIGN KEY (IDPers) REFERENCES Personne(IDPers) ON DELETE CASCADE,\n" +
                "\tCONSTRAINT FKIDAnimal FOREIGN KEY (IDAnimal) REFERENCES Animal(IDAnimal) ON DELETE CASCADE,\n" +
                "\tCHECK(DateDebut <= DateFin)\n" +
                "\tCHECK(DateDebut BETWEEN '1900-01-01' AND CURRENT_DATE),\n" +
                "\tCHECK(DateFin BETWEEN '1900-01-01' AND CURRENT_DATE)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Trajet (\n" +
                "\tIDObjet\t\t\t\tINTEGER\t\t\tNOT NULL,\n" +
                "\tLieuDepart\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tLieuArrivee\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tDateDepart\t\t\tDATE\t\t\tNOT NULL,\n" +
                "\tHeureDepart\t\t\tTIME\t\t\tNOT NULL,\n" +
                "\tDuree\t\t\t\tINTEGER,\n" +
                "\tCONSTRAINT PKTrajet PRIMARY KEY (IDObjet, LieuDepart, LieuArrivee, DateDepart, HeureDepart),\n" +
                "\tCONSTRAINT FKLieuDepart FOREIGN KEY (LieuDepart) REFERENCES Lieu(NomLieu) ON DELETE CASCADE,\n" +
                "\tCONSTRAINT FKLieuArrivee FOREIGN KEY (LieuArrivee) REFERENCES Lieu(NomLieu) ON DELETE CASCADE,\n" +
                "\tCHECK(LieuDepart != LieuArrivee),\n" +
                "\tCHECK(DateDepart BETWEEN '1900-01-01' AND CURRENT_DATE)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "INSERT INTO Lieu VALUES ('Parlement du Canada',\t\t\t\t'Ottawa',\t'Canada', 45.4251295, -75.7021194, 175);\n" +
                "INSERT INTO Lieu VALUES ('Assemblée nationale du Québec',\t'Québec',\t'Canada', 46.8089707, -71.2160001, 80);\n" +
                "INSERT INTO Lieu VALUES ('Stade Olympique de Montréal',\t\t'Montréal', 'Canada', 45.5579994, -73.5540703, 200);\n" +
                "INSERT INTO Lieu VALUES ('Université de Montréal',\t\t\t'Montréal',\t'Canada', 45.5056193, -73.6159479, 300);\n" +
                "INSERT INTO Lieu VALUES ('Université McGill',\t\t\t\t'Montréal',\t'Canada', 45.5047884, -73.5793398, 200);\n" +
                "INSERT INTO Lieu VALUES ('Université du Québec à Montréal',\t'Montréal',\t'Canada', 45.5128576, -73.5672661, 175);\n" +
                "INSERT INTO Lieu VALUES ('Oratoire Saint-Joseph',\t\t\t'Montréal',\t'Canada', 45.4925772, -73.6205277, 75);\n" +
                "INSERT INTO Lieu VALUES ('BAnQ Grande Bibliothèque',\t\t'Montréal',\t'Canada', 45.515459, -73.5645016, 75);\n" +
                "INSERT INTO Lieu VALUES ('Gare dAutocars de Montréal',\t\t'Montréal',\t'Canada', 45.5167516, -73.5659157, 65);\n" +
                "INSERT INTO Lieu VALUES ('Port de Montréal',\t\t\t\t'Montréal',\t'Canada', 45.5860436, -73.5085838, 250);\n" +
                "INSERT INTO Lieu VALUES ('Aéroport Pierre-Elliot-Trudeau',\t'Montréal',\t'Canada', 45.4697382, -73.7449195, 2500);\n" +
                "\n" +
                "INSERT INTO ObjetMobile VALUES (41, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (42, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (43, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (44, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (45, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (11, 'Véhicule');\n" +
                "INSERT INTO ObjetMobile VALUES (13, 'Véhicule');\n" +
                "INSERT INTO ObjetMobile VALUES (14, 'Véhicule');\n" +
                "INSERT INTO ObjetMobile VALUES (15, 'Véhicule');\n" +
                "INSERT INTO ObjetMobile VALUES (25, 'Véhicule');\n" +
                "INSERT INTO ObjetMobile VALUES (61, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (62, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (63, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (64, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (65, 'Animal');\n" +
                "\n" +
                "INSERT INTO Personne VALUES (41, 'Jean',\t\t'Gagnon',\t\t'1950-04-06', 'Fonctionnaire');\n" +
                "INSERT INTO Personne VALUES (42, 'Éric',\t\t'Bertrand',\t\t'1992-07-12', 'Étudiant');\n" +
                "INSERT INTO Personne VALUES (43, 'Martine',\t\t'Cartier',\t\t'1987-11-16', 'Étudiant');\n" +
                "INSERT INTO Personne VALUES (44, 'Micheline',\t'Rivard',\t\t'1967-04-30', 'Professeur');\n" +
                "INSERT INTO Personne VALUES (45, 'Anne',\t\t'Sauvé',\t\t'1951-03-02', 'Camioneur');\n" +
                "\n" +
                "INSERT INTO Vehicule VALUES (11, 5, 'Automobile',\t\t'GAR 884');\n" +
                "INSERT INTO Vehicule VALUES (13, 1, 'Bicyclette',\t\tNULL);\n" +
                "INSERT INTO Vehicule VALUES (14, 1, 'Motocyclette',\t\t'981 039');\n" +
                "INSERT INTO Vehicule VALUES (15, 4, 'Automobile',\t\t'XAC 103');\n" +
                "INSERT INTO Vehicule VALUES (25, 2, 'Semi-remorque',\t'R398911');\n" +
                "\n" +
                "INSERT INTO Animal VALUES (61, 'Chien', 'Café',\t\t'2015-04-03');\n" +
                "INSERT INTO Animal VALUES (62, 'Chien', 'Fido',\t\t'2008-11-22');\n" +
                "INSERT INTO Animal VALUES (63, 'Chat', 'Caramel',\tNULL);\n" +
                "INSERT INTO Animal VALUES (64, 'Chat', NULL,\t\tNULL);\n" +
                "INSERT INTO Animal VALUES (65, 'Chat', NULL,\t\tNULL);\n" +
                "\n" +
                "INSERT INTO Proprietaire VALUES (41, 11, '2010-08-22', NULL);\n" +
                "INSERT INTO Proprietaire VALUES (43, 13, '2013-04-17', NULL);\n" +
                "INSERT INTO Proprietaire VALUES (44, 14, '2012-04-06', NULL);\n" +
                "INSERT INTO Proprietaire VALUES (45, 15, '2009-11-19', NULL);\n" +
                "INSERT INTO Proprietaire VALUES (45, 25, '2012-06-09', NULL);\n" +
                "\n" +
                "INSERT INTO Maitre VALUES (43, 61, '2015-04-16', NULL);\n" +
                "INSERT INTO Maitre VALUES (44, 62, '2010-06-08', NULL);\n" +
                "INSERT INTO Maitre VALUES (42, 63, '2013-11-04', NULL);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (41, 'Assemblée nationale du Québec',\t\t'Parlement du Canada',\t\t\t\t'2017-04-05', '08:15:00', 265);\n" +
                "INSERT INTO Trajet VALUES (41, 'Parlement du Canada',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-07', '07:12:00', 128);\n" +
                "INSERT INTO Trajet VALUES (41, 'Université de Montréal',\t\t\t'Université du Québec à Montréal',\t'2017-04-07', '10:38:00', 23);\n" +
                "INSERT INTO Trajet VALUES (41, 'Université du Québec à Montréal',\t'Université McGill',\t\t\t\t'2017-04-07', '12:05:00', 12);\n" +
                "INSERT INTO Trajet VALUES (41, 'Université McGill',\t\t\t\t\t'Assemblée nationale du Québec',\t'2017-04-07', '13:33:00', 194);\n" +
                "INSERT INTO Trajet VALUES (11, 'Assemblée nationale du Québec',\t\t'Parlement du Canada',\t\t\t\t'2017-04-05', '08:15:00', 265);\n" +
                "INSERT INTO Trajet VALUES (11, 'Parlement du Canada',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-07', '07:12:00', 128);\n" +
                "INSERT INTO Trajet VALUES (11, 'Université de Montréal',\t\t\t'Université du Québec à Montréal',\t'2017-04-07', '10:38:00', 23);\n" +
                "INSERT INTO Trajet VALUES (11, 'Université du Québec à Montréal',\t'Université McGill',\t\t\t\t'2017-04-07', '12:05:00', 12);\n" +
                "INSERT INTO Trajet VALUES (11, 'Université McGill',\t\t\t\t\t'Assemblée nationale du Québec',\t'2017-04-07', '13:33:00', 194);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (42, 'Gare d'Autocars de Montréal',\t\t'Université du Québec à Montréal',\t'2017-04-03', '08:43:00', 8);\n" +
                "INSERT INTO Trajet VALUES (42, 'Université du Québec à Montréal',\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-03', '14:12:00', 5);\n" +
                "INSERT INTO Trajet VALUES (42, 'BAnQ Grande Bibliothèque',\t\t\t'Gare d'Autocars de Montréal',\t\t'2017-04-03', '15:49:00', 4);\n" +
                "INSERT INTO Trajet VALUES (42, 'Gare d'Autocars de Montréal',\t\t'Université du Québec à Montréal',\t'2017-04-04', '08:54:00', 7);\n" +
                "INSERT INTO Trajet VALUES (42, 'Université du Québec à Montréal',\t'Gare d'Autocars de Montréal',\t\t'2017-04-04', '15:03:00', 6);\n" +
                "INSERT INTO Trajet VALUES (42, 'Gare d'Autocars de Montréal',\t\t'Université du Québec à Montréal',\t'2017-04-05', '09:50:00', 7);\n" +
                "INSERT INTO Trajet VALUES (42, 'Université du Québec à Montréal',\t'Gare d'Autocars de Montréal',\t\t'2017-04-05', '12:03:00', 8);\n" +
                "INSERT INTO Trajet VALUES (42, 'Gare d'Autocars de Montréal',\t\t'Université du Québec à Montréal',\t'2017-04-06', '09:41:00', 8);\n" +
                "INSERT INTO Trajet VALUES (42, 'Université du Québec à Montréal',\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-06', '14:24:00', 3);\n" +
                "INSERT INTO Trajet VALUES (42, 'BAnQ Grande Bibliothèque',\t\t\t'Gare d'Autocars de Montréal',\t\t'2017-04-06', '16:53:00', 5);\n" +
                "INSERT INTO Trajet VALUES (42, 'Gare d'Autocars de Montréal',\t\t'Université du Québec à Montréal',\t'2017-04-07', '09:33:00', 9);\n" +
                "INSERT INTO Trajet VALUES (42, 'Université du Québec à Montréal',\t'Gare d'Autocars de Montréal',\t\t'2017-04-07', '14:11:00', 9);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (43, 'Stade Olympique de Montréal',\t\t'Université de Montréal',\t\t\t'2017-04-03', '08:05:00', 55);\n" +
                "INSERT INTO Trajet VALUES (43, 'Université de Montréal',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-03', '15:01:00', 50);\n" +
                "INSERT INTO Trajet VALUES (43, 'Stade Olympique de Montréal',\t\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-04', '10:32:00', 40);\n" +
                "INSERT INTO Trajet VALUES (43, 'BAnQ Grande Bibliothèque',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-04', '11:29:00', 46);\n" +
                "INSERT INTO Trajet VALUES (43, 'Stade Olympique de Montréal',\t\t'Université de Montréal',\t\t\t'2017-04-05', '08:15:00', 52);\n" +
                "INSERT INTO Trajet VALUES (43, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-05', '11:59:00', 9);\n" +
                "INSERT INTO Trajet VALUES (43, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-05', '12:48:00', 11);\n" +
                "INSERT INTO Trajet VALUES (43, 'Université de Montréal',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-05', '14:45:00', 48);\n" +
                "INSERT INTO Trajet VALUES (43, 'Stade Olympique de Montréal',\t\t'Université de Montréal',\t\t\t'2017-04-07', '09:08:00', 54);\n" +
                "INSERT INTO Trajet VALUES (43, 'Université de Montréal',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-07', '13:12:00', 51);\n" +
                "INSERT INTO Trajet VALUES (13, 'Stade Olympique de Montréal',\t\t'Université de Montréal',\t\t\t'2017-04-03', '08:05:00', 55);\n" +
                "INSERT INTO Trajet VALUES (13, 'Université de Montréal',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-03', '15:01:00', 50);\n" +
                "INSERT INTO Trajet VALUES (13, 'Stade Olympique de Montréal',\t\t'Université de Montréal',\t\t\t'2017-04-05', '08:15:00', 52);\n" +
                "INSERT INTO Trajet VALUES (13, 'Université de Montréal',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-05', '14:45:00', 48);\n" +
                "INSERT INTO Trajet VALUES (13, 'Stade Olympique de Montréal',\t\t'Université de Montréal',\t\t\t'2017-04-07', '09:08:00', 54);\n" +
                "INSERT INTO Trajet VALUES (13, 'Université de Montréal',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-07', '13:12:00', 51);\n" +
                "INSERT INTO Trajet VALUES (61, 'Stade Olympique de Montréal',\t\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-04', '10:32:00', 40);\n" +
                "INSERT INTO Trajet VALUES (61, 'BAnQ Grande Bibliothèque',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-04', '11:29:00', 46);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (44, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-03', '08:01:00', 20);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-03', '16:42:00', 25);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-03', '18:41:00', 25);\n" +
                "INSERT INTO Trajet VALUES (44, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-03', '19:22:00', 26);\n" +
                "INSERT INTO Trajet VALUES (62, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-03', '18:41:00', 25);\n" +
                "INSERT INTO Trajet VALUES (62, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-03', '19:22:00', 26);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-05', '09:13:00', 18);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université McGill',\t\t\t\t\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-05', '11:39:00', 32);\n" +
                "INSERT INTO Trajet VALUES (44, 'BAnQ Grande Bibliothèque',\t\t\t'Université McGill',\t\t\t\t'2017-04-05', '13:58:00', 29);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-05', '17:02:00', 26);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-06', '08:15:00', 28);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '18:17:00', 32);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-07', '09:14:00', 21);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-07', '13:31:00', 19);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-03', '08:01:00', 20);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-03', '16:42:00', 25);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-05', '09:13:00', 18);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-05', '17:02:00', 26);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-06', '08:15:00', 28);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '18:17:00', 32);\n" +
                "INSERT INTO Trajet VALUES (44, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-06', '19:14:00', 14);\n" +
                "INSERT INTO Trajet VALUES (44, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '20:02:00', 23);\n" +
                "INSERT INTO Trajet VALUES (62, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-06', '19:14:00', 14);\n" +
                "INSERT INTO Trajet VALUES (62, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '20:02:00', 23);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université de Montréal',\t\t\t'Université McGill',\t\t\t\t'2017-04-07', '09:14:00', 21);\n" +
                "INSERT INTO Trajet VALUES (14, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-07', '13:31:00', 19);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (45, 'Stade Olympique de Montréal',\t\t'Port de Montréal', \t\t\t\t'2017-04-06', '07:22:00', 16);\n" +
                "INSERT INTO Trajet VALUES (45, 'Port de Montréal',\t\t\t\t\t'Université du Québec à Montréal',\t'2017-04-06', '07:45:00', 28);\n" +
                "INSERT INTO Trajet VALUES (45, 'Université du Québec à Montréal',\t'Université McGill',\t\t\t\t'2017-04-06', '08:34:00', 17);\n" +
                "INSERT INTO Trajet VALUES (45, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '09:38:00', 21);\n" +
                "INSERT INTO Trajet VALUES (45, 'Université de Montréal',\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-06', '10:51:00', 46);\n" +
                "INSERT INTO Trajet VALUES (45, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-06', '13:59:00', 59);\n" +
                "INSERT INTO Trajet VALUES (45, 'Port de Montréal',\t\t\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-06', '15:29:00', 17);\n" +
                "INSERT INTO Trajet VALUES (45, 'Stade Olympique de Montréal',\t\t'Port de Montréal', \t\t\t\t'2017-04-07', '07:11:00', 14);\n" +
                "INSERT INTO Trajet VALUES (45, 'Port de Montréal', \t\t\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-07', '07:42:00', 74);\n" +
                "INSERT INTO Trajet VALUES (45, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-07', '09:14:00', 69);\n" +
                "INSERT INTO Trajet VALUES (45, 'Port de Montréal', \t\t\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-07', '10:57:00', 72);\n" +
                "INSERT INTO Trajet VALUES (45, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-07', '13:01:00', 67);\n" +
                "INSERT INTO Trajet VALUES (45, 'Port de Montréal', \t\t\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-07', '14:45:00', 59);\n" +
                "INSERT INTO Trajet VALUES (45, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-07', '16:13:00', 59);\n" +
                "INSERT INTO Trajet VALUES (45, 'Port de Montréal',\t\t\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-07', '18:07:00', 13);\n" +
                "INSERT INTO Trajet VALUES (15, 'Stade Olympique de Montréal',\t\t'Port de Montréal', \t\t\t\t'2017-04-06', '07:22:00', 14);\n" +
                "INSERT INTO Trajet VALUES (25, 'Port de Montréal',\t\t\t\t\t'Université du Québec à Montréal',\t'2017-04-06', '07:45:00', 28);\n" +
                "INSERT INTO Trajet VALUES (25, 'Université du Québec à Montréal',\t'Université McGill',\t\t\t\t'2017-04-06', '08:34:00', 17);\n" +
                "INSERT INTO Trajet VALUES (25, 'Université McGill',\t\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '09:38:00', 21);\n" +
                "INSERT INTO Trajet VALUES (25, 'Université de Montréal',\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-06', '10:51:00', 46);\n" +
                "INSERT INTO Trajet VALUES (25, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-06', '13:59:00', 59);\n" +
                "INSERT INTO Trajet VALUES (15, 'Port de Montréal',\t\t\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-06', '15:29:00', 13);\n" +
                "INSERT INTO Trajet VALUES (15, 'Stade Olympique de Montréal',\t\t'Port de Montréal', \t\t\t\t'2017-04-07', '07:11:00', 14);\n" +
                "INSERT INTO Trajet VALUES (25, 'Port de Montréal', \t\t\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-07', '07:42:00', 74);\n" +
                "INSERT INTO Trajet VALUES (25, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-07', '09:14:00', 69);\n" +
                "INSERT INTO Trajet VALUES (25, 'Port de Montréal', \t\t\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-07', '10:57:00', 72);\n" +
                "INSERT INTO Trajet VALUES (25, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-07', '13:01:00', 67);\n" +
                "INSERT INTO Trajet VALUES (25, 'Port de Montréal', \t\t\t\t\t'Aéroport Pierre-Elliot-Trudeau',\t'2017-04-07', '14:45:00', 59);\n" +
                "INSERT INTO Trajet VALUES (25, 'Aéroport Pierre-Elliot-Trudeau',\t'Port de Montréal',\t\t\t\t\t'2017-04-07', '16:13:00', 59);\n" +
                "INSERT INTO Trajet VALUES (15, 'Port de Montréal',\t\t\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-07', '18:07:00', 13);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (63, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-04', '09:40:00', 20);\n" +
                "INSERT INTO Trajet VALUES (63, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-04', '10:11:00', 17);\n" +
                "INSERT INTO Trajet VALUES (63, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-04', '10:30:00', 15);\n" +
                "INSERT INTO Trajet VALUES (63, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-04', '10:59:00', 14);\n" +
                "INSERT INTO Trajet VALUES (63, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-04', '13:13:00', 14);\n" +
                "INSERT INTO Trajet VALUES (63, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-04', '14:28:00', 17);\n" +
                "INSERT INTO Trajet VALUES (63, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-06', '09:56:00', 29);\n" +
                "INSERT INTO Trajet VALUES (63, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '10:11:00', 13);\n" +
                "INSERT INTO Trajet VALUES (63, 'Université de Montréal',\t\t\t'Oratoire Saint-Joseph',\t\t\t'2017-04-06', '11:23:00', 23);\n" +
                "INSERT INTO Trajet VALUES (63, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-06', '11:58:00', 12);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (64, 'Université du Québec à Montréal',\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-02', '13:21:00', 32);\n" +
                "INSERT INTO Trajet VALUES (64, 'BAnQ Grande Bibliothèque',\t\t\t'Université du Québec à Montréal',\t'2017-04-02', '15:01:00', 9);\n" +
                "INSERT INTO Trajet VALUES (64, 'Université du Québec à Montréal',\t'Univeristé McGill',\t\t\t\t'2017-04-03', '08:09:00', 48);\n" +
                "INSERT INTO Trajet VALUES (64, 'Univeristé McGill',\t\t\t\t\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-04', '17:35:00', 57);\n" +
                "INSERT INTO Trajet VALUES (64, 'BAnQ Grande Bibliothèque',\t\t\t'Stade Olympique de Montréal',\t\t'2017-04-05', '10:48:00', 143);\n" +
                "\n" +
                "INSERT INTO Trajet VALUES (65, 'Université du Québec à Montréal',\t'Oratoire Saint-Joseph',\t\t\t'2017-04-04', '13:14:00', 121);\n" +
                "INSERT INTO Trajet VALUES (65, 'Oratoire Saint-Joseph',\t\t\t\t'Université de Montréal',\t\t\t'2017-04-04', '16:38:00', 12);\n" +
                "INSERT INTO Trajet VALUES (65, 'Université de Montréal',\t\t\t'Univeristé McGill',\t\t\t\t'2017-04-04', '19:46:00', 156);\n" +
                "INSERT INTO Trajet VALUES (65, 'Univeristé McGill',\t\t\t\t\t'BAnQ Grande Bibliothèque',\t\t\t'2017-04-05', '02:22:00', 53);\n" +
                "INSERT INTO Trajet VALUES (65, 'BAnQ Grande Bibliothèque',\t\t\t'Université McGill',\t\t\t\t'2017-04-05', '15:44:00', 41);"
        );
    }
}