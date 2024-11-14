package gsb.vue;

import javax.swing.*;
import java.awt.*;

public class JIFFicheMedicament extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtDepotLegal, txtNomCommercial, txtComposition, txtEffets, txtContreIndications, txtPrixEchantillon, txtCodeFamille, txtLibelleFamille;

    // Constructeur modifié pour accepter les paramètres individuels
    public JIFFicheMedicament(String depotLegal, String nomCommercial, String composition, String effets, 
                              String contreIndications, String prixEchantillon, String codeFamille, String libelleFamille) {
        super("Fiche Médicament", true, true, true, true);
        setSize(500, 500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Remplacer les valeurs null par "Non défini"
        depotLegal = (depotLegal != null) ? depotLegal : "Non défini";
        nomCommercial = (nomCommercial != null) ? nomCommercial : "Non défini";
        composition = (composition != null) ? composition : "Non défini";
        effets = (effets != null) ? effets : "Non défini";
        contreIndications = (contreIndications != null) ? contreIndications : "Non défini";
        prixEchantillon = (prixEchantillon != null) ? prixEchantillon : "Non défini";
        codeFamille = (codeFamille != null) ? codeFamille : "Non défini";
        libelleFamille = (libelleFamille != null) ? libelleFamille : "Non défini";

        // Initialisation des champs de texte avec les valeurs (non nulles)
        txtDepotLegal = new JTextField(depotLegal);
        txtNomCommercial = new JTextField(nomCommercial);
        txtComposition = new JTextField(composition);
        txtEffets = new JTextField(effets);
        txtContreIndications = new JTextField(contreIndications);
        txtPrixEchantillon = new JTextField(prixEchantillon);
        txtCodeFamille = new JTextField(codeFamille);
        txtLibelleFamille = new JTextField(libelleFamille);

        // Ajuster la largeur préférée de chaque JTextField dynamiquement en fonction de la longueur du contenu
        txtDepotLegal.setPreferredSize(new Dimension(calculatePreferredWidth(depotLegal), 25));
        txtNomCommercial.setPreferredSize(new Dimension(calculatePreferredWidth(nomCommercial), 25));
        txtComposition.setPreferredSize(new Dimension(calculatePreferredWidth(composition), 25));
        txtEffets.setPreferredSize(new Dimension(calculatePreferredWidth(effets), 25));
        txtContreIndications.setPreferredSize(new Dimension(calculatePreferredWidth(contreIndications), 25));
        txtPrixEchantillon.setPreferredSize(new Dimension(calculatePreferredWidth(prixEchantillon), 25));
        txtCodeFamille.setPreferredSize(new Dimension(calculatePreferredWidth(codeFamille), 25));
        txtLibelleFamille.setPreferredSize(new Dimension(calculatePreferredWidth(libelleFamille), 25));

        // Ajouter les labels et les champs de texte au layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Dépôt légal"), gbc);
        gbc.gridx = 1;
        add(txtDepotLegal, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Nom commercial"), gbc);
        gbc.gridx = 1;
        add(txtNomCommercial, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Composition"), gbc);
        gbc.gridx = 1;
        add(txtComposition, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Effets"), gbc);
        gbc.gridx = 1;
        add(txtEffets, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Contre Indications"), gbc);
        gbc.gridx = 1;
        add(txtContreIndications, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Prix Échantillon"), gbc);
        gbc.gridx = 1;
        add(txtPrixEchantillon, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Code Famille"), gbc);
        gbc.gridx = 1;
        add(txtCodeFamille, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Libellé Famille"), gbc);
        gbc.gridx = 1;
        add(txtLibelleFamille, gbc);

        // Bouton de fermeture
        JButton btnFermer = new JButton("FERMER");
        btnFermer.addActionListener(e -> dispose());
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnFermer, gbc);
    }

    // Méthode pour calculer la largeur préférée en fonction de la longueur du texte
    private int calculatePreferredWidth(String text) {
        int baseWidth = 150; // Largeur de base par défaut
        int extraWidth = text.length() * 7; // Ajouter de la largeur pour chaque caractère
        return Math.max(baseWidth, extraWidth); // Assurez-vous d'utiliser au moins la largeur de base
    }
}
