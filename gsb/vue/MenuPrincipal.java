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
        setTitle("GSB");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.GRAY);
        add(desktopPane, BorderLayout.CENTER);

        mbar = new JMenuBar();

        // Menu Médecins
        mMedecins = new JMenu("Médecins");
        JMenuItem mC1 = new JMenuItem("Consultation Médecin");
        mC1.addActionListener(this);
        mC1.setActionCommand("Consultation Médecin");
        JMenuItem mC2 = new JMenuItem("Liste Médecins");
        mC2.addActionListener(this);
        mC2.setActionCommand("Liste Médecins");
        mMedecins.add(mC1);
        mMedecins.add(mC2);

        // Menu Visiteurs
        mVisiteur = new JMenu("Visiteurs");
        JMenuItem mC4 = new JMenuItem("Liste Visiteur");
        mC4.addActionListener(this);
        mC4.setActionCommand("Liste Visiteur");
        JMenuItem mC5 = new JMenuItem("Ajout Visiteur");
        mC5.addActionListener(this);
        mC5.setActionCommand("Ajout Visiteur");
        JMenuItem mC6 = new JMenuItem("Stock Visiteur");
        mC6.addActionListener(this);
        mC6.setActionCommand("Stock Visiteur");
        JMenuItem mC7 = new JMenuItem("Ajout Echantillon");
        mC7.addActionListener(this);
        mC7.setActionCommand("Ajout Echantillon");
        mVisiteur.add(mC4);
        mVisiteur.add(mC5);
        mVisiteur.add(mC6);
        mVisiteur.add(mC7);

        // Menu Médicaments
        mMedicaments = new JMenu("Médicaments");
        JMenuItem mE1 = new JMenuItem("Liste Médicaments");
        mE1.addActionListener(this);
        mE1.setActionCommand("Liste Médicaments");
        JMenuItem mE2 = new JMenuItem("Ajout Médicament");
        mE2.addActionListener(this);
        mE2.setActionCommand("Ajout Médicament");
        JMenuItem mE3 = new JMenuItem("Médicament par famille");
        mE3.addActionListener(this);
        mE3.setActionCommand("Médicament par famille");
        mMedicaments.add(mE1);
        mMedicaments.add(mE2);
        mMedicaments.add(mE3);

        // Menu Visites
        mVisites = new JMenu("Visites");
        JMenuItem mA1 = new JMenuItem("Liste des Visites");
        mA1.addActionListener(this);
        mA1.setActionCommand("Liste Visites");
        JMenuItem mA2 = new JMenuItem("Ajout Visite");
        mA2.addActionListener(this);
        mA2.setActionCommand("Ajout Visite");
        mVisites.add(mA1);
        mVisites.add(mA2);
        

        mbar.add(mMedecins);
        mbar.add(mVisiteur);
        mbar.add(mMedicaments);
        mbar.add(mVisites);
        setJMenuBar(mbar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
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
                case "Stock Visiteur":
                    ouvrirFenetre(new JIFStocker(this)); // Nouvelle fenêtre pour le stock des visiteurs
                    break;
                case "Ajout Echantillon":
                    ouvrirFenetre(new JIFAjoutEchantillon()); // Nouvelle fenêtre pour l'ajout d'échantillons
                    break;
                case "Liste Médicaments":
                    ouvrirFenetre(new JIFListeMedicament());
                    break;
                case "Ajout Médicament":
                    ouvrirFenetre(new JIFAjoutMedicament()); // Nouvelle fenêtre pour l'ajout de médicaments
                    break;
                case "Médicament par famille":
                    ouvrirFenetre(new JIFMedicamentParFamille());
                    break;
                case "Liste Visites":
                    ouvrirFenetre(new JIFListeVisites(this));
                    break;
                case "Ajout Visite":
                    ouvrirFenetre(new JIFAjoutVisite());
                    break;
               
                default:
                    JOptionPane.showMessageDialog(this, "Option non reconnue : " + choixOption);
            }
        }
    }

    public void ouvrirFenetre(JInternalFrame uneFenetre) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame.getClass().equals(uneFenetre.getClass())) {
                try {
                    frame.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }

        desktopPane.add(uneFenetre);
        uneFenetre.setVisible(true);
        uneFenetre.setResizable(true);
        uneFenetre.setMaximizable(true);
        uneFenetre.setClosable(true);
        uneFenetre.pack();
        uneFenetre.setLocation((desktopPane.getWidth() - uneFenetre.getWidth()) / 3,
                               (desktopPane.getHeight() - uneFenetre.getHeight()) / 3);
        try {
            uneFenetre.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}
