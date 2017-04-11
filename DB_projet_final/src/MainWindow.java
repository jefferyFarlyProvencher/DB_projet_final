import javax.swing.*;
import java.awt.*;

/**
 * Created by Mihai on 2017-04-10.
 */
public class MainWindow extends JFrame{

    private static class Options {
        private static final String REQUEST_0 = "5 plus longs trajets de véhicules";
        private static final String REQUEST_1 = "Durées moyennes des trajets par type de véhicule pour les personnes possédant plus d'un véhicule";
        private static final String REQUEST_2 = "Lieux visités par les propriétaires d'automobiles";
        private static final String REQUEST_3 = "Coordonnées des 3 destinations les plus fréquentées par les maîtres de chiens";
        private static final String REQUEST_4 = "Trajet le plus fréquent de chaque personne";
        private static final String REQUEST_5 = "Trajets effectués par des maitres promenant leur animal";
        private static final String REQUEST_6 = "Durée moyenne des trajets effectués par des animaux qui ont un maître";
        private static final String REQUEST_7 = "Durée moyenne des trajets selon le type d'objet mobile";
        private static final String REQUEST_8 = "Liste des trajets effectués par des personnes, triée par durée moyenne des trajets";
        private static final String REQUEST_9 = "Liste des trajets effectués par des étudiants et des professeurs";
    }

    private final String[] options = {
            Options.REQUEST_0, Options.REQUEST_1, Options.REQUEST_2, Options.REQUEST_3,
            Options.REQUEST_4, Options.REQUEST_5, Options.REQUEST_6, Options.REQUEST_7,
            Options.REQUEST_8, Options.REQUEST_9,
    };

    private JPanel mainPanel;
    private JButton submitBtn;
    private JComboBox optionsDropDown;
    private JTextArea textArea;

    public MainWindow(){
        init();
        initContent();
        pack();
    }

    private void init(){
        setTitle("Programme Java - BD");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(860, 640));

        mainPanel = new JPanel(new FlowLayout());
        mainPanel.setSize(860, 640);

