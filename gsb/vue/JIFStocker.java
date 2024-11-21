package gsb.vue;

import gsb.modele.Stocker;
import gsb.modele.dao.StockerDao;
import gsb.modele.Medicament;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * Fenêtre interne permettant de consulter le stock des médicaments pour un visiteur donné.
 * L'utilisateur peut entrer un code visiteur et afficher la liste des médicaments stockés
 * pour ce visiteur dans un tableau.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 18 novembre 2024
 */
public class JIFStocker extends JInternalFrame {

    /**
     * Identifiant de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    private JTextField tfCodeVisiteur;  // Champ de texte pour entrer le code visiteur
    private JTable tableStock;          // Tableau pour afficher le stock des médicaments
    private JButton btnRechercher;      // Bouton pour lancer la recherche du stock

    /**
     * Constructeur de la fenêtre de consultation du stock pour un visiteur donné.
     * Ce constructeur crée l'interface graphique permettant à l'utilisateur de saisir un code visiteur
     * et d'afficher les informations du stock des médicaments dans un tableau.
     * 
     * @param menuPrincipal Référence à l'instance du menu principal, utilisée pour l'intégration dans une application plus large.
     */
    public JIFStocker(MenuPrincipal menuPrincipal) {
        super("Stock Visiteur", true, true, true, true);

        setTitle("Stock des échantillons pour un visiteur");
        setSize(500, 300);
        setLocation(100, 100);
        setLayout(new BorderLayout());

        // Panel pour la saisie du code visiteur
        JPanel panelVisiteur = new JPanel();
        panelVisiteur.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelVisiteur.add(new JLabel("Code visiteur :"));
        tfCodeVisiteur = new JTextField(15);
        panelVisiteur.add(tfCodeVisiteur);

        // Bouton Rechercher
        btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeVisiteur = tfCodeVisiteur.getText().trim();
                if (!codeVisiteur.isEmpty()) {
                    updateStockTable(codeVisiteur);
                } else {
                    JOptionPane.showMessageDialog(JIFStocker.this, "Veuillez entrer un code visiteur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelVisiteur.add(btnRechercher);
        add(panelVisiteur, BorderLayout.NORTH);

        // Configuration du tableau
        String[] columnNames = {"Dépôt Légal", "Nom Médicament", "Quantité en Stock"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableStock = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableStock);
        scrollPane.setPreferredSize(new Dimension(450, 200));
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Méthode pour actualiser le tableau affichant le stock des médicaments pour un visiteur donné.
     * Cette méthode effectue une requête dans la base de données pour récupérer les stocks associés
     * au code visiteur fourni, puis met à jour le tableau avec ces données.
     * 
     * @param codeVisiteur Le code du visiteur dont on souhaite voir le stock des médicaments.
     */
    private void updateStockTable(String codeVisiteur) {
        try {
            // Créez la connexion à la base de données (ajustez avec vos propres paramètres)
            String url = "jdbc:mysql://localhost:3306/gsb"; // Remplacez par l'URL de votre base
            String user = "root"; // Remplacez par votre utilisateur
            String password = ""; // Remplacez par votre mot de passe
            Connection cnx = DriverManager.getConnection(url, user, password);  // Connexion à la base

            // Récupération du stock pour le visiteur donné
            ArrayList<Stocker> listeStock = StockerDao.getStockPourVisiteur(cnx, codeVisiteur);

            // Mise à jour du modèle de table
            DefaultTableModel model = (DefaultTableModel) tableStock.getModel();
            model.setRowCount(0); // Effacer les données existantes

            // Remplir le tableau avec les données de stock
            for (Stocker stock : listeStock) {
                Medicament medicament = stock.getMedDepotLegal();
                Object[] rowData = {
                    medicament.getMedDepotLegal(),       // Dépôt légal
                    medicament.getMedNomCommercial(),    // Nom du médicament
                    stock.getQuantiteStockee()           // Quantité en stock
                };
                model.addRow(rowData);
            }

            // Vérifier si aucune donnée n'a été trouvée
            if (listeStock.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aucun stock trouvé pour le visiteur avec le code " + codeVisiteur, "Aucun résultat", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des stocks : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Méthode principale pour afficher l'interface graphique de cette fenêtre.
     * Elle permet de tester l'affichage de la fenêtre dans une application autonome.
     * 
     * @param args Arguments de ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Stocker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane);

            JIFStocker stockerFrame = new JIFStocker(null);
            desktopPane.add(stockerFrame);
            stockerFrame.setVisible(true);

            frame.setVisible(true);
        });
    }
}
