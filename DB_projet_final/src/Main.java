/**
 * Created by farlyprj on 17-04-10.
 */

import oracle.jdbc.driver.OracleDriver;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.*;

public class Main {

    public static void main(String[] args)
    {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.iro.umontreal.ca:1521:orcl","farlyprj", "prjp112F" );
        }catch (SQLException e)
        {
            System.out.println("Connection ERROR");
            e.printStackTrace();
        }

        if(connection !=null)
        {
            System.out.println("Connection successful");

            String statement = "SELECT table_name FROM user_tables";

            Statement st = null;

            try{
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(statement);
                while(resultSet.next())
                {
                    System.out.println(resultSet.getString(1)+"\n");
                }
            }

            catch (SQLException e){
                e.printStackTrace();
            }

            finally {

                if(st != null)
                {
                    try {
                        st.close();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            System.out.println("ANOTHER CONNECTION ERROR");
            System.exit(-1);
        }
    }

    public static String selectQuery(int selection)
    {
        switch(selection)
        {
            case 0:

                //Liste des 5 plus longs trajets de véhicules, accompagné du type de véhicule et du nom du propriétaire

                return "SELECT Prenom, Nom, Occupation, Type, Duree\n" +
                    "FROM Personne, (SELECT IDPers AS ID_P, Type, Duree\n" +
                    "\t\t\t\tFROM Vehicule, (SELECT IDPers, IDVehicule AS ID_V, Duree\n" +
                    "\t\t\t\t\t\t\t\tFROM Proprietaire, (SELECT IDObjet, Duree\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tFROM Trajet\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE IDObjet IN (SELECT IDVehicule FROM Vehicule)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tORDER BY Duree DESC\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tLIMIT 5)\n" +
                    "\t\t\t\t\t\t\t\tWHERE IDObjet = IDVehicule)\n" +
                    "\t\t\t\tWHERE IDVehicule = ID_V)\n" +
                    "WHERE IDPers = ID_P;";

            case 1:

                //Durées moyennes des trajets par type de véhicule pour les personnes possédant plus d'un véhicule

                return "SELECT Prenom, Nom, Occupation, Type, Avg_Duree\n" +
                    "FROM Personne, (SELECT IDPers AS ID_P, Type, Avg_Duree\n" +
                    "\t\t\t\tFROM Vehicule, (SELECT IDPers, IDVehicule AS ID_V, ROUND(AVG(Duree),2) AS Avg_Duree\n" +
                    "\t\t\t\t\t\t\t\tFROM Trajet, (SELECT IDPers, IDVehicule\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t  FROM Proprietaire, (SELECT IDPers AS ID_P, COUNT(*) AS NbVehicules\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM Proprietaire\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  GROUP BY IDPers\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  HAVING NbVehicules > 1)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t  WHERE IDPers = ID_P)\n" +
                    "\t\t\t\t\t\t\t\tWHERE IDObjet = IDVehicule\n" +
                    "\t\t\t\t\t\t\t\tGROUP BY IDVehicule)\n" +
                    "\t\t\t\tWHERE IDVehicule = ID_V)\n" +
                    "WHERE IDPers = ID_P\n" +
                    "ORDER BY Prenom, Nom, Avg_Duree DESC;";

            case 2:

                //Lieux visités par les propriétaires d'automobiles

                return "SELECT Prenom, Nom, LieuArrivee AS Lieu\n" +
                        "FROM Personne, (SELECT IDObjet AS IDPers, LieuArrivee\n" +
                        "\t\t\t\tFROM Trajet\n" +
                        "\t\t\t\tWHERE IDObjet IN (SELECT IDPers\n" +
                        "\t\t\t\t\t\t\t\t  FROM Proprietaire\n" +
                        "\t\t\t\t\t\t\t\t  WHERE IDVehicule IN (SELECT IDVehicule\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t   FROM Vehicule\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE Type = 'Automobile'))) AS B1\n" +
                        "WHERE Personne.IDPers = B1.IDPers\n" +
                        "GROUP BY Prenom, Nom, Lieu;";

            case 3:

                //Coordonnées des 3 destinations les plus fréquentées par les maîtres de chiens

                return "SELECT NomLieu AS Destination, Latitude, Longitude, Frequence\n" +
                        "FROM Lieu, (SELECT LieuArrivee, COUNT(*) AS Frequence\n" +
                        "\t\t\tFROM Trajet\n" +
                        "\t\t\tWHERE IDObjet IN (SELECT IDPers\n" +
                        "\t\t\t\t\t\t\t  FROM Maitre\n" +
                        "\t\t\t\t\t\t\t  WHERE IDAnimal IN (SELECT IDAnimal\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t FROM Animal\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t WHERE NomEspece = 'Chien'))\n" +
                        "\t\t   GROUP BY LieuArrivee)\n" +
                        "WHERE NomLieu = LieuArrivee\n" +
                        "GROUP BY Destination\n" +
                        "ORDER BY Frequence DESC\n" +
                        "LIMIT 3;";

            case 4:

                //Trajet le plus fréquent de chaque personne

                return "SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, MAX(Frequence) AS NbParcours\n" +
                        "FROM (SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, COUNT(*) AS Frequence\n" +
                        "\t  FROM Trajet, Personne\n" +
                        "\t  WHERE IDObjet = IDPers\n" +
                        "\t  GROUP BY IDPers, LieuDepart, LieuArrivee)\n" +
                        "GROUP BY Nom, Prenom\n" +
                        "ORDER BY Frequence DESC;";

            case 5:

                //Trajets effectués par des maitres promenant leur animal

                return "SELECT Prenom, Nom, NomAnimal, A1.LieuDepart AS Depart, A1.LieuArrivee AS Arrivee, A1.DateDepart AS Date, A1.HeureDepart AS Heure\n" +
                    "FROM (SELECT Prenom, Nom, LieuDepart, LieuArrivee, DateDepart, HeureDepart\n" +
                    "\t  FROM Trajet, (SELECT IDPers, Prenom, Nom\n" +
                    "\t\t\t\t\tFROM Personne\n" +
                    "\t\t\t\t\tWHERE IDPers IN (SELECT IDPers FROM Maitre))\n" +
                    "\t  WHERE IDPers = IDObjet) AS A1,\n" +
                    "\t (SELECT Nom AS NomAnimal, LieuDepart, LieuArrivee, DateDepart, HeureDepart\n" +
                    "\t  FROM Trajet, (SELECT IDAnimal, Nom\n" +
                    "\t  \t\t\t    FROM Animal\n" +
                    "\t \t\t\t    WHERE IDAnimal IN (SELECT IDAnimal FROM Maitre))\n" +
                    "\t  WHERE IDAnimal = IDObjet) AS A2\n" +
                    "WHERE A1.LieuDepart = A2.LieuDepart AND A1.LieuArrivee = A2.LieuArrivee\n" +
                    "AND A1.DateDepart = A2.DateDepart AND A1.HeureDepart = A2.HeureDepart\n" +
                    "ORDER BY NomAnimal, Date, Heure;";

            case 6:

                //Durée moyenne des trajets effectués par des animaux qui ont un maître

                return "SELECT NomEspece, ROUND(AVG(Duree),2) As Avg_Duree\n" +
                        "FROM Trajet, (SELECT IDAnimal, NomEspece\n" +
                        "\t\t\t  FROM Animal\n" +
                        "\t\t\t  WHERE IDAnimal IN (SELECT IDAnimal FROM Maitre))\n" +
                        "WHERE IDObjet = IDAnimal\n" +
                        "GROUP BY NomEspece;";

            case 7:

                // Durée moyenne des trajets selon le type d'objet mobile

                return "SELECT Type, ROUND(AVG(Duree),2) AS Avg_Duree\n" +
                        "FROM Trajet, (SELECT Type, IDAnimal\n" +
                        "\t\t\t  FROM ObjetMobile, Animal\n" +
                        "\t\t\t  WHERE IDObjet = IDAnimal) WHERE IDObjet = IDAnimal\n" +
                        "UNION\n" +
                        "SELECT Type, ROUND(AVG(Duree),2) AS Avg_Duree\n" +
                        "FROM Trajet, (SELECT Type, IDPers\n" +
                        "\t\t\t  FROM ObjetMobile, Personne\n" +
                        "\t\t\t  WHERE IDObjet = IDPers) WHERE IDObjet = IDPers\n" +
                        "UNION\n" +
                        "SELECT Type, ROUND(AVG(Duree),2) AS Avg_Duree\n" +
                        "FROM Trajet, (SELECT ObjetMobile.Type, IDVehicule\n" +
                        "\t\t\t  FROM ObjetMobile, Vehicule\n" +
                        "\t\t\t  WHERE IDObjet = IDVehicule) WHERE IDObjet = IDVehicule;";

            case 8:

                //Liste des trajets effectués par des personnes, triée par durée moyenne des trajets

                return "SELECT LieuDepart, LieuArrivee, MIN(Duree), MAX(Duree), ROUND(AVG(Duree),0) AS Avg_Duree\n" +
                    "FROM Trajet\n" +
                    "WHERE IDObjet IN (SELECT IDPers FROM Personne)\n" +
                    "GROUP BY LieuDepart, LieuArrivee\n" +
                    "ORDER BY Avg_Duree DESC;";

            case 9:

                //Liste des trajets effectués par des étudiants et des professeurs

                return "SELECT DISTINCT LieuDepart, LieuArrivee\n" +
                    "FROM Trajet\n" +
                    "WHERE IDObjet IN (SELECT IDPers\n" +
                    "\t\t\t\t  FROM Personne\n" +
                    "\t\t\t\t  WHERE Occupation = 'Étudiant' OR Occupation = 'Professeur')\n" +
                    "ORDER BY LieuDepart, LieuArrivee;";

            default: return "-1";

        }
    }
}
