package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 2591453837113855452L;

    protected JDesktopPane desktopPane;
    protected JMenuBar mbar;
    protected JMenu mMedecins;
    protected JMenu mMedicaments;
    protected JMenu mVisiteur;
    protected JMenu mVisites;

    public MenuPrincipal() {
        // Configuration de la fenêtre principale
        setTitle("GSB");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        // Création du JDesktopPane pour les fenêtres internes
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.GRAY);
        add(desktopPane, BorderLayout.CENTER);

        // Configuration de la barre de menu
        mbar = new JMenuBar();

        // Menu "Médecins"
        mMedecins = new JMenu("Médecins");
        JMenuItem mC1 = new JMenuItem("Consultation Médecin");
        mC1.addActionListener(this);
        mC1.setActionCommand("Consultation Médecin");
        JMenuItem mC2 = new JMenuItem("Liste Médecins");
        mC2.addActionListener(this);
        mC2.setActionCommand("Liste Médecins");
        mMedecins.add(mC1);
        mMedecins.add(mC2);

        // Menu "Visiteurs"
        mVisiteur = new JMenu("Visiteurs");
        JMenuItem mC4 = new JMenuItem("Liste Visiteur");
        mC4.addActionListener(this);
        mC4.setActionCommand("Liste Visiteur");
        JMenuItem mC5 = new JMenuItem("Ajout Visiteur");
        mC5.addActionListener(this);
        mC5.setActionCommand("Ajout Visiteur");
        JMenuItem mC6 = new JMenuItem("Stock Visiteur");
        mC6.addActionListener(this);
        mC6.setActionCommand("Afficher Stock Visiteur");
        JMenuItem mC7 = new JMenuItem("Ajout Echantillon");
        mC7.addActionListener(this);
        mC7.setActionCommand("Ajout Echantillon");
        mVisiteur.add(mC4);
        mVisiteur.add(mC5);
        mVisiteur.add(mC6);
        mVisiteur.add(mC7);

        // Menu "Médicaments"
        mMedicaments = new JMenu("Médicaments");
        JMenuItem mE1 = new JMenuItem("Liste Médicaments");
        mE1.addActionListener(this);
        mE1.setActionCommand("Liste Médicaments");
        JMenuItem mE3 = new JMenuItem("Médicaments par famille");
        mE3.addActionListener(this);
        mE3.setActionCommand("Médicament par famille");
        mMedicaments.add(mE1);
        mMedicaments.add(mE3);

        // Menu "Visites"
        mVisites = new JMenu("Visites");
        JMenuItem mA1 = new JMenuItem("Consultation Visite");
        mA1.addActionListener(this);
        mA1.setActionCommand("Consultation Visite");
        JMenuItem mA2 = new JMenuItem("Ajout Visite");
        mA2.addActionListener(this);
        mA2.setActionCommand("Ajout Visite");
        mVisites.add(mA1);
        mVisites.add(mA2);

        // Ajout des menus à la barre de menu
        mbar.add(mMedecins);
        mbar.add(mVisiteur);
        mbar.add(mMedicaments);
        mbar.add(mVisites);
        setJMenuBar(mbar);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Vérifie si l'élément cliqué est un JMenuItem
        if (evt.getSource() instanceof JMenuItem) {
            String choixOption = evt.getActionCommand();

            switch (choixOption) {
                case "Consultation Médecin":
                    ouvrirFenetre(new JIFMedecinCons());
                    break;
                case "Liste Médecins":
                    ouvrirFenetre(new JIFMedecinListeCol(this));
                    break;
                case "Liste Visiteur":
                    ouvrirFenetre(new JIFListeVisiteur(this));
                    break;
                case "Ajout Visiteur":
                    ouvrirFenetre(new JIFAjoutVisiteur());
                    break;
                case "Afficher Stock Visiteur":
                    ouvrirFenetre(new JIFStocker());
                    break;
                case "Médicament par famille":
                    ouvrirFenetre(new JIFMedicamentParFamille());
                    break;
                case "Liste Médicaments":
                    ouvrirFenetre(new JIFListeMedicament());
                    break;
                case "Ajout Echantillon":
                    ouvrirFenetre(new JIFAjoutEchantillon());
                    break;
                case "Consultation Visite":
                    JOptionPane.showMessageDialog(this, "Consultation des visites");
                    break;
                case "Ajout Visite":
                    JOptionPane.showMessageDialog(this, "Ajout d'une visite");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Option non reconnue : " + choixOption);
            }
        }
    }

    // Méthode pour ouvrir une fenêtre interne
    public void ouvrirFenetre(JInternalFrame uneFenetre) {
        // Vérifie si une fenêtre du même type est déjà ouverte
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame.getClass().equals(uneFenetre.getClass())) {
                try {
                    frame.setSelected(true); // Sélectionne la fenêtre déjà ouverte
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }

        // Si aucune fenêtre du même type n'est ouverte, alors l'ajouter
        desktopPane.add(uneFenetre);
        uneFenetre.setVisible(true);
        uneFenetre.setResizable(true);
        uneFenetre.setMaximizable(true);
        uneFenetre.setClosable(true);
        uneFenetre.pack(); // Ajuste la taille de la fenêtre interne à son contenu
        uneFenetre.setLocation((desktopPane.getWidth() - uneFenetre.getWidth()) / 3,
                               (desktopPane.getHeight() - uneFenetre.getHeight()) / 3);
        try {
            uneFenetre.setSelected(true); // Sélectionne la nouvelle fenêtre
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}