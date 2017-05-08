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
                    response = BD.execute("select LieuDepart,LieuArrivee, Duree from  trajet,vehicule_ where idobjettrajet = idvehicule order by duree desc OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY;");
                    break;
                case Options.REQUEST_1:
                    response = BD.execute("select IDPers, typeof, avg(duree) from trajet,vehicule_, Proprietaire_ where idvehiculep in (select idvehiculep from Proprietaire_ where idpers in (select IDPers from Proprietaire_ group by IDPers having count(*) > 1)) and idobjettrajet=idvehiculep and idvehicule=idvehiculep  group by IDPers,typeof;\n");
                    break;

                case Options.REQUEST_2:
                    response = BD.execute(
                            "SELECT Prenom, Nom, LieuArrivee AS Lieu " +
                            "FROM Personne, SELECT IDObjet AS IDPers, LieuArrivee " +
                            "FROM Trajet " +
                            "WHERE IDObjet IN SELECT IDPers " +
                            "FROM Proprietaire " +
                            "WHERE IDVehicule IN SELECT IDVehicule " +
                            "FROM Vehicule " +
                            "WHERE Type = 'Automobile' AS B1 " +
                            "WHERE Personne.IDPers = B1.IDPers " +
                            "GROUP BY Prenom, Nom, Lieu;");
                    break;
                case Options.REQUEST_3:

                    //not working here : expression absente

                    response = BD.execute(
                            "SELECT NomLieu AS Destination, Latitude, Longitude, Frequence" +
                            "FROM Lieu, SELECT LieuArrivee, COUNT(*) AS Frequence " +
                            "FROM Trajet " +
                            "WHERE IDObjet IN SELECT IDPers " +
                            "FROM Maitre " +
                            "WHERE IDAnimal IN SELECT IDAnimal " +
                            "FROM Animal " +
                            "WHERE NomEspece = 'Chien' " +
                            "GROUP BY LieuArrivee " +
                            "WHERE NomLieu = LieuArrivee " +
                            "GROUP BY Destination " +
                            "ORDER BY Frequence DESC " +
                            "LIMIT 3;");
                    break;
                case Options.REQUEST_4:
                    response = BD.execute(
                            "SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, MAX(Frequence) AS NbParcours " +
                            "FROM SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, COUNT(*) AS Frequence " +
                            "FROM Trajet, Personne " +
                            "WHERE IDObjet = IDPers " +
                            "GROUP BY IDPers, LieuDepart, LieuArrivee " +
                            "GROUP BY Nom, Prenom " +
                            "ORDER BY Frequence DESC;");
                    break;
                case Options.REQUEST_5:

                    //HERE not working: missing FROM where a FROM is expected

                    response = BD.execute(
                            "SELECT Prenom, Nom, NomAnimal, A1.LieuDepart AS Depart, A1.LieuArrivee AS Arrivee, A1.DateDepart AS Date, A1.HeureDepart AS Heure " +
                            "FROM SELECT Prenom, Nom, LieuDepart, LieuArrivee, DateDepart, HeureDepart " +
                            "FROM Trajet, SELECT IDPers, Prenom, Nom " +
                            "FROM Personne " +
                            "WHERE IDPers IN SELECT IDPers FROM Maitre " +
                            "WHERE IDPers = IDObjet AS A1, " +
                            "SELECT Nom AS NomAnimal, LieuDepart, LieuArrivee, DateDepart, HeureDepart " +
                            "FROM Trajet, SELECT IDAnimal, Nom " +
                            "FROM Animal " +
                            "WHERE IDAnimal IN SELECT IDAnimal FROM Maitre " +
                            "WHERE IDAnimal = IDObjet AS A2 " +
                            "WHERE A1.LieuDepart = A2.LieuDepart AND A1.LieuArrivee = A2.LieuArrivee " +
                            "AND A1.DateDepart = A2.DateDepart AND A1.HeureDepart = A2.HeureDepart " +
                            "ORDER BY NomAnimal, Date, Heure;");
                    break;
                case Options.REQUEST_6:
                    response = BD.execute(
                            "SELECT NomEspece, ROUND(AVG(Duree),2) As Avg_Duree " +
                            "FROM Trajet, SELECT IDAnimal, NomEspece " +
                            "FROM Animal " +
                            "WHERE IDAnimal IN SELECT IDAnimal FROM Maitre " +
                            "WHERE IDObjet = IDAnimal " +
                            "GROUP BY NomEspece;");
                    break;
                case Options.REQUEST_7:
                    response = BD.execute(
                            "SELECT Type, ROUND(AVG(Duree),2) AS Avg_Duree " +
                            "FROM Trajet, SELECT Type, IDAnimal " +
                            "FROM ObjetMobile, Animal " +
                            "WHERE IDObjet = IDAnimal WHERE IDObjet = IDAnimal " +
                            "UNION " +
                            "SELECT Type, ROUND(AVG(Duree),2) AS Avg_Duree " +
                            "FROM Trajet, SELECT Type, IDPers " +
                            "FROM ObjetMobile, Personne " +
                            "WHERE IDObjet = IDPers WHERE IDObjet = IDPers " +
                            "UNION " +
                            "SELECT Type, ROUND(AVG(Duree),2) AS Avg_Duree " +
                            "FROM Trajet, (SELECT ObjetMobile.Type, IDVehicule " +
                            "FROM ObjetMobile, Vehicule " +
                            "WHERE IDObjet = IDVehicule) WHERE IDObjet = IDVehicule;");
                    break;
                case Options.REQUEST_8:

                    //not working expression absente

                    response = BD.execute(
                            "SELECT LieuDepart, LieuArrivee, MIN(Duree), MAX(Duree), ROUND(AVG(Duree),0) AS Avg_Duree " +
                            "FROM Trajet " +
                            "WHERE IDObjet IN SELECT IDPers FROM Personne " +
                            "GROUP BY LieuDepart, LieuArrivee " +
                            "ORDER BY Avg_Duree DESC;");
                    break;
                default:

                    //not working expression absente

                    response = BD.execute(
                            "SELECT DISTINCT LieuDepart, LieuArrivee " +
                            "FROM Trajet " +
                            "WHERE IDObjet IN SELECT IDPers " +
                            "FROM Personne " +
                            "WHERE Occupation = 'Étudiant' OR Occupation = 'Professeur' " +
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
     * @param s : String of answers from DB
     */
    private void setTextAreaContent(String s){
        textArea.setText(s);
    }
}
