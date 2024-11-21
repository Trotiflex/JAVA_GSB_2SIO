package gsb.vue;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre interne affichant une fiche détaillée d'un médicament avec ses informations.
 * Cette fenêtre permet de visualiser des informations telles que le dépôt légal, le nom commercial,
 * la composition, les effets, les contre-indications, le prix de l'échantillon, le code et le libellé de la famille.
 * Les informations sont affichées sous forme de champs non modifiables.
 * 
 * @author Trotiflex
 * @version 1.1
 * @since 2024-11-21
 */
public class JIFFicheMedicament extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtDepotLegal, txtNomCommercial, txtComposition, txtEffets, txtContreIndications, txtPrixEchantillon, txtCodeFamille, txtLibelleFamille;

    /**
     * Constructeur de la fenêtre qui affiche la fiche d'un médicament avec les informations passées en paramètres.
     * Si une valeur est nulle, le texte "Non défini" est utilisé comme valeur par défaut.
     * Les champs sont dynamiquement redimensionnés en fonction du contenu de chaque information.
     * 
     * @param depotLegal Le dépôt légal du médicament.
     * @param nomCommercial Le nom commercial du médicament.
     * @param composition La composition du médicament.
     * @param effets Les effets du médicament.
     * @param contreIndications Les contre-indications du médicament.
     * @param prixEchantillon Le prix de l'échantillon du médicament.
     * @param codeFamille Le code de la famille du médicament.
     * @param libelleFamille Le libellé de la famille du médicament.
     */
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

    /**
     * Calcule la largeur préférée d'un champ de texte en fonction de la longueur du texte qu'il contient.
     * 
     * @param text Le texte à analyser pour déterminer la largeur préférée.
     * @return La largeur préférée calculée pour le champ de texte.
     */
    private int calculatePreferredWidth(String text) {
        int baseWidth = 150; // Largeur de base par défaut
        int extraWidth = text.length() * 7; // Ajouter de la largeur pour chaque caractère
        return Math.max(baseWidth, extraWidth); // Assurez-vous d'utiliser au moins la largeur de base
    }
}
