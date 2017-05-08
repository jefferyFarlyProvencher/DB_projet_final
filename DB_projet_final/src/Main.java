/**
 * Created by farlyprj on 17-04-10.
 */

import oracle.jdbc.driver.OracleDriver;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.*;

public class Main {


    public static void main(String[] args)
    {
        //initializeBD();
        MainWindow window = new MainWindow();
    }

    private static void initializeBD(){

  //      dropTables();

        for(int i =1; i < 7; i++)
        {
         //   createTables(i);
        }

        BD.execute("\n" +
                "CREATE TABLE Lieu_ (\n" +
                "\tNomLieu\t\t\t\tVARCHAR2(100)\tNOT NULL primary key,\n" +
                "\tVille\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tPays\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tLatitude\t\t\tNUMBER(10,7)\tNOT NULL,\n" +
                "\tLongitude\t\t\tNUMBER(10,7)\tNOT NULL,\n" +
                "\tRayon\t\t\t\tNUMBER(5)\tNOT NULL,\n" +
                "\tCHECK(Latitude BETWEEN -90 AND 90),\n" +
                "\tCHECK(Longitude BETWEEN -180 AND 180),\n" +
                "\tCHECK(Rayon > 0)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "INSERT INTO Lieu_ VALUES ('Parlement du Canada',\t\t\t\t'Ottawa',\t'Canada', 45.4251295, -75.7021194, 175);\n" +
                "INSERT INTO Lieu_ VALUES ('Assemblee nationale du Quebec',\t'Quebec',\t'Canada', 46.8089707, -71.2160001, 80);\n" +
                "INSERT INTO Lieu_ VALUES ('Stade Olympique de Montreal',\t\t'Montreal', 'Canada', 45.5579994, -73.5540703, 200);\n" +
                "INSERT INTO Lieu_ VALUES ('Universite de Montreal',\t\t\t'Montreal',\t'Canada', 45.5056193, -73.6159479, 300);\n" +
                "INSERT INTO Lieu_ VALUES ('Universite McGill',\t\t\t\t'Montreal',\t'Canada', 45.5047884, -73.5793398, 200);\n" +
                "INSERT INTO Lieu_ VALUES ('Universite du Quebec a Montreal',\t'Montreal',\t'Canada', 45.5128576, -73.5672661, 175);\n" +
                "INSERT INTO Lieu_ VALUES ('Oratoire Saint-Joseph',\t\t\t'Montreal',\t'Canada', 45.4925772, -73.6205277, 75);\n" +
                "INSERT INTO Lieu_ VALUES ('BAnQ Grande Bibliothèque',\t\t'Montreal',\t'Canada', 45.515459, -73.5645016, 75);\n" +
                "INSERT INTO Lieu_ VALUES ('Gare dAutocars de Montreal',\t\t'Montreal',\t'Canada', 45.5167516, -73.5659157, 65);\n" +
                "INSERT INTO Lieu_ VALUES ('Port de Montreal',\t\t\t\t'Montreal',\t'Canada', 45.5860436, -73.5085838, 250);\n" +
                "INSERT INTO Lieu_ VALUES ('Aeroport Pierre-Elliot-Trudeau',\t'Montreal',\t'Canada', 45.4697382, -73.7449195, 2500);\n" +
                "\n" +
                "CREATE TABLE ObjetMobile (\n" +
                "\tIDObjet\t\t\t\tNumber(5)\tNOT NULL primary key,\n" +
                "\tTypeof\t\t\t\tVARCHAR2(30)\tNOT NULL\n" +
                ");\n" +
                "\n" +
                "INSERT INTO ObjetMobile VALUES (41, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (42, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (43, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (44, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (45, 'Personne');\n" +
                "INSERT INTO ObjetMobile VALUES (11, 'Vehicule');\n" +
                "INSERT INTO ObjetMobile VALUES (13, 'Vehicule');\n" +
                "INSERT INTO ObjetMobile VALUES (14, 'Vehicule');\n" +
                "INSERT INTO ObjetMobile VALUES (15, 'Vehicule');\n" +
                "INSERT INTO ObjetMobile VALUES (25, 'Vehicule');\n" +
                "INSERT INTO ObjetMobile VALUES (61, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (62, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (63, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (64, 'Animal');\n" +
                "INSERT INTO ObjetMobile VALUES (65, 'Animal');\n" +
                "\n" +
                "CREATE TABLE Personne_ (\n" +
                "\tIDPers\t\t\t\tNUMBER(5)\tNOT NULL primary key,\n" +
                "\tPrenom\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tNom\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                "\tDateNais\t\t\tDATE            NOT NULL,\n" +
                "\tOccupation\t\t\tVARCHAR2(100),\n" +
                "\tCONSTRAINT FKIDPers FOREIGN KEY (IDPers) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE\n" +
                ");\n" +
                "\n" +
                "INSERT INTO Personne_ VALUES (41, 'Jean',\t'Gagnon',\tTO_DATE('06/04/1950', 'DD/MM/YYYY'), 'Fonctionnaire');\n" +
                "INSERT INTO Personne_ VALUES (42, 'Éric',\t'Bertrand',\tTO_DATE('12/07/1992', 'DD/MM/YYYY'), 'Étudiant');\n" +
                "INSERT INTO Personne_ VALUES (43, 'Martine',\t'Cartier',\tTO_DATE('16/11/1987', 'DD/MM/YYYY'), 'Étudiant');\n" +
                "INSERT INTO Personne_ VALUES (44, 'Micheline',\t'Rivard',\tTO_DATE('30/04/1967', 'DD/MM/YYYY'), 'Professeur');\n" +
                "INSERT INTO Personne_ VALUES (45, 'Anne',\t'Sauve',\tTO_DATE('02/03/1951', 'DD/MM/YYYY'), 'Camioneur');\n" +
                "\n" +
                "CREATE TABLE Vehicule_ (\n" +
                "\tIDVehicule\t\t\tNUMBER(5)\t\t\tNOT NULL primary key,\n" +
                "\tNbPassagers\t\t\tNUMBER(5)\t\t\tNOT NULL,\n" +
                "\tTypeof\t\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tImmatriculation\t\t        CHAR(7),\n" +
                "\tCONSTRAINT FKIDVehicule FOREIGN KEY (IDVehicule) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE,\n" +
                "\tCHECK(NbPassagers > 0)\n" +
                ");\n" +
                "\n" +
                "INSERT INTO Vehicule_ VALUES (11, 5, 'Automobile',\t\t'GAR 884');\n" +
                "INSERT INTO Vehicule_ VALUES (13, 1, 'Bicyclette',\t\tNULL);\n" +
                "INSERT INTO Vehicule_ VALUES (14, 1, 'Motocyclette',\t\t'981 039');\n" +
                "INSERT INTO Vehicule_ VALUES (15, 4, 'Automobile',\t\t'XAC 103');\n" +
                "INSERT INTO Vehicule_ VALUES (25, 2, 'Semi-remorque',\t'R398911');\n" +
                "\n" +
                "CREATE TABLE Animal_ (\n" +
                "\tIDAnimal\t\t\tNUMBER(5)\tNOT NULL primary key,\n" +
                "\tNomEspece\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tNom\t\t\t\tVARCHAR2(100),\n" +
                "\tDateNais\t\t\tDATE,\n" +
                "\tCONSTRAINT FKIDAnimal FOREIGN KEY (IDAnimal) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE\n" +
                ");\n" +
                "\n" +
                "INSERT INTO Animal_ VALUES (61, 'Chien', 'Cafe',\t\tTO_DATE('03/04/2015', 'DD/MM/YYYY'));\n" +
                "INSERT INTO Animal_ VALUES (62, 'Chien', 'Fido',\t\tTO_DATE('22/11/2008', 'DD/MM/YYYY'));\n" +
                "INSERT INTO Animal_ VALUES (63, 'Chat', 'Caramel',\tNULL);\n" +
                "INSERT INTO Animal_ VALUES (64, 'Chat', NULL,\t\tNULL);\n" +
                "INSERT INTO Animal_ VALUES (65, 'Chat', NULL,\t\tNULL);\n" +
                "\n" +
                "CREATE TABLE Proprietaire_ (\n" +
                "\tIDPers\t\tNUMBER(5)\t\tNOT NULL,\n" +
                "\tIDVehiculeP\tNUMBER(5)\t\tNOT NULL,\n" +
                "\tDateDebut\tDATE\t\t NOT NULL,\n" +
                "\tDateFin\t\tDATE,\n" +
                "\tCONSTRAINT PKProprietaire PRIMARY KEY (IDPers, IDVehiculeP),\n" +
                "\tCONSTRAINT FKIDPersProp FOREIGN KEY (IDPers) REFERENCES Personne_(IDPers) ON DELETE CASCADE,\n" +
                "\tCONSTRAINT FKIDVehiculeProp FOREIGN KEY (IDVehiculeP) REFERENCES Vehicule_(IDVehicule) ON DELETE CASCADE\n" +
                "\t);\n" +
                "\n" +
                "INSERT INTO Proprietaire_ VALUES (41, 11, TO_DATE('22/08/2010', 'DD/MM/YYYY'), NULL);\n" +
                "INSERT INTO Proprietaire_ VALUES (43, 13, TO_DATE('17/04/2013', 'DD/MM/YYYY'), NULL);\n" +
                "INSERT INTO Proprietaire_ VALUES (44, 14, TO_DATE('06/04/2012', 'DD/MM/YYYY'), NULL);\n" +
                "INSERT INTO Proprietaire_ VALUES (45, 15, TO_DATE('19/11/2009', 'DD/MM/YYYY'), NULL);\n" +
                "INSERT INTO Proprietaire_ VALUES (45, 25, TO_DATE('09/06/2012', 'DD/MM/YYYY'), NULL);\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE TABLE Maitre_ (\n" +
                "\tIDPers\t\t\t\tNUMBER(5)\t\t\tNOT NULL,\n" +
                "\tIDAnimal\t\t\tNUMBER(5)\t\t\tNOT NULL,\n" +
                "\tDateDebut\t\t\tDATE\t\t\tNOT NULL,\n" +
                "\tDateFin\t\t\t\tDATE,\n" +
                "\tCONSTRAINT PKMaitre PRIMARY KEY (IDPers, IDAnimal),\n" +
                "\tCONSTRAINT FKIDPersMaitre FOREIGN KEY (IDPers) REFERENCES Personne_(IDPers) ON DELETE CASCADE,\n" +
                "\tCONSTRAINT FKIDAnimalMaitre FOREIGN KEY (IDAnimal) REFERENCES Animal_(IDAnimal) ON DELETE CASCADE\n" +
                ");\n" +
                "\n" +
                "INSERT INTO Maitre_ VALUES (43, 61, TO_DATE('16/04/2015', 'DD/MM/YYYY'), NULL);\n" +
                "INSERT INTO Maitre_ VALUES (44, 62, TO_DATE('08/06/2010', 'DD/MM/YYYY'), NULL);\n" +
                "INSERT INTO Maitre_ VALUES (42, 63, TO_DATE('04/12/2013', 'DD/MM/YYYY'), NULL);\n" +
                "\n" +
                "CREATE TABLE Trajet(\n" +
                "\tIDObjettrajet\t\t\t\tNUMBER(5)\tNOT NULL,\n" +
                "\tLieuDepart\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tLieuArrivee\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                "\tDate_Time\t\t\tTIMESTAMP\tNOT NULL,\n" +
                "\tDuree\t\t\t\tNUMBER(5)       NOT NULL,\n" +
                "\tCONSTRAINT PKTrajet PRIMARY KEY (idobjettrajet, LieuDepart, LieuArrivee, Date_Time),\n" +
                "\tCHECK(LieuDepart != LieuArrivee)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "INSERT INTO Trajet VALUES(41, 'Assemblee nationale du Quebec',    'Parlement du Canada',         TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),265);\n" +
                "INSERT INTO Trajet VALUES(41, 'Parlement du Canada',    'Universite de Montreal',                TO_TIMESTAMP('07/04/2017 07:12:00', 'DD/MM/YYYY HH24:MI:SS'),128);\n" +
                "INSERT INTO Trajet VALUES(41, 'Universite de Montreal',    'Universite du Quebec a Montreal',    TO_TIMESTAMP('07/04/2017 10:38:00', 'DD/MM/YYYY HH24:MI:SS'),23);\n" +
                "INSERT INTO Trajet VALUES(41, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('07/04/2017 12:05:00', 'DD/MM/YYYY HH24:MI:SS'),12);\n" +
                "INSERT INTO Trajet VALUES(41, 'Universite McGill',    'Assemblee nationale du Quebec',           TO_TIMESTAMP('07/04/2017 13:33:00', 'DD/MM/YYYY HH24:MI:SS'),194);\n" +
                "INSERT INTO Trajet VALUES(11, 'Assemblee nationale du Quebec',    'Parlement du Canada',         TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),265);\n" +
                "INSERT INTO Trajet VALUES(11, 'Parlement du Canada',    'Universite de Montreal',                TO_TIMESTAMP('07/04/2017 07:12:00', 'DD/MM/YYYY HH24:MI:SS'),128);\n" +
                "INSERT INTO Trajet VALUES(11, 'Universite de Montreal',    'Universite du Quebec a Montreal',    TO_TIMESTAMP('07/04/2017 10:38:00', 'DD/MM/YYYY HH24:MI:SS'),23);\n" +
                "INSERT INTO Trajet VALUES(11, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('07/04/2017 12:05:00', 'DD/MM/YYYY HH24:MI:SS'),12);\n" +
                "INSERT INTO Trajet VALUES(11, 'Universite McGill',    'Assemblee nationale du Quebec',           TO_TIMESTAMP('07/04/2017 13:33:00', 'DD/MM/YYYY HH24:MI:SS'),194);\n" +
                "INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('03/04/2017 08:43:00', 'DD/MM/YYYY HH24:MI:SS'),8);\n" +
                "INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'BAnQ Grande Bibliothèque',  TO_TIMESTAMP('03/04/2017 14:12:00', 'DD/MM/YYYY HH24:MI:SS'),5);\n" +
                "INSERT INTO Trajet VALUES(42, 'BAnQ Grande Bibliothèque',    'Gare dAutocars de Montreal',       TO_TIMESTAMP('03/04/2017 15:49:00', 'DD/MM/YYYY HH24:MI:SS'),4);\n" +
                "INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('04/04/2017 08:54:00', 'DD/MM/YYYY HH24:MI:SS'),7);\n" +
                "INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'Gare dAutocars de Montreal',TO_TIMESTAMP('04/04/2017 15:03:00', 'DD/MM/YYYY HH24:MI:SS'),6);\n" +
                "INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('05/04/2017 09:50:00', 'DD/MM/YYYY HH24:MI:SS'),7);\n" +
                "INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'Gare dAutocars de Montreal',TO_TIMESTAMP('05/04/2017 12:03:00', 'DD/MM/YYYY HH24:MI:SS'),8);\n" +
                "INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('06/04/2017 09:41:00', 'DD/MM/YYYY HH24:MI:SS'),8);\n" +
                "INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'BAnQ Grande Bibliothèque',  TO_TIMESTAMP('06/04/2017 14:24:00', 'DD/MM/YYYY HH24:MI:SS'),3);\n" +
                "INSERT INTO Trajet VALUES(42, 'BAnQ Grande Bibliothèque',    'Gare dAutocars de Montreal',       TO_TIMESTAMP('06/04/2017 16:53:00', 'DD/MM/YYYY HH24:MI:SS'),5);\n" +
                "INSERT INTO Trajet VALUES(42, 'Gare dAutocars de Montreal',    'Universite du Quebec a Montreal',TO_TIMESTAMP('07/04/2017 09:33:00', 'DD/MM/YYYY HH24:MI:SS'),9);\n" +
                "INSERT INTO Trajet VALUES(42, 'Universite du Quebec a Montreal',    'Gare dAutocars de Montreal',TO_TIMESTAMP('07/04/2017 14:11:00', 'DD/MM/YYYY HH24:MI:SS'),9);\n" +
                "INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('03/04/2017 08:05:00', 'DD/MM/YYYY HH24:MI:SS'),55);\n" +
                "INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('03/04/2017 15:01:00', 'DD/MM/YYYY HH24:MI:SS'),50);\n" +
                "INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'BAnQ Grande Bibliothèque',      TO_TIMESTAMP('04/04/2017 10:32:00', 'DD/MM/YYYY HH24:MI:SS'),40);\n" +
                "INSERT INTO Trajet VALUES(43, 'BAnQ Grande Bibliothèque',    'Stade Olympique de Montreal',      TO_TIMESTAMP('04/04/2017 11:29:00', 'DD/MM/YYYY HH24:MI:SS'),46);\n" +
                "INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),52);\n" +
                "INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('05/04/2017 11:59:00', 'DD/MM/YYYY HH24:MI:SS'),9);\n" +
                "INSERT INTO Trajet VALUES(43, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('05/04/2017 12:48:00', 'DD/MM/YYYY HH24:MI:SS'),11);\n" +
                "INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('05/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),48);\n" +
                "INSERT INTO Trajet VALUES(43, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('07/04/2017 09:08:00', 'DD/MM/YYYY HH24:MI:SS'),54);\n" +
                "INSERT INTO Trajet VALUES(43, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('07/04/2017 13:12:00', 'DD/MM/YYYY HH24:MI:SS'),51);\n" +
                "INSERT INTO Trajet VALUES(13, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('03/04/2017 08:05:00', 'DD/MM/YYYY HH24:MI:SS'),55);\n" +
                "INSERT INTO Trajet VALUES(13, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('03/04/2017 15:01:00', 'DD/MM/YYYY HH24:MI:SS'),50);\n" +
                "INSERT INTO Trajet VALUES(13, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('05/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),52);\n" +
                "INSERT INTO Trajet VALUES(13, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('05/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),48);\n" +
                "INSERT INTO Trajet VALUES(13, 'Stade Olympique de Montreal',    'Universite de Montreal',        TO_TIMESTAMP('07/04/2017 09:08:00', 'DD/MM/YYYY HH24:MI:SS'),54);\n" +
                "INSERT INTO Trajet VALUES(13, 'Universite de Montreal',    'Stade Olympique de Montreal',        TO_TIMESTAMP('07/04/2017 13:12:00', 'DD/MM/YYYY HH24:MI:SS'),51);\n" +
                "INSERT INTO Trajet VALUES(61, 'Stade Olympique de Montreal',    'BAnQ Grande Bibliothèque',      TO_TIMESTAMP('04/04/2017 10:32:00', 'DD/MM/YYYY HH24:MI:SS'),40);\n" +
                "INSERT INTO Trajet VALUES(61, 'BAnQ Grande Bibliothèque',    'Stade Olympique de Montreal',      TO_TIMESTAMP('04/04/2017 11:29:00', 'DD/MM/YYYY HH24:MI:SS'),46);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('03/04/2017 08:01:00', 'DD/MM/YYYY HH24:MI:SS'),20);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('03/04/2017 16:42:00', 'DD/MM/YYYY HH24:MI:SS'),25);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('03/04/2017 18:41:00', 'DD/MM/YYYY HH24:MI:SS'),25);\n" +
                "INSERT INTO Trajet VALUES(44, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('03/04/2017 19:22:00', 'DD/MM/YYYY HH24:MI:SS'),26);\n" +
                "INSERT INTO Trajet VALUES(62, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('03/04/2017 18:41:00', 'DD/MM/YYYY HH24:MI:SS'),25);\n" +
                "INSERT INTO Trajet VALUES(62, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('03/04/2017 19:22:00', 'DD/MM/YYYY HH24:MI:SS'),26);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('05/04/2017 09:13:00', 'DD/MM/YYYY HH24:MI:SS'),18);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite McGill',    'BAnQ Grande Bibliothèque',                TO_TIMESTAMP('05/04/2017 11:39:00', 'DD/MM/YYYY HH24:MI:SS'),32);\n" +
                "INSERT INTO Trajet VALUES(44, 'BAnQ Grande Bibliothèque',    'Universite McGill',                TO_TIMESTAMP('05/04/2017 13:58:00', 'DD/MM/YYYY HH24:MI:SS'),29);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('05/04/2017 17:02:00', 'DD/MM/YYYY HH24:MI:SS'),26);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('06/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),28);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 18:17:00', 'DD/MM/YYYY HH24:MI:SS'),32);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),21);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('07/04/2017 13:31:00', 'DD/MM/YYYY HH24:MI:SS'),19);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('03/04/2017 08:01:00', 'DD/MM/YYYY HH24:MI:SS'),20);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('03/04/2017 16:42:00', 'DD/MM/YYYY HH24:MI:SS'),25);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('05/04/2017 09:13:00', 'DD/MM/YYYY HH24:MI:SS'),18);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('05/04/2017 17:02:00', 'DD/MM/YYYY HH24:MI:SS'),26);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('06/04/2017 08:15:00', 'DD/MM/YYYY HH24:MI:SS'),28);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 18:17:00', 'DD/MM/YYYY HH24:MI:SS'),32);\n" +
                "INSERT INTO Trajet VALUES(44, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 19:14:00', 'DD/MM/YYYY HH24:MI:SS'),14);\n" +
                "INSERT INTO Trajet VALUES(44, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 20:02:00', 'DD/MM/YYYY HH24:MI:SS'),3);\n" +
                "INSERT INTO Trajet VALUES(62, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 19:14:00', 'DD/MM/YYYY HH24:MI:SS'),14);\n" +
                "INSERT INTO Trajet VALUES(62, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 20:02:00', 'DD/MM/YYYY HH24:MI:SS'),23);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),21);\n" +
                "INSERT INTO Trajet VALUES(14, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('07/04/2017 13:31:00', 'DD/MM/YYYY HH24:MI:SS'),19);\n" +
                "INSERT INTO Trajet VALUES(45, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('06/04/2017 07:22:00', 'DD/MM/YYYY HH24:MI:SS'),16);\n" +
                "INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Universite du Quebec a Montreal',          TO_TIMESTAMP('06/04/2017 07:45:00', 'DD/MM/YYYY HH24:MI:SS'),28);\n" +
                "INSERT INTO Trajet VALUES(45, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('06/04/2017 08:34:00', 'DD/MM/YYYY HH24:MI:SS'),17);\n" +
                "INSERT INTO Trajet VALUES(45, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 09:38:00', 'DD/MM/YYYY HH24:MI:SS'),21);\n" +
                "INSERT INTO Trajet VALUES(45, 'Universite de Montreal',    'Aeroport Pierre-Elliot-Trudeau',     TO_TIMESTAMP('06/04/2017 10:51:00', 'DD/MM/YYYY HH24:MI:SS'),46);\n" +
                "INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('06/04/2017 13:59:00', 'DD/MM/YYYY HH24:MI:SS'),59);\n" +
                "INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('06/04/2017 15:29:00', 'DD/MM/YYYY HH24:MI:SS'),17);\n" +
                "INSERT INTO Trajet VALUES(45, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('07/04/2017 07:11:00', 'DD/MM/YYYY HH24:MI:SS'),14);\n" +
                "INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 07:42:00', 'DD/MM/YYYY HH24:MI:SS'),74);\n" +
                "INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),69);\n" +
                "INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 10:57:00', 'DD/MM/YYYY HH24:MI:SS'),72);\n" +
                "INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 13:01:00', 'DD/MM/YYYY HH24:MI:SS'),67);\n" +
                "INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),59);\n" +
                "INSERT INTO Trajet VALUES(45, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 16:13:00', 'DD/MM/YYYY HH24:MI:SS'),59);\n" +
                "INSERT INTO Trajet VALUES(45, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('07/04/2017 18:07:00', 'DD/MM/YYYY HH24:MI:SS'),13);\n" +
                "INSERT INTO Trajet VALUES(15, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('06/04/2017 07:22:00', 'DD/MM/YYYY HH24:MI:SS'),14);\n" +
                "INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Universite du Quebec a Montreal',          TO_TIMESTAMP('06/04/2017 07:45:00', 'DD/MM/YYYY HH24:MI:SS'),28);\n" +
                "INSERT INTO Trajet VALUES(25, 'Universite du Quebec a Montreal',    'Universite McGill',         TO_TIMESTAMP('06/04/2017 08:34:00', 'DD/MM/YYYY HH24:MI:SS'),17);\n" +
                "INSERT INTO Trajet VALUES(25, 'Universite McGill',    'Universite de Montreal',                  TO_TIMESTAMP('06/04/2017 09:38:00', 'DD/MM/YYYY HH24:MI:SS'),21);\n" +
                "INSERT INTO Trajet VALUES(25, 'Universite de Montreal',    'Aeroport Pierre-Elliot-Trudeau',     TO_TIMESTAMP('06/04/2017 10:51:00', 'DD/MM/YYYY HH24:MI:SS'),46);\n" +
                "INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('06/04/2017 13:59:00', 'DD/MM/YYYY HH24:MI:SS'),59);\n" +
                "INSERT INTO Trajet VALUES(15, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('06/04/2017 15:29:00', 'DD/MM/YYYY HH24:MI:SS'),13);\n" +
                "INSERT INTO Trajet VALUES(15, 'Stade Olympique de Montreal',    'Port de Montreal',              TO_TIMESTAMP('07/04/2017 07:11:00', 'DD/MM/YYYY HH24:MI:SS'),14);\n" +
                "INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 07:42:00', 'DD/MM/YYYY HH24:MI:SS'),74);\n" +
                "INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 09:14:00', 'DD/MM/YYYY HH24:MI:SS'),69);\n" +
                "INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 10:57:00', 'DD/MM/YYYY HH24:MI:SS'),72);\n" +
                "INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 13:01:00', 'DD/MM/YYYY HH24:MI:SS'),67);\n" +
                "INSERT INTO Trajet VALUES(25, 'Port de Montreal',    'Aeroport Pierre-Elliot-Trudeau',           TO_TIMESTAMP('07/04/2017 14:45:00', 'DD/MM/YYYY HH24:MI:SS'),59);\n" +
                "INSERT INTO Trajet VALUES(25, 'Aeroport Pierre-Elliot-Trudeau',    'Port de Montreal',           TO_TIMESTAMP('07/04/2017 16:13:00', 'DD/MM/YYYY HH24:MI:SS'),59);\n" +
                "INSERT INTO Trajet VALUES(15, 'Port de Montreal',    'Stade Olympique de Montreal',              TO_TIMESTAMP('07/04/2017 18:07:00', 'DD/MM/YYYY HH24:MI:SS'),13);\n" +
                "INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('04/04/2017 09:40:00', 'DD/MM/YYYY HH24:MI:SS'),20);\n" +
                "INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 10:11:00', 'DD/MM/YYYY HH24:MI:SS'),17);\n" +
                "INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('04/04/2017 10:30:00', 'DD/MM/YYYY HH24:MI:SS'),15);\n" +
                "INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 10:59:00', 'DD/MM/YYYY HH24:MI:SS'),14);\n" +
                "INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('04/04/2017 13:13:00', 'DD/MM/YYYY HH24:MI:SS'),14);\n" +
                "INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 14:28:00', 'DD/MM/YYYY HH24:MI:SS'),17);\n" +
                "INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 09:56:00', 'DD/MM/YYYY HH24:MI:SS'),29);\n" +
                "INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 10:11:00', 'DD/MM/YYYY HH24:MI:SS'),13);\n" +
                "INSERT INTO Trajet VALUES(63, 'Universite de Montreal',    'Oratoire Saint-Joseph',              TO_TIMESTAMP('06/04/2017 11:23:00', 'DD/MM/YYYY HH24:MI:SS'),23);\n" +
                "INSERT INTO Trajet VALUES(63, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('06/04/2017 11:58:00', 'DD/MM/YYYY HH24:MI:SS'),12);\n" +
                "INSERT INTO Trajet VALUES(64, 'Universite du Quebec a Montreal',    'BAnQ Grande Bibliothèque',  TO_TIMESTAMP('02/04/2017 13:21:00', 'DD/MM/YYYY HH24:MI:SS'),32);\n" +
                "INSERT INTO Trajet VALUES(64, 'BAnQ Grande Bibliothèque',    'Universite du Quebec a Montreal',  TO_TIMESTAMP('02/04/2017 15:01:00', 'DD/MM/YYYY HH24:MI:SS'),9);\n" +
                "INSERT INTO Trajet VALUES(64, 'Universite du Quebec à Montreal',    'Univeriste McGill',         TO_TIMESTAMP('03/04/2017 08:09:00', 'DD/MM/YYYY HH24:MI:SS'),48);\n" +
                "INSERT INTO Trajet VALUES(64, 'Universite McGill',    'BAnQ Grande Bibliothèque',                TO_TIMESTAMP('04/04/2017 17:35:00', 'DD/MM/YYYY HH24:MI:SS'),57);\n" +
                "INSERT INTO Trajet VALUES(64, 'BAnQ Grande Bibliothèque',    'Stade Olympique de Montreal',      TO_TIMESTAMP('05/04/2017 10:48:00', 'DD/MM/YYYY HH24:MI:SS'),143);\n" +
                "INSERT INTO Trajet VALUES(65, 'Universite du Quebec à Montreal',    'Oratoire Saint-Joseph',     TO_TIMESTAMP('04/04/2017 13:14:00', 'DD/MM/YYYY HH24:MI:SS'),121);\n" +
                "INSERT INTO Trajet VALUES(65, 'Oratoire Saint-Joseph',    'Universite de Montreal',              TO_TIMESTAMP('04/04/2017 16:38:00', 'DD/MM/YYYY HH24:MI:SS'),12);\n" +
                "INSERT INTO Trajet VALUES(65, 'Universite de Montreal',    'Universite McGill',                  TO_TIMESTAMP('04/04/2017 19:46:00', 'DD/MM/YYYY HH24:MI:SS'),156);\n" +
                "INSERT INTO Trajet VALUES(65, 'Universite McGill',    'BAnQ Grande Bibliothèque',                TO_TIMESTAMP('05/04/2017 02:22:00', 'DD/MM/YYYY HH24:MI:SS'),53);\n" +
                "INSERT INTO Trajet VALUES(65, 'BAnQ Grande Bibliothèque',    'Universite McGill',                TO_TIMESTAMP('05/04/2017 15:44:00', 'DD/MM/YYYY HH24:MI:SS'),41);\n" +
                "\n"

        );
    }

    private static void dropTables()
    {
        BD.execute("DROP TABLE Lieu_;\n" +
                "DROP TABLE ObjetMobile;\n" +
                "DROP TABLE Personne_;\n" +
                "DROP TABLE Vehicule_;\n" +
                "DROP TABLE Animal_;\n" +
                "DROP TABLE Proprietaire_;\n" +
                "DROP TABLE Maitre_;\n" +
                "DROP TABLE Trajet;");
    }

    private static void createTables(int which_table)
    {
        switch (which_table)
        {
            // Lieu
            case 1: BD.execute("CREATE TABLE Lieu_ (\n" +
                    "\tNomLieu\t\t\t\tVARCHAR2(100)\tNOT NULL primary key,\n" +
                    "\tVille\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                    "\tPays\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                    "\tLatitude\t\t\tNUMBER(10,7)\tNOT NULL,\n" +
                    "\tLongitude\t\t\tNUMBER(10,7)\tNOT NULL,\n" +
                    "\tRayon\t\t\t\tNUMBER(5)\tNOT NULL,\n" +
                    "\tCHECK(Latitude BETWEEN -90 AND 90),\n" +
                    "\tCHECK(Longitude BETWEEN -180 AND 180),\n" +
                    "\tCHECK(Rayon > 0)\n" +
                    ");");

            // ObjetMobile
            case 2: BD.execute("CREATE TABLE ObjetMobile (\n" +
                    "\tIDObjet\t\t\t\tNumber(5)\tNOT NULL primary key,\n" +
                    "\tTypeof\t\t\t\tVARCHAR2(30)\tNOT NULL\n" +
                    ");");

            //Personne
            case 3: BD.execute("CREATE TABLE Personne_ (\n" +
                    "\tIDPers\t\t\t\tNUMBER(5)\tNOT NULL primary key,\n" +
                    "\tPrenom\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                    "\tNom\t\t\t\tVARCHAR2(30)\tNOT NULL,\n" +
                    "\tDateNais\t\t\tDATE            NOT NULL,\n" +
                    "\tOccupation\t\t\tVARCHAR2(100),\n" +
                    "\tCONSTRAINT FKIDPers FOREIGN KEY (IDPers) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE\n" +
                    ");");

            //Vehicule
            case 4: BD.execute("CREATE TABLE Vehicule_ (\n" +
                    "\tIDVehicule\t\t\tNUMBER(5)\t\t\tNOT NULL primary key,\n" +
                    "\tNbPassagers\t\t\tNUMBER(5)\t\t\tNOT NULL,\n" +
                    "\tTypeof\t\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                    "\tImmatriculation\t\t        CHAR(7),\n" +
                    "\tCONSTRAINT FKIDVehicule FOREIGN KEY (IDVehicule) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE,\n" +
                    "\tCHECK(NbPassagers > 0)\n" +
                    ");");

            //Animal
            case 5: BD.execute("CREATE TABLE Animal_ (\n" +
                    "\tIDAnimal\t\t\tNUMBER(5)\tNOT NULL primary key,\n" +
                    "\tNomEspece\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                    "\tNom\t\t\t\tVARCHAR2(100),\n" +
                    "\tDateNais\t\t\tDATE,\n" +
                    "\tCONSTRAINT FKIDAnimal FOREIGN KEY (IDAnimal) REFERENCES ObjetMobile(IDObjet) ON DELETE CASCADE\n" +
                    ");");

            //Proprietaire
            case 6: BD.execute("CREATE TABLE Proprietaire_ (\n" +
                    "\tIDPers\t\tNUMBER(5)\t\tNOT NULL,\n" +
                    "\tIDVehiculeP\tNUMBER(5)\t\tNOT NULL,\n" +
                    "\tDateDebut\tDATE\t\t NOT NULL,\n" +
                    "\tDateFin\t\tDATE,\n" +
                    "\tCONSTRAINT PKProprietaire PRIMARY KEY (IDPers, IDVehiculeP),\n" +
                    "\tCONSTRAINT FKIDPersProp FOREIGN KEY (IDPers) REFERENCES Personne_(IDPers) ON DELETE CASCADE,\n" +
                    "\tCONSTRAINT FKIDVehiculeProp FOREIGN KEY (IDVehiculeP) REFERENCES Vehicule_(IDVehicule) ON DELETE CASCADE\n" +
                    "\t);");

            //Maitre
            case 7: BD.execute("CREATE TABLE Maitre_ (\n" +
                    "\tIDPers\t\t\t\tNUMBER(5)\t\t\tNOT NULL,\n" +
                    "\tIDAnimal\t\t\tNUMBER(5)\t\t\tNOT NULL,\n" +
                    "\tDateDebut\t\t\tDATE\t\t\tNOT NULL,\n" +
                    "\tDateFin\t\t\t\tDATE,\n" +
                    "\tCONSTRAINT PKMaitre PRIMARY KEY (IDPers, IDAnimal),\n" +
                    "\tCONSTRAINT FKIDPersMaitre FOREIGN KEY (IDPers) REFERENCES Personne_(IDPers) ON DELETE CASCADE,\n" +
                    "\tCONSTRAINT FKIDAnimalMaitre FOREIGN KEY (IDAnimal) REFERENCES Animal_(IDAnimal) ON DELETE CASCADE\n" +
                    ");");

            //Trajet
            case 8: BD.execute("CREATE TABLE Trajet(\n" +
                    "\tIDObjettrajet\t\t\t\tNUMBER(5)\tNOT NULL,\n" +
                    "\tLieuDepart\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                    "\tLieuArrivee\t\t\tVARCHAR2(100)\tNOT NULL,\n" +
                    "\tDate_Time\t\t\tTIMESTAMP\tNOT NULL,\n" +
                    "\tDuree\t\t\t\tNUMBER(5)       NOT NULL,\n" +
                    "\tCONSTRAINT PKTrajet PRIMARY KEY (idobjettrajet, LieuDepart, LieuArrivee, Date_Time),\n" +
                    "\tCHECK(LieuDepart != LieuArrivee)\n" +
                    ");");
        }
    }


}