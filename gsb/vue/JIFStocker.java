package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JIFStocker extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfCodeVisiteur;
    private JTable tableStock;
    private JButton btnRechercher;

    public JIFStocker() {
        setTitle("Stock échantillon pour un visiteur");
        setSize(400, 300);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel pour la saisie du code visiteur
        JPanel panelVisiteur = new JPanel();
        panelVisiteur.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelVisiteur.add(new JLabel("Code visiteur"));
        tfCodeVisiteur = new JTextField(15);
        panelVisiteur.add(tfCodeVisiteur);

        // Bouton Rechercher
        btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique de recherche des échantillons pour le visiteur (à implémenter)
                // Actualiser le tableau des stocks ici
                System.out.println("Rechercher pour visiteur : " + tfCodeVisiteur.getText());
                // Appeler une méthode pour mettre à jour les données de la table
                // updateStockTable(tfCodeVisiteur.getText());
            }
        });
        panelVisiteur.add(btnRechercher);

        add(panelVisiteur, BorderLayout.NORTH);

        // Données du tableau (à remplacer par les données récupérées depuis la base de données)
        String[][] data = {
            {"", "", ""}
        };
        String[] columnNames = {"Code", "Nom", "Stock"};

        // Création de la table avec un modèle par défaut
        tableStock = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tableStock);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        add(scrollPane, BorderLayout.CENTER);
    }

    // Méthode pour actualiser le tableau avec les données du stock
    // public void updateStockTable(String codeVisiteur) {
    //     // Logique pour récupérer les données de stock pour le code visiteur
    //     // et les afficher dans la table
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JIFStocker stockerFrame = new JIFStocker();
            stockerFrame.setVisible(true);
        });
    }
}
