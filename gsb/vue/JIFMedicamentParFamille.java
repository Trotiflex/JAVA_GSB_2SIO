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

public class JIFMedicamentParFamille extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private Connection connection;  // Déclaration de la connexion en tant que membre
    private JTable table;

    public JIFMedicamentParFamille() {
        setTitle("Médicament par famille");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);

        // Création du panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel du haut contenant le label et le champ texte pour la famille
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel familleLabel = new JLabel("Famille");
        JTextField familleField = new JTextField(15);
        JButton rechercherButton = new JButton("Rechercher");

        topPanel.add(familleLabel);
        topPanel.add(familleField);
        topPanel.add(rechercherButton);

        // Ajout du panel du haut au panel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Création des colonnes de la table (mise à jour à 8 colonnes)
        String[] columnNames = {
            "Dépot Légal", "Nom commercial", "Composition", "Effets", 
            "Contre indications", "Code Famille", "Libellé Famille", "Prix Échantillon"
        };

        // Données par défaut de la table (vide pour l'instant)
        Object[][] data = {};

        // Création de la table
        table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Ajout de la table au panel principal
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Création du bouton "FERMER" en bas
        JButton fermerButton = new JButton("FERMER");
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermer la fenêtre et la connexion à la base de données
                dispose();
                fermerConnexion();  // Appel de la méthode pour fermer la connexion
            }
        });

        // Ajout du bouton de fermeture au panel principal
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(fermerButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Ajout du panel principal à la fenêtre
        add(mainPanel);

        // ActionListener pour le bouton Rechercher
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le nom de la famille depuis le champ de texte
                String familleRecherchee = familleField.getText().trim();

                if (familleRecherchee.isEmpty()) {
                    JOptionPane.showMessageDialog(JIFMedicamentParFamille.this, "Veuillez entrer un code famille.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Créer la connexion à la base de données manuellement
                    if (connection == null || connection.isClosed()) {
                        connection = ConnexionMySql.getConnection();  // Connexion manuelle
                    }

                    // Rechercher les médicaments de la famille dans la base de données
                    List<Medicament> medicaments = MedicamentDao.rechercherParFamille(connection, familleRecherchee);  // Passage de la connexion et du paramètre famille

                    // Si aucun médicament n'est trouvé, afficher un message
                    if (medicaments.isEmpty()) {
                        JOptionPane.showMessageDialog(JIFMedicamentParFamille.this, "Aucun médicament trouvé pour cette famille.", "Aucun résultat", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Préparer les données pour le tableau
                    Object[][] tableData = new Object[medicaments.size()][8]; // 8 colonnes

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

                    // Mettre à jour le tableau avec les nouvelles données
                    table.setModel(new javax.swing.table.DefaultTableModel(tableData, columnNames));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(JIFMedicamentParFamille.this, "Erreur lors de la recherche des médicaments.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Méthode pour fermer la connexion à la base de données proprement
    private void fermerConnexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();  // Fermeture explicite de la connexion
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Affiche l'interface graphique
        SwingUtilities.invokeLater(() -> {
            JIFMedicamentParFamille frame = new JIFMedicamentParFamille();
            frame.setVisible(true);
        });
    }
}
