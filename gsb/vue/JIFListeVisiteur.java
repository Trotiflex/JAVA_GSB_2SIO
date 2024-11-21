package gsb.vue;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Fenêtre interne permettant de lister et rechercher des visiteurs.
 * Cette classe affiche la liste des visiteurs dans un tableau et permet à l'utilisateur de rechercher un visiteur 
 * par son matricule. Lorsque le matricule est saisi et validé, la fiche détaillée du visiteur correspondant est affichée.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 17 novembre 2021
 */
public class JIFListeVisiteur extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ArrayList<Visiteur> lesVisiteurs;

    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    protected JTextField JTcodeVisiteur;
    protected JButton JBAfficherFiche;
    protected MenuPrincipal fenetreContainer;

    /**
     * Constructeur de la fenêtre. Il initialise les données des visiteurs à partir de la base de données et crée
     * un tableau avec ces données. Il configure également un champ de recherche pour trouver un visiteur par matricule.
     * 
     * @param uneFenetreContainer Le conteneur principal de la fenêtre, utilisé pour ouvrir la fiche détaillée d'un visiteur.
     */
    public JIFListeVisiteur(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;

        // Récupération des données de visiteurs dans la collection
        lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs();

        int nbLignes = lesVisiteurs.size();
        String[][] data = new String[nbLignes][5]; // 5 colonnes pour afficher les informations

        int i = 0;
        for (Visiteur unVisiteur : lesVisiteurs) {
            data[i][0] = unVisiteur.getMatricule();
            data[i][1] = unVisiteur.getNom();
            data[i][2] = unVisiteur.getPrenom();
            data[i][3] = unVisiteur.getCodePostal();  // Affichage de la ville
            data[i][4] = unVisiteur.getCodeUnit();  // Ajout de l'unité
            i++;
        }

        String[] columnNames = {"Matricule", "Nom", "Prénom", "Code Postal", "Code Unité"};
        JTable table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Panneau principal
        p = new JPanel();
        p.add(scrollPane);

        // Panneau de saisie pour rechercher un visiteur
        pSaisie = new JPanel();
        JTcodeVisiteur = new JTextField(20);
        JTcodeVisiteur.setMaximumSize(JTcodeVisiteur.getPreferredSize());
        JBAfficherFiche = new JButton("Afficher Fiche Visiteur");
        JBAfficherFiche.addActionListener(this);
        pSaisie.add(JTcodeVisiteur);
        pSaisie.add(JBAfficherFiche);
        p.add(pSaisie);

        // Mise en forme de la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(p);

        // Dimensions de la fenêtre
        setSize(650, 400);
        setClosable(true);
        setResizable(true);
        setTitle("Liste des Visiteurs");
        setVisible(true);
    }

    /**
     * Gère l'action de clic sur le bouton "Afficher Fiche Visiteur". Cette méthode permet de rechercher un visiteur
     * par son matricule saisi dans le champ de texte. Si le visiteur est trouvé, sa fiche détaillée est affichée. 
     * Sinon, un message d'erreur est affiché.
     * 
     * @param arg0 L'événement déclenché par l'action du bouton.
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == JBAfficherFiche) {
            // Recherche du visiteur à partir du matricule saisi
            Visiteur unVisiteur = VisiteurDao.rechercher(JTcodeVisiteur.getText());
            if (unVisiteur != null) {
                // Si le visiteur est trouvé, ouvrir la fenêtre avec ses informations
                JIFFicheVisiteur ficheVisiteur = new JIFFicheVisiteur(
                    unVisiteur.getMatricule(),
                    unVisiteur.getNom(),
                    unVisiteur.getPrenom(),
                    unVisiteur.getAdresse(),
                    unVisiteur.getDateEntree(),
                    unVisiteur.getCodeUnit(),
                    unVisiteur.getNomUnit()
                );
                // Ouvrir la fenêtre à travers le menu principal
                fenetreContainer.ouvrirFenetre(ficheVisiteur);
            } else {
                // Si le visiteur n'est pas trouvé, afficher un message d'erreur
                JOptionPane.showMessageDialog(this, "Visiteur non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
