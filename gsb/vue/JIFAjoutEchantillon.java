package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JIFAjoutEchantillon extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtCodeVisiteur, txtDepotLegal, txtQuantite;
    private JButton btnAjouterQuantite;

    public JIFAjoutEchantillon() {
        super("Ajout échantillons pour un visiteur", true, true, true, true);
        setSize(300, 200);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Utilisation de GridBagLayout pour aligner les composants
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

        // Ajout des labels et des champs au panneau avec GridBagConstraints
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
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeVisiteur = txtCodeVisiteur.getText().trim();
                String depotLegal = txtDepotLegal.getText().trim();
                String quantiteStr = txtQuantite.getText().trim();

                // Validation des champs
                if (codeVisiteur.isEmpty() || depotLegal.isEmpty() || quantiteStr.isEmpty()) {
                    JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int quantite = Integer.parseInt(quantiteStr);
                    // TODO: Ajoutez ici le code pour gérer l'ajout de la quantité
                    JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "Quantité ajoutée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Réinitialiser les champs après ajout
                    txtCodeVisiteur.setText("");
                    txtDepotLegal.setText("");
                    txtQuantite.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JIFAjoutEchantillon.this, "La quantité doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

