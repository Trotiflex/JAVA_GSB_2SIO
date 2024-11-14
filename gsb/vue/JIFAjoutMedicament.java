package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JIFAjoutMedicament extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JTextField tfDepotLegal, tfNomCommercial, tfComposition, tfEffets, tfContreIndications, tfCodeFamille, tfLibelleFamille;
    private JButton btnAjouter;

    public JIFAjoutMedicament() {
        setTitle("Ajout d'un Médicament");
        setSize(400, 350);
        
        // Positionner la fenêtre au centre de l'écran
        setLocation(100,150);  // Centre la fenêtre dans l'écran

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel pour les champs
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.add(new JLabel("Dépôt légal"));
        tfDepotLegal = new JTextField();
        panel.add(tfDepotLegal);

        panel.add(new JLabel("Nom commercial"));
        tfNomCommercial = new JTextField();
        panel.add(tfNomCommercial);

        panel.add(new JLabel("Composition"));
        tfComposition = new JTextField();
        panel.add(tfComposition);

        panel.add(new JLabel("Effets"));
        tfEffets = new JTextField();
        panel.add(tfEffets);

        panel.add(new JLabel("Contre indications"));
        tfContreIndications = new JTextField();
        panel.add(tfContreIndications);

        panel.add(new JLabel("Code Famille"));
        tfCodeFamille = new JTextField();
        panel.add(tfCodeFamille);

        panel.add(new JLabel("Libellé famille"));
        tfLibelleFamille = new JTextField();
        panel.add(tfLibelleFamille);

        add(panel, BorderLayout.CENTER);

        // Bouton "Ajouter"
        btnAjouter = new JButton("AJOUTER");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logique d'ajout du médicament (à implémenter)
                JOptionPane.showMessageDialog(null, "Médicament ajouté !");
            }
        });
        add(btnAjouter, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JIFAjoutMedicament ajout = new JIFAjoutMedicament();
            ajout.setVisible(true);
        });
    }
}
