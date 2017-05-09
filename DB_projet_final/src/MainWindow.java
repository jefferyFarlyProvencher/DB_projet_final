import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mihai on 2017-04-10.
 */
public class MainWindow extends JFrame{

    private static class Options {
        private static final String REQUEST_0 = "5 plus longs trajets de véhicules";
        private static final String REQUEST_1 = "Durees moyennes des trajets par type de véhicule pour les personnes ayant 1 véhicule et plus";
        private static final String REQUEST_2 = "Lieux visités par les propriétaires d'automobiles";
        private static final String REQUEST_8 = "Coordonnées des 3 destinations les plus fréquentées par les maîtres de chiens";
        private static final String REQUEST_9 = "Trajet le plus fréquent de chaque personne";
        private static final String REQUEST_3 = "Trajets effectués par des maitres promenant leur animal";
        private static final String REQUEST_4 = "Durée moyenne des trajets effectués par des animaux qui ont un maître";
        private static final String REQUEST_5 = "Durée moyenne des trajets selon le type d'objet mobile";
        private static final String REQUEST_6 = "Liste des trajets effectués par des personnes, triée par durée moyenne des trajets";
        private static final String REQUEST_7 = "Liste des trajets effectués par des étudiants et des professeurs";
    }

    private final String[] options = {
            Options.REQUEST_0, Options.REQUEST_1, Options.REQUEST_2, Options.REQUEST_3,
            Options.REQUEST_4, Options.REQUEST_5, Options.REQUEST_6, Options.REQUEST_7,
            Options.REQUEST_8, Options.REQUEST_9,
    };

    private ArrayList<String> LMD;

    private JPanel mainPanel;
    private JButton submitBtn;
    private JComboBox optionsDropDown;
    private JTextArea textArea;
    private JTable jTable;

    public MainWindow(ArrayList<String> LMD){
        this.LMD = LMD;
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
        optionsDropDown.setPreferredSize(new Dimension(700, 30));

        // -- Submit Btn.
        submitBtn = new JButton("Submit Query");
        submitBtn.setPreferredSize(new Dimension(120, 30));
        submitBtn.addActionListener((e) -> {

            String request = LMD.get(optionsDropDown.getSelectedIndex());

            System.out.println(request + '\n');

            String response = BD.execute(request);

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
