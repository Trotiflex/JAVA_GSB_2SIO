package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;

import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.VisiteDao;
import gsb.modele.Visite;

/**
 * La classe <code>JIFAjoutVisite</code> représente une fenêtre interne (JInternalFrame) permettant
 * à l'utilisateur d'ajouter une nouvelle visite dans la base de données. Elle fournit un formulaire
 * avec plusieurs champs de texte pour saisir des informations telles que la référence de la visite, 
 * la date, le commentaire, le matricule du visiteur et le code du médecin. Lors de la soumission du 
 * formulaire, ces informations sont validées et enregistrées dans la base de données via une connexion
 * MySQL.
 *
 * @author Louise
 */
public class JIFAjoutVisite extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtReference, txtDateVisite, txtCommentaire, txtMatricule, txtCodeMed;
    private JButton btnAjouter;

    /**
     * Constructeur de la classe <code>JIFAjoutVisite</code>.
     * Ce constructeur initialise la fenêtre interne avec un formulaire comportant des champs de saisie
     * pour la référence de la visite, la date de la visite, le commentaire, le matricule du visiteur 
     * et le code du médecin. Un bouton "Ajouter" permet de soumettre les données pour les insérer dans 
     * la base de données.
     *
     * La taille de la fenêtre est fixée à 400x250 pixels et la fenêtre est configurée pour se fermer 
     * lorsque l'utilisateur clique sur la croix de fermeture.
     */
    public JIFAjoutVisite() {
        super("Ajout d'une visite", true, true, true, true);
        setSize(400, 250);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Création du panneau principal avec un GridLayout pour organiser les champs
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des champs de saisie pour chaque information
        mainPanel.add(new JLabel("Référence:"));
        txtReference = new JTextField();
        mainPanel.add(txtReference);

        mainPanel.add(new JLabel("Date visite:"));
        txtDateVisite = new JTextField();
        mainPanel.add(txtDateVisite);

        mainPanel.add(new JLabel("Commentaire:"));
        txtCommentaire = new JTextField();
        mainPanel.add(txtCommentaire);

        mainPanel.add(new JLabel("Matricule visiteur:"));
        txtMatricule = new JTextField();
        mainPanel.add(txtMatricule);

        mainPanel.add(new JLabel("Code médecin:"));
        txtCodeMed = new JTextField();
        mainPanel.add(txtCodeMed);

        // Création du bouton "Ajouter"
        btnAjouter = new JButton("Ajouter");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAjouter);

        // Définir le layout de la fenêtre et ajouter les composants
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action associée au bouton "Ajouter"
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les valeurs des champs
                String reference = txtReference.getText();
                String dateVisiteStr = txtDateVisite.getText();
                String commentaire = txtCommentaire.getText();
                String matricule = txtMatricule.getText();
                String codeMed = txtCodeMed.getText();

                // Validation des champs
                if (reference.isEmpty() || dateVisiteStr.isEmpty() || commentaire.isEmpty() || matricule.isEmpty() || codeMed.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Conversion de la date au format SQL
                    java.sql.Date dateVisite = Date.valueOf(dateVisiteStr); // Supposé au format "yyyy-mm-dd"

                    // Création de l'objet Visite
                    Visite nouvelleVisite = new Visite(reference, codeMed, dateVisite, commentaire, matricule, null); // La localité est facultative

                    // Connexion à la base de données
                    Connection connection = ConnexionMySql.getConnection();

                    // Enregistrement de la visite dans la base de données
                    int result = VisiteDao.creer(nouvelleVisite, connection);

                    // Vérification du succès de l'insertion
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Visite ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Échec de l'ajout de la visite.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    // Fermeture de la connexion après utilisation
                    connection.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de la visite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
