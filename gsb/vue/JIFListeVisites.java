package gsb.vue;

import gsb.modele.Visite;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.VisiteDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La classe <code>JIFListeVisites</code> représente une fenêtre interne (JInternalFrame) affichant la liste
 * des visites enregistrées dans la base de données. Elle permet à l'utilisateur de rechercher, consulter les détails,
 * et modifier des visites existantes. La fenêtre présente un tableau avec les visites et propose plusieurs boutons 
 * pour interagir avec les données (détails, mise à jour, recherche).
 * 
 * @author Louise
 */
public class JIFListeVisites extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtCodeVisiteur, txtDateVisite;
    private JTable tableVisites;
    private DefaultTableModel tableModel; // Modèle de données pour le tableau
    private JButton btnDetails, btnRechercher, btnMiseAJour;

    /**
     * Constructeur de la classe <code>JIFListeVisites</code>.
     * Ce constructeur initialise la fenêtre interne avec un formulaire pour la recherche de visites
     * ainsi qu'un tableau affichant les résultats. Il ajoute également des boutons permettant de consulter
     * les détails d'une visite sélectionnée, de la mettre à jour, ou de rechercher des visites selon des critères.
     *
     * @param parentFrame La fenêtre principale qui contiendra cette fenêtre interne.
     *                    Ce paramètre permet d'ouvrir d'autres fenêtres internes depuis cette classe.
     */
    public JIFListeVisites(MenuPrincipal parentFrame) {
        super("Liste des visites", true, true, true, true);
        setSize(600, 400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Panel pour les entrées utilisateur
        JPanel inputPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // 3x3 pour ajouter le bouton Rechercher
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Code visiteur:"));
        txtCodeVisiteur = new JTextField();
        inputPanel.add(txtCodeVisiteur);

        inputPanel.add(new JLabel("Date visite:"));
        txtDateVisite = new JTextField();
        inputPanel.add(txtDateVisite);

        // Bouton Rechercher
        btnRechercher = new JButton("Rechercher");
        inputPanel.add(new JLabel()); // Placeholder pour alignement
        inputPanel.add(btnRechercher);

        // Création du modèle pour le tableau
        String[] columnNames = {"Référence", "Code médecin", "Lieu"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableVisites = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableVisites);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Bouton "Visite détaillée"
        btnDetails = new JButton("Visite détaillée");
        buttonPanel.add(btnDetails);

        // Bouton "Mise à jour visite"
        btnMiseAJour = new JButton("Mise à jour visite");
        buttonPanel.add(btnMiseAJour);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données de la base dans le tableau
        chargerDonnees();

        // Ajouter un ActionListener pour le bouton "Détails"
        btnDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableVisites.getSelectedRow();
                if (selectedRow != -1) {
                    String reference = tableVisites.getValueAt(selectedRow, 0).toString();
                    JIFRecapVisite recapVisite = new JIFRecapVisite(reference);
                    parentFrame.ouvrirFenetre(recapVisite);
                } else {
                    JOptionPane.showMessageDialog(JIFListeVisites.this, "Veuillez sélectionner une visite.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Ajouter un ActionListener pour le bouton "Mise à jour visite"
        btnMiseAJour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableVisites.getSelectedRow();
                if (selectedRow != -1) {
                    String reference = tableVisites.getValueAt(selectedRow, 0).toString();
                    Connection connection = ConnexionMySql.getConnection();
                    Visite visite = VisiteDao.getVisiteByReference(reference, connection );
                    if (visite != null) {
                        // Ouvrir la fenêtre de modification
                        JIFModifierVisite modifierVisiteFrame = new JIFModifierVisite(visite);
                        parentFrame.ouvrirFenetre(modifierVisiteFrame);
                    } else {
                        JOptionPane.showMessageDialog(JIFListeVisites.this, "Erreur lors de la récupération de la visite.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(JIFListeVisites.this, "Veuillez sélectionner une visite.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Ajouter un ActionListener pour le bouton "Rechercher"
        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effectuerRecherche();
            }
        });
    }

    /**
     * Charge les données des visites depuis la base de données et les affiche dans le tableau.
     * Cette méthode interroge la base de données pour récupérer toutes les visites et les ajoute dans le modèle du tableau.
     * Si une erreur survient lors de la connexion à la base de données, un message d'erreur est affiché.
     * 
     * @return void
     */
    private void chargerDonnees() {
        // Effacer les données existantes dans le modèle
        tableModel.setRowCount(0);

        try {
            // Connexion à la base de données
            Connection connection = ConnexionMySql.getConnection();

            // Récupérer les données des visites
            ArrayList<Visite> visites = VisiteDao.retournerCollectionDesVisites(connection);

            // Ajouter chaque visite au modèle du tableau
            for (Visite visite : visites) {
                Object[] rowData = {
                    visite.getReference(),
                    visite.getCodeMed(),
                    visite.getLocalite() != null ? visite.getLocalite().getVille() : "Non spécifié"
                };
                tableModel.addRow(rowData);
            }

            connection.close(); // Fermer la connexion après utilisation

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Effectue une recherche de visites en fonction des critères fournis par l'utilisateur.
     * Si un critère est vide, un message d'erreur est affiché. Si des visites sont trouvées, 
     * elles sont affichées dans le tableau.
     * 
     * @return void
     */
    private void effectuerRecherche() {
        // Effacer les données existantes dans le modèle
        tableModel.setRowCount(0);

        String codeVisiteur = txtCodeVisiteur.getText().trim();
        String dateVisite = txtDateVisite.getText().trim();

        if (codeVisiteur.isEmpty() && dateVisite.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer au moins un critère de recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Connexion à la base de données
            Connection connection = ConnexionMySql.getConnection();

            Visite visite = null;
            if (!codeVisiteur.isEmpty() && !dateVisite.isEmpty()) {
                // Recherche par code visiteur et date
                visite = VisiteDao.rechercherParVisiteurEtDate(dateVisite, codeVisiteur, connection);
            } else if (!codeVisiteur.isEmpty()) {
                // Recherche uniquement par code visiteur
                JOptionPane.showMessageDialog(this, "Veuillez spécifier également une date de visite.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Vérifier si une visite a été trouvée
            if (visite != null) {
                Object[] rowData = {
                    visite.getReference(),
                    visite.getCodeMed(),
                    visite.getLocalite() != null ? visite.getLocalite().getVille() : "Non spécifié"
                };
                tableModel.addRow(rowData);
            } else {
                JOptionPane.showMessageDialog(this, "Aucune visite trouvée pour les critères donnés.", "Résultat", JOptionPane.INFORMATION_MESSAGE);
            }

            connection.close(); // Fermer la connexion après utilisation

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
