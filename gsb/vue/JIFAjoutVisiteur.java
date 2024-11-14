package gsb.vue;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class JIFAjoutVisiteur extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtMatricule, txtNom, txtPrenom, txtAdresse, txtLogin, txtMdp;
    private JTextField txtCodePostal, txtVille, txtDateEntree, txtCodeUnit, txtNomUnit;
    private JButton btnAjouter, btnAnnuler;

    public JIFAjoutVisiteur() {
        super("Ajout Visiteur", true, true, true, true);
        setSize(400, 350);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Titre de la fenêtre
        JLabel label = new JLabel("Ajoutez un visiteur ici.");
        label.setHorizontalAlignment(JLabel.CENTER); // Centrer le titre
        add(label, BorderLayout.NORTH);

        // Création des champs et des labels pour chaque attribut
        JLabel lblMatricule = new JLabel("Matricule :");
        txtMatricule = new JTextField(10);

        JLabel lblNom = new JLabel("Nom :");
        txtNom = new JTextField(10);

        JLabel lblPrenom = new JLabel("Prénom :");
        txtPrenom = new JTextField(10);

        JLabel lblLogin = new JLabel("Login :");
        txtLogin = new JTextField(10);

        JLabel lblMdp = new JLabel("Mot de passe :");
        txtMdp = new JTextField(10);

        JLabel lblAdresse = new JLabel("Adresse :");
        txtAdresse = new JTextField(10);

        JLabel lblCodePostal = new JLabel("Code Postal :");
        txtCodePostal = new JTextField(10);

        JLabel lblVille = new JLabel("Ville :");
        txtVille = new JTextField(10);

        JLabel lblDateEntree = new JLabel("Date d'entrée (yyyy-MM-dd) :");
        txtDateEntree = new JTextField(10);

        JLabel lblCodeUnite = new JLabel("Code Unité :");
        txtCodeUnit = new JTextField(10);

        JLabel lblNomUnite = new JLabel("Nom Unité :");
        txtNomUnit = new JTextField(10);

        // Ajouter des boutons
        btnAjouter = new JButton("Ajouter");
        btnAnnuler = new JButton("Annuler");
        btnAjouter.addActionListener(this);
        btnAnnuler.addActionListener(this);

        // Utilisation d'un GridLayout pour les champs et labels
        setLayout(new BorderLayout(5, 5));  // Utilisation de BorderLayout pour structurer les composants

        // Panel pour les champs de texte et labels
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(13, 2, 5, 5));  // 13 lignes et 2 colonnes (labels et champs)
        fieldsPanel.add(lblMatricule);
        fieldsPanel.add(txtMatricule);
        fieldsPanel.add(lblNom);
        fieldsPanel.add(txtNom);
        fieldsPanel.add(lblPrenom);
        fieldsPanel.add(txtPrenom);
        fieldsPanel.add(lblLogin);
        fieldsPanel.add(txtLogin);
        fieldsPanel.add(lblMdp);
        fieldsPanel.add(txtMdp);
        fieldsPanel.add(lblAdresse);
        fieldsPanel.add(txtAdresse);
        fieldsPanel.add(lblCodePostal);
        fieldsPanel.add(txtCodePostal);
        fieldsPanel.add(lblVille);
        fieldsPanel.add(txtVille);
        fieldsPanel.add(lblDateEntree);
        fieldsPanel.add(txtDateEntree);
        fieldsPanel.add(lblCodeUnite);
        fieldsPanel.add(txtCodeUnit);
        fieldsPanel.add(lblNomUnite);
        fieldsPanel.add(txtNomUnit);

        add(fieldsPanel, BorderLayout.CENTER);  // Ajoute les champs et labels au centre

        // Créer un JPanel pour contenir les boutons et les centrer
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Centrer les boutons
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnAnnuler);
        
        // Ajouter le panneau des boutons au bas de la fenêtre
        add(buttonPanel, BorderLayout.SOUTH);

        // Mise à jour de la fenêtre
        setVisible(true);
    }

    @Override
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
            // Récupérer les valeurs saisies par l'utilisateur
            String matricule = txtMatricule.getText();
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String login = txtLogin.getText();
            String mdp = txtMdp.getText();
            String adresse = txtAdresse.getText();
            String codePostal = txtCodePostal.getText();
            Date dateEntree = null;
            
            // Tentative de conversion de la date d'entrée
            try {
                dateEntree = Date.valueOf(txtDateEntree.getText());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Date invalide, veuillez entrer la date au format yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return; // Si la date est invalide, on arrête le processus
            }
            
            String codeUnit = txtCodeUnit.getText();
            String nomUnit = txtNomUnit.getText();
            
            // Validation des champs obligatoires
            if (matricule.isEmpty() || nom.isEmpty() || prenom.isEmpty() || login.isEmpty() || mdp.isEmpty() || adresse.isEmpty() || 
                codePostal.isEmpty() || dateEntree == null || codeUnit.isEmpty() || nomUnit.isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return; // Si un champ est vide, on arrête le processus
            }

            // Création de l'objet Visiteur
            Visiteur visiteur = new Visiteur(matricule, nom, prenom, login, mdp, adresse, codePostal, dateEntree, codeUnit, nomUnit);

            // Appel de la méthode pour ajouter le visiteur dans la base de données
            VisiteurDao.creer(visiteur);
            
            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(this, "Visiteur ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            
            // Optionnel : Réinitialiser les champs après ajout
            txtMatricule.setText("");
            txtNom.setText("");
            txtPrenom.setText("");
            txtLogin.setText("");
            txtMdp.setText("");
            txtAdresse.setText("");
            txtCodePostal.setText("");
            txtDateEntree.setText("");
            txtCodeUnit.setText("");
            txtNomUnit.setText("");
        } else if (e.getSource() == btnAnnuler) {
            // Fermer la fenêtre si le bouton Annuler est cliqué
            setVisible(false);
        }
    }

        
    }

