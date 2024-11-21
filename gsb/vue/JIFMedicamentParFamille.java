package gsb.vue;

import gsb.modele.dao.MedicamentDao;
import gsb.modele.Medicament;
import gsb.modele.dao.ConnexionMySql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Fenêtre interne affichant la liste des médicaments d'une famille donnée. 
 * L'utilisateur peut entrer un code famille et rechercher les médicaments 
 * associés à cette famille dans la base de données. Les résultats sont affichés dans un tableau.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 18 novembre 2024
 */
public class JIFMedicamentParFamille extends JInternalFrame {

    /**
     * Identifiant de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    private Connection connection;  // Connexion à la base de données
    private JTable table;  // Tableau pour afficher les résultats des médicaments

    /**
     * Constructeur de la fenêtre de recherche des médicaments par famille.
     * Ce constructeur crée l'interface graphique permettant à l'utilisateur de saisir un code famille 
     * et d'afficher les médicaments associés dans un tableau.
     */
    public JIFMedicamentParFamille() {
        // Configuration de la fenêtre
        setTitle("Médicament par famille");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Ferme uniquement cette fenêtre, sans quitter l'application
        setLocation(100, 100);

        // Création du panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Création du panel supérieur pour la saisie du code famille et le bouton de recherche
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel familleLabel = new JLabel("Famille");
        JTextField familleField = new JTextField(15);
        JButton rechercherButton = new JButton("Rechercher");

        // Ajout des composants au panel supérieur
        topPanel.add(familleLabel);
        topPanel.add(familleField);
        topPanel.add(rechercherButton);

        // Ajout du panel supérieur au panel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Définition des colonnes de la table
        String[] columnNames = {
            "Dépot Légal", "Nom commercial", "Composition", "Effets", 
            "Contre indications", "Code Famille", "Libellé Famille", "Prix Échantillon"
        };

        // Création du tableau avec des données vides au départ
        Object[][] data = {};

        // Initialisation de la table
        table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Ajout de la table au panel principal
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Ajout du panel principal à la fenêtre
        add(mainPanel);

        // Action à exécuter lors du clic sur le bouton "Rechercher"
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupère le code de la famille à rechercher
                String familleRecherchee = familleField.getText().trim();

                // Vérifie si le champ est vide
                if (familleRecherchee.isEmpty()) {
                    JOptionPane.showMessageDialog(JIFMedicamentParFamille.this, "Veuillez entrer un code famille.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Créer la connexion si elle n'existe pas ou si elle est fermée
                    if (connection == null || connection.isClosed()) {
                        connection = ConnexionMySql.getConnection();  // Connexion manuelle à la base
                    }

                    // Recherche des médicaments dans la base de données pour la famille donnée
                    List<Medicament> medicaments = MedicamentDao.rechercherParFamille(connection, familleRecherchee);  // Recherche dans la base de données

                    // Si aucun médicament n'est trouvé, affiche un message
                    if (medicaments.isEmpty()) {
                        JOptionPane.showMessageDialog(JIFMedicamentParFamille.this, "Aucun médicament trouvé pour cette famille.", "Aucun résultat", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Prépare les données pour la table
                    Object[][] tableData = new Object[medicaments.size()][8];  // 8 colonnes

                    for (int i = 0; i < medicaments.size(); i++) {
                        Medicament medicament = medicaments.get(i);
                        tableData[i][0] = medicament.getMedDepotLegal();        // Code dépôt légal
                        tableData[i][1] = medicament.getMedNomCommercial();     // Nom commercial
                        tableData[i][2] = medicament.getMedComposition();       // Composition
                        tableData[i][3] = medicament.getMedEffets();            // Effets
                        tableData[i][4] = medicament.getMedContreIndic();       // Contre-indications
                        tableData[i][5] = medicament.getFamCode();              // Code famille
                        tableData[i][6] = medicament.getFamLibelle();           // Libellé famille
                        tableData[i][7] = medicament.getMedPrixEchantillon();   // Prix de l'échantillon
                    }

                    // Met à jour le modèle de la table avec les nouvelles données
                    table.setModel(new javax.swing.table.DefaultTableModel(tableData, columnNames));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(JIFMedicamentParFamille.this, "Erreur lors de la recherche des médicaments.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Méthode principale pour afficher l'interface graphique de cette fenêtre.
     * 
     * @param args Arguments de ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Affiche l'interface graphique dans le thread de l'EDT
        SwingUtilities.invokeLater(() -> {
            JIFMedicamentParFamille frame = new JIFMedicamentParFamille();
            frame.setVisible(true);
        });
    }
}
