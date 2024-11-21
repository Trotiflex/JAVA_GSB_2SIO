package gsb.vue;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Fenêtre interne permettant d'afficher la liste des médecins sous forme de tableau.
 * Cette fenêtre permet également de rechercher un médecin par son code et d'afficher sa fiche détaillée.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 17 novembre 2024
 */
public class JIFMedecinListeCol extends JInternalFrame implements ActionListener {

    /**
     * Identifiant de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<Medecin> lesMedecins; // Liste des médecins récupérés depuis la base de données

    protected JPanel p;  // Panneau principal de la fenêtre
    protected JScrollPane scrollPane;  // Panneau pour afficher le tableau des médecins
    protected JPanel pSaisie;  // Panneau pour les champs de saisie (recherche par code)
    protected JTextField JTcodeMedecin;  // Champ de texte pour saisir le code du médecin
    protected JButton JBafficherFiche;  // Bouton pour afficher la fiche d'un médecin
    protected MenuPrincipal fenetreContainer;  // Référence vers la fenêtre principale

    /**
     * Constructeur de la fenêtre de liste des médecins.
     * Ce constructeur récupère la liste des médecins à partir de la base de données et les affiche
     * dans un tableau. Il inclut également un champ de saisie pour rechercher un médecin par son code.
     * 
     * @param uneFenetreContainer La fenêtre principale dans laquelle cette fenêtre interne sera affichée.
     */
    public JIFMedecinListeCol(MenuPrincipal uneFenetreContainer) {

        fenetreContainer = uneFenetreContainer;
        
        // Récupération des données des médecins dans la collection
        lesMedecins = MedecinDao.retournerCollectionDesMedecins();

        int nbLignes = lesMedecins.size();
        JTable table;

        // Panneau principal
        p = new JPanel();

        // Création du tableau pour afficher les médecins
        String[][] data = new String[nbLignes][4];  // 4 colonnes pour afficher les informations des médecins
        int i = 0;
        for (Medecin unMedecin : lesMedecins) {
            data[i][0] = unMedecin.getCodeMed();
            data[i][1] = unMedecin.getNom();
            data[i][2] = unMedecin.getPrenom();
            data[i][3] = unMedecin.getLaLocalite().getVille();
            i++;
        }
        String[] columnNames = {"Code", "Nom", "Prénom", "Ville"};
        table = new JTable(data, columnNames);

        // Ajout du tableau dans un JScrollPane pour permettre le défilement
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane);

        // Panneau pour les champs de saisie et le bouton
        pSaisie = new JPanel();
        JTcodeMedecin = new JTextField(20);
        JTcodeMedecin.setMaximumSize(JTcodeMedecin.getPreferredSize());
        JBafficherFiche = new JButton("Afficher Fiche Medecin");
        JBafficherFiche.addActionListener(this);
        pSaisie.add(JTcodeMedecin);
        pSaisie.add(JBafficherFiche);
        p.add(pSaisie);

        // Mise en forme de la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    /**
     * Action à exécuter lors de l'appui sur un bouton.
     * Si l'utilisateur appuie sur le bouton pour afficher la fiche d'un médecin,
     * la méthode recherche ce médecin à partir du code saisi et ouvre sa fiche dans une nouvelle fenêtre.
     * 
     * @param arg0 L'événement généré par l'action utilisateur (clic sur un bouton).
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == JBafficherFiche) {
            // Recherche du médecin à partir du code saisi
            Medecin unMedecin = MedecinDao.rechercher(JTcodeMedecin.getText());
            if (unMedecin != null) {
                // Si le médecin est trouvé, ouvrir la fenêtre de sa fiche
                fenetreContainer.ouvrirFenetre(new JIFMedecinFiche(unMedecin));
            }
        }
    }
}
