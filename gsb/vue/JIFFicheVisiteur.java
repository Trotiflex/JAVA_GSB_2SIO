package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Fenêtre interne affichant une fiche détaillée d'un visiteur avec ses informations personnelles et professionnelles.
 * Cette fenêtre permet de visualiser des informations telles que le matricule, le nom, le prénom, l'adresse,
 * la date d'entrée dans l'entreprise, le code et le nom de l'unité à laquelle il est affecté.
 * Les informations sont affichées sous forme de champs non modifiables.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 2024-11-21
 */
public class JIFFicheVisiteur extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtMatricule, txtNom, txtPrenom, txtAdresse, txtDateEntree, txtCodeUnit, txtNomUnit;

    /**
     * Constructeur de la fenêtre qui affiche la fiche d'un visiteur avec les informations passées en paramètres.
     * Si une valeur est nulle, la chaîne vide est utilisée pour la date d'entrée. La date est formatée au format "yyyy-MM-dd".
     * 
     * @param matricule Le matricule du visiteur.
     * @param nom Le nom du visiteur.
     * @param prenom Le prénom du visiteur.
     * @param adresse L'adresse du visiteur.
     * @param date La date d'entrée du visiteur dans l'entreprise.
     * @param codeUnit Le code de l'unité à laquelle le visiteur est affecté.
     * @param nomUnit Le nom de l'unité à laquelle le visiteur est affecté.
     */
    public JIFFicheVisiteur(String matricule, String nom, String prenom, String adresse, Date date, String codeUnit, String nomUnit) {
        super("Fiche Visiteur", true, true, true, true);
        setSize(500, 500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Utilisation de GridBagLayout pour un alignement flexible
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Espacement autour des composants

        // Formatage de la date en String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (date != null) ? sdf.format(date) : "";

        // Création des champs de saisie avec les valeurs passées dans le constructeur
        txtMatricule = new JTextField(matricule, 20);
        txtNom = new JTextField(nom, 20);
        txtPrenom = new JTextField(prenom, 20);
        txtAdresse = new JTextField(adresse, 20);
        txtDateEntree = new JTextField(formattedDate, 20);
        txtCodeUnit = new JTextField(codeUnit, 20);
        txtNomUnit = new JTextField(nomUnit, 20);

        // Ajout des composants au panneau avec GridBagConstraints pour l'alignement
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Matricule"), gbc);
        gbc.gridx = 1;
        add(txtMatricule, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Nom"), gbc);
        gbc.gridx = 1;
        add(txtNom, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Prénom"), gbc);
        gbc.gridx = 1;
        add(txtPrenom, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Adresse"), gbc);
        gbc.gridx = 1;
        add(txtAdresse, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Date entrée"), gbc);
        gbc.gridx = 1;
        add(txtDateEntree, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Code Unité"), gbc);
        gbc.gridx = 1;
        add(txtCodeUnit, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Nom Unité"), gbc);
        gbc.gridx = 1;
        add(txtNomUnit, gbc);

        // Ajout du bouton Fermer en bas à droite
        JButton btnFermer = new JButton("FERMER");
        btnFermer.addActionListener(e -> dispose());
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnFermer, gbc);
    }
}
