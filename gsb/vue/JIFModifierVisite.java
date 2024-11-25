package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.VisiteDao;
import gsb.modele.Visite;
import java.sql.Connection;

/**
 * La classe <code>JIFModifierVisite</code> représente une fenêtre interne (JInternalFrame) permettant
 * à l'utilisateur de modifier les informations d'une visite existante dans la base de données.
 * Cette fenêtre affiche un formulaire avec des champs pré-remplis avec les informations de la visite 
 * à modifier. L'utilisateur peut modifier certains champs (comme le matricule du visiteur, le code du médecin 
 * et le commentaire), puis soumettre les modifications pour les enregistrer dans la base de données.
 * 
 * @author Louise
 */
public class JIFModifierVisite extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtReference, txtDateVisite, txtMatricule, txtCodeMed, txtCommentaire;
    private JButton btnModifier;
    private Visite visite;

    /**
     * Constructeur de la classe <code>JIFModifierVisite</code>.
     * Ce constructeur initialise la fenêtre interne avec un formulaire pré-rempli avec les informations 
     * de la visite à modifier. Les informations comme la référence, la date, le matricule du visiteur,
     * le code du médecin et le commentaire sont affichées dans des champs de texte. 
     * Un bouton "Modifier" permet de soumettre les modifications à la base de données.
     *
     * @param visite L'objet <code>Visite</code> représentant la visite à modifier. 
     *              Les informations de cette visite sont affichées dans le formulaire.
     */
    public JIFModifierVisite(Visite visite) {
        super("Mise à jour d'une visite", true, true, true, true);
        setSize(400, 400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        this.visite = visite;

        // Création du panneau principal avec un GridLayout pour organiser les champs
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des champs de saisie pour chaque information
        mainPanel.add(new JLabel("Référence:"));
        txtReference = new JTextField(visite.getReference());
        txtReference.setEditable(false); // Ne peut pas être modifiée
        mainPanel.add(txtReference);

        mainPanel.add(new JLabel("Date visite:"));
        txtDateVisite = new JTextField(visite.getDateVisite().toString());
        mainPanel.add(txtDateVisite);

        mainPanel.add(new JLabel("Matricule visiteur:"));
        txtMatricule = new JTextField(visite.getMatricule());
        mainPanel.add(txtMatricule);

        mainPanel.add(new JLabel("Code médecin:"));
        txtCodeMed = new JTextField(visite.getCodeMed());
        mainPanel.add(txtCodeMed);

        mainPanel.add(new JLabel("Commentaire:"));
        txtCommentaire = new JTextField(visite.getCommentaire());
        mainPanel.add(txtCommentaire);

        // Création du bouton "Modifier"
        btnModifier = new JButton("Modifier");

        // Création du panneau pour le bouton Modifier
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnModifier);

        // Définir le layout de la fenêtre et ajouter les composants
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action associée au bouton "Modifier"
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mise à jour des informations dans l'objet visite
                    visite.setMatricule(txtMatricule.getText());
                    visite.setCodeMed(txtCodeMed.getText());
                    visite.setCommentaire(txtCommentaire.getText());

                    // Connexion à la base de données
                    Connection connection = ConnexionMySql.getConnection(); // Obtenir la connexion à la DB
                    
                    // Appel de la méthode de mise à jour pour modifier la visite dans la base de données
                    int result = VisiteDao.modifier(visite, connection); 

                    // Vérification du succès de la mise à jour
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Visite mise à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la visite.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    // Fermeture de la connexion après l'utilisation
                    connection.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
