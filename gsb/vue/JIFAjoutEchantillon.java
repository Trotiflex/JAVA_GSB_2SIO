package gsb.vue;

import gsb.modele.dao.StockerDao;
import gsb.modele.dao.ConnexionMySql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * Fenêtre interne permettant d'ajouter la quantité d'échantillons pour un visiteur donné.
 * Cette fenêtre permet de saisir le code du visiteur, le dépôt légal du médicament et la quantité d'échantillons à ajouter.
 * Un bouton permet d'ajouter la quantité saisie en base de données.
 * 
 * @author Isabelle
 * @version 1.0
 * @since 2024-11-21
 */
public class JIFAjoutEchantillon extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtCodeVisiteur, txtDepotLegal, txtQuantite;
    private JButton btnAjouterQuantite;

    /**
     * Constructeur de la fenêtre permettant d'ajouter des échantillons.
     * Initialise la fenêtre avec les champs de saisie et le bouton d'ajout.
     */
    public JIFAjoutEchantillon() {
        super("Ajout échantillons pour un visiteur", true, true, true, true);
        setSize(300, 200);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champs de saisie
        txtCodeVisiteur = new JTextField(15);
        txtDepotLegal = new JTextField(15);
        txtQuantite = new JTextField(15);

        // Bouton pour ajouter la quantité
        btnAjouterQuantite = new JButton("Ajouter quantité");

        // Ajout des labels et des champs au panneau
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Code visiteur"), gbc);
        gbc.gridx = 1;
        add(txtCodeVisiteur, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Dépôt légal"), gbc);
        gbc.gridx = 1;
        add(txtDepotLegal, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Quantité"), gbc);
        gbc.gridx = 1;
        add(txtQuantite, gbc);

        // Ajout du bouton Ajouter en bas au centre
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAjouterQuantite, gbc);

        // Action du bouton Ajouter quantité
        btnAjouterQuantite.addActionListener(new ActionListener() {
            /**
             * Méthode exécutée lorsqu'on clique sur le bouton "Ajouter quantité".
             * Cette méthode récupère les valeurs saisies, vérifie leur validité,
             * et ajoute la quantité d'échantillons dans la base de données.
             * 
             * @param e L'événement de clic sur le bouton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeVisiteur = txtCodeVisiteur.getText().trim();
                String depotLegal = txtDepotLegal.getText().trim();
                String quantiteStr = txtQuantite.getText().trim();

                // Vérification que tous les champs sont remplis
                if (codeVisiteur.isEmpty() || depotLegal.isEmpty() || quantiteStr.isEmpty()) {
                    JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Conversion de la quantité en entier
                    int quantite = Integer.parseInt(quantiteStr);

                    // Connexion à la base de données
                    try (Connection connection = ConnexionMySql.getConnection()) {
                        boolean success = StockerDao.ajouterEchantillon(connection, codeVisiteur, depotLegal, quantite);

                        // Affichage d'un message en fonction du succès ou de l'échec
                        if (success) {
                            JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "Quantité ajoutée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            // Réinitialisation des champs
                            txtCodeVisiteur.setText("");
                            txtDepotLegal.setText("");
                            txtQuantite.setText("");
                        } else {
                            JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "Erreur lors de l'ajout de la quantité.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "La quantité doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "Erreur de connexion à la base de données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
