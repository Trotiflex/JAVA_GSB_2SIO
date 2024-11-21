package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.ConnexionMySql;  // Assurez-vous d'importer votre classe de connexion

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;  // Importer Connection
import java.util.List;

/**
 * Fenêtre interne affichant une liste de médicaments récupérée depuis la base de données.
 * Cette fenêtre permet de visualiser les informations de chaque médicament dans un tableau.
 * Il est également possible d'ouvrir la fiche détaillée d'un médicament soit en double-cliquant sur une ligne du tableau,
 * soit en saisissant son code dans un champ de texte et en cliquant sur un bouton.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 2024-11-21
 */
public class JIFListeMedicament extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JTextField txtCode;
    private JButton btnFicheDetaillee;
    private List<Medicament> medicaments;

    /**
     * Constructeur de la fenêtre qui initialise la liste des médicaments à partir de la base de données.
     * Le tableau affiche les médicaments avec leurs informations telles que le code, le nom, le prix et la famille.
     * 
     * @throws Exception Si une erreur de connexion à la base de données se produit.
     */
    public JIFListeMedicament() {
        super("Liste des Médicaments", true, true, true, true);
        setSize(600, 400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Obtenez la connexion à la base de données
        try (Connection connection = ConnexionMySql.getConnection()) {  // Assurez-vous d'avoir une méthode de connexion
            // Récupérer les médicaments depuis la base de données
            medicaments = MedicamentDao.retournerCollectionDesMedicaments(connection);  // Passage de la connexion à la méthode
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Création du tableau
        String[] columnNames = {"Code", "Nom", "Prix", "Famille"};
        String[][] data = new String[medicaments.size()][4];  // Tableau de 4 colonnes
        for (int i = 0; i < medicaments.size(); i++) {
            Medicament med = medicaments.get(i);
            
            data[i][0] = med.getMedDepotLegal();  // Code du médicament (Dépôt légal)
            data[i][1] = med.getMedNomCommercial();  // Nom commercial du médicament
            data[i][2] = med.getFamLibelle();  // Libellé de la famille du médicament
            
            // Assurez-vous de placer correctement le prix
            if (med.getMedPrixEchantillon() != null) {
                data[i][3] = String.valueOf(med.getMedPrixEchantillon());  // Prix de l'échantillon
            } else {
                data[i][3] = "Non défini";  // Cas où le prix est nul
            }
        }

        // Création du tableau avec les données et les colonnes définies
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        // Champ de saisie et bouton
        JPanel panelRecherche = new JPanel();
        txtCode = new JTextField(10);
        btnFicheDetaillee = new JButton("Fiche médicament détaillée");
        btnFicheDetaillee.addActionListener(this);

        panelRecherche.add(new JLabel("Code"));
        panelRecherche.add(txtCode);
        panelRecherche.add(btnFicheDetaillee);

        // Ajout des composants à la fenêtre
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelRecherche, BorderLayout.SOUTH);

        // Double-clic pour ouvrir la fiche détaillée
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Medicament med = medicaments.get(row);
                        ouvrirFicheMedicament(med);
                    }
                }
            }
        });
    }

    /**
     * Gère l'action de clic sur le bouton "Fiche médicament détaillée".
     * Recherche un médicament en fonction du code saisi et ouvre sa fiche détaillée.
     * Si aucun médicament n'est trouvé, un message d'erreur est affiché.
     * 
     * @param e L'événement déclenché par l'action du bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFicheDetaillee) {
            String code = txtCode.getText().trim();
            for (Medicament med : medicaments) {
                if (med.getMedDepotLegal().equals(code)) {
                    ouvrirFicheMedicament(med);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Médicament non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ouvre une fiche détaillée pour un médicament spécifique.
     * Une nouvelle fenêtre `JIFFicheMedicament` est créée et affichée avec les détails du médicament.
     * 
     * @param medicament Le médicament dont la fiche détaillée doit être ouverte.
     */
    private void ouvrirFicheMedicament(Medicament medicament) {
        JIFFicheMedicament fiche = new JIFFicheMedicament(
            medicament.getMedDepotLegal(),
            medicament.getMedNomCommercial(),
            medicament.getMedComposition(),
            medicament.getMedEffets(),
            medicament.getMedContreIndic(),
            medicament.getMedPrixEchantillon(),
            medicament.getFamCode(),
            medicament.getFamLibelle()
        );
        fiche.setVisible(true);

        getParent().add(fiche);
        try {
            fiche.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }
}
