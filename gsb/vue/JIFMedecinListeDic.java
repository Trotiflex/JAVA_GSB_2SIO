package gsb.vue;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Fenêtre interne permettant d'afficher la liste des médecins sous forme de tableau,
 * en utilisant un dictionnaire (`HashMap`) pour stocker les médecins. Cette fenêtre
 * permet également de rechercher un médecin par son code et d'afficher ses détails dans une nouvelle fenêtre.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 17 novembre 2024
 */
public class JIFMedecinListeDic extends JInternalFrame implements ActionListener {

    /**
     * Identifiant de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    // Dictionnaire contenant les médecins indexés par leur code
    private HashMap<String, Medecin> diccoMedecin;

    // Composants graphiques de la fenêtre
    protected JPanel p;  // Panneau principal de la fenêtre
    protected JScrollPane scrollPane;  // Panneau pour afficher le tableau des médecins
    protected JPanel pSaisie;  // Panneau pour les champs de saisie (recherche par code)
    protected JTextField JTcodeMedecin;  // Champ de texte pour saisir le code du médecin
    protected JButton JBafficherFiche;  // Bouton pour afficher la fiche d'un médecin
    protected MenuPrincipal fenetreContainer;  // Référence vers la fenêtre principale
    protected JTable table;  // Tableau pour afficher les données des médecins

    /**
     * Constructeur de la fenêtre de liste des médecins.
     * Ce constructeur initialise la fenêtre en récupérant la liste des médecins à partir
     * du dictionnaire et les affiche dans un tableau. Il inclut également un champ de saisie
     * pour rechercher un médecin par son code.
     * 
     * @param uneFenetreContainer La fenêtre principale dans laquelle cette fenêtre interne sera affichée.
     */
    public JIFMedecinListeDic(MenuPrincipal uneFenetreContainer) {

        fenetreContainer = uneFenetreContainer;
        
        // Récupération des données des médecins dans un dictionnaire
        diccoMedecin = MedecinDao.retournerDictionnaireDesMedecins();
        int nbLignes = diccoMedecin.size();  // Nombre de médecins dans le dictionnaire

        // Initialisation du panneau principal
        p = new JPanel();

        // Création du tableau pour afficher les médecins
        String[][] data = new String[nbLignes][4];  // 4 colonnes pour afficher les informations des médecins
        int i = 0;
        for (Map.Entry<String, Medecin> uneEntree : diccoMedecin.entrySet()) {
            data[i][0] = uneEntree.getValue().getCodeMed();
            data[i][1] = uneEntree.getValue().getNom();
            data[i][2] = uneEntree.getValue().getPrenom();
            data[i][3] = uneEntree.getValue().getLaLocalite().getVille();
            i++;
        }
        String[] columnNames = {"Code", "Nom", "Prénom", "Ville"};
        table = new JTable(data, columnNames);
        
        // Ajout d'un écouteur de sélection de ligne dans le tableau
        table.getSelectionModel().addListSelectionListener(table);

        // Ajout du tableau dans un JScrollPane pour permettre le défilement
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane);

        // Panneau pour les champs de saisie et le bouton
        pSaisie = new JPanel();
        JTcodeMedecin = new JTextField(20);
        JTcodeMedecin.setMaximumSize(JTcodeMedecin.getPreferredSize());
        JBafficherFiche = new JButton("Afficher Fiche Medecin");
        JBafficherFiche.addActionListener(this);  // Source d'événements
        pSaisie.add(JTcodeMedecin);
        pSaisie.add(JBafficherFiche);
        p.add(pSaisie);

        // Mise en forme de la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    /**
     * Action à exécuter lors de l'appui sur un bouton ou la sélection d'une ligne du tableau.
     * Si l'utilisateur clique sur le bouton pour afficher la fiche d'un médecin, la méthode
     * recherche ce médecin dans le dictionnaire et ouvre sa fiche dans une nouvelle fenêtre.
     * 
     * @param arg0 L'événement généré par l'action utilisateur (clic sur un bouton ou sélection dans le tableau).
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        
        // Si l'utilisateur clique sur le bouton pour afficher la fiche du médecin
        if (source == JBafficherFiche) {
            // Vérifie si le médecin existe dans le dictionnaire
            if (diccoMedecin.containsKey(JTcodeMedecin.getText())) {
                Medecin unMedecin = diccoMedecin.get(JTcodeMedecin.getText());
                // Si trouvé, ouvrir la fenêtre de sa fiche
                fenetreContainer.ouvrirFenetre(new JIFMedecinFiche(unMedecin));
            }
        }

        // Si l'utilisateur sélectionne une ligne du tableau
        if (source == table) {
            // Récupère le code du médecin sélectionné et le met dans le champ de texte
            JTcodeMedecin.setText((String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
        }
    }
}