        add(mainPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    private void initContent(){
        // -- Drop Down.
        optionsDropDown = new JComboBox(options);
        optionsDropDown.setSelectedIndex(0);
        optionsDropDown.setPreferredSize(new Dimension(620, 30));

        // -- Submit Btn.
        submitBtn = new JButton("Submit Query");
        submitBtn.setPreferredSize(new Dimension(120, 30));
        submitBtn.addActionListener((e) -> {
            String response;
            switch(optionsDropDown.getSelectedItem().toString()){
                case Options.REQUEST_0:
                    response = BD.execute(
                            "SELECT Prenom, Nom, Occupation, Type, Duree\n" +
                            "FROM Personne, (SELECT IDPers AS ID_P, Type, Duree\n" +
                            "\t\t\t\tFROM Vehicule, (SELECT IDPers, IDVehicule AS ID_V, Duree\n" +
                            "\t\t\t\t\t\t\t\tFROM Proprietaire, (SELECT IDObjet, Duree\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\tFROM Trajet\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE IDObjet IN (SELECT IDVehicule FROM Vehicule)\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\tORDER BY Duree DESC\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\tLIMIT 5)\n" +
                            "\t\t\t\t\t\t\t\tWHERE IDObjet = IDVehicule)\n" +
                            "\t\t\t\tWHERE IDVehicule = ID_V)\n" +
                            "WHERE IDPers = ID_P;");
                    break;
                case Options.REQUEST_1:
                    response = BD.execute(
                            "SELECT Prenom, Nom, Occupation, Type, Avg_Duree\n" +
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
                            "ORDER BY Prenom, Nom, Avg_Duree DESC;");
                    break;
                case Options.REQUEST_2:
                    response = BD.execute(
                            "SELECT Prenom, Nom, LieuArrivee AS Lieu\n" +
                            "FROM Personne, (SELECT IDObjet AS IDPers, LieuArrivee\n" +
                            "\t\t\t\tFROM Trajet\n" +
                            "\t\t\t\tWHERE IDObjet IN (SELECT IDPers\n" +
                            "\t\t\t\t\t\t\t\t  FROM Proprietaire\n" +
                            "\t\t\t\t\t\t\t\t  WHERE IDVehicule IN (SELECT IDVehicule\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t   FROM Vehicule\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE Type = 'Automobile'))) AS B1\n" +
                            "WHERE Personne.IDPers = B1.IDPers\n" +
                            "GROUP BY Prenom, Nom, Lieu;");
                    break;
                case Options.REQUEST_3:
                    response = BD.execute(
                            "SELECT NomLieu AS Destination, Latitude, Longitude, Frequence\n" +
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
                            "LIMIT 3;");
                    break;
                case Options.REQUEST_4:
                    response = BD.execute(
                            "SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, MAX(Frequence) AS NbParcours\n" +
                            "FROM (SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, COUNT(*) AS Frequence\n" +
                            "\t  FROM Trajet, Personne\n" +
                            "\t  WHERE IDObjet = IDPers\n" +
                            "\t  GROUP BY IDPers, LieuDepart, LieuArrivee)\n" +
                            "GROUP BY Nom, Prenom\n" +
                            "ORDER BY Frequence DESC;");
                    break;
                case Options.REQUEST_5:
                    response = BD.execute(
                            "SELECT Prenom, Nom, NomAnimal, A1.LieuDepart AS Depart, A1.LieuArrivee AS Arrivee, A1.DateDepart AS Date, A1.HeureDepart AS Heure\n" +
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
                            "ORDER BY NomAnimal, Date, Heure;");
                    break;
                case Options.REQUEST_6:
                    response = BD.execute(
                            "SELECT NomEspece, ROUND(AVG(Duree),2) As Avg_Duree\n" +
                            "FROM Trajet, (SELECT IDAnimal, NomEspece\n" +
                            "\t\t\t  FROM Animal\n" +
                            "\t\t\t  WHERE IDAnimal IN (SELECT IDAnimal FROM Maitre))\n" +
                            "WHERE IDObjet = IDAnimal\n" +
                            "GROUP BY NomEspece;");
                    break;
                case Options.REQUEST_7:
                    response = BD.execute(
                            "SELECT Type, ROUND(AVG(Duree),2) AS Avg_Duree\n" +
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
                            "\t\t\t  WHERE IDObjet = IDVehicule) WHERE IDObjet = IDVehicule;");
                    break;
                case Options.REQUEST_8:
                    response = BD.execute(
                            "SELECT LieuDepart, LieuArrivee, MIN(Duree), MAX(Duree), ROUND(AVG(Duree),0) AS Avg_Duree\n" +
                            "FROM Trajet\n" +
                            "WHERE IDObjet IN (SELECT IDPers FROM Personne)\n" +
                            "GROUP BY LieuDepart, LieuArrivee\n" +
                            "ORDER BY Avg_Duree DESC;");
                    break;
                default:
                    response = BD.execute(
                            "SELECT DISTINCT LieuDepart, LieuArrivee\n" +
                            "FROM Trajet\n" +
                            "WHERE IDObjet IN (SELECT IDPers\n" +
                            "\t\t\t\t  FROM Personne\n" +
                            "\t\t\t\t  WHERE Occupation = 'Étudiant' OR Occupation = 'Professeur')\n" +
                            "ORDER BY LieuDepart, LieuArrivee;");
            }

            // --
            setTextAreaContent(response);
        });

        // -- TextView.
        textArea = new JTextArea("no response yet");

        // -- Add all elements to main panel.
        mainPanel.add(optionsDropDown);
        mainPanel.add(submitBtn);
        add(textArea, BorderLayout.CENTER);
    }

    /**
     *
     * @param s : lalalala
     */
    private void setTextAreaContent(String s){
        textArea.setText(s);
    }
}
