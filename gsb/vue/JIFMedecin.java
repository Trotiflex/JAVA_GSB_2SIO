
package gsb.vue;

import gsb.modele.Medecin;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Fenêtre interne permettant d'afficher et de modifier les informations relatives à un médecin.
 * Cette classe affiche un formulaire avec les champs associés aux informations d'un médecin, tels que le code,
 * le nom, le prénom, l'adresse, le code postal, la ville, le téléphone, le potentiel et la spécialité.
 * 
 * Elle inclut deux méthodes pour remplir ou vider les champs de texte à partir d'un objet de type `Medecin`.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 17 novembre 2024
 */
public class JIFMedecin extends JInternalFrame {

    /**
     * Identifiant de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    protected JPanel p;  
    protected JPanel pTexte;
    protected JPanel pBoutons;

    protected JLabel JLcode;
    protected JLabel JLnom;
    protected JLabel JLprenom;
    protected JLabel JLadresse;
    protected JLabel JLcp;
    protected JLabel JLville;
    protected JLabel JLtelephone;
    protected JLabel JLpotentiel;
    protected JLabel JLspecialite;
    
    protected JTextField JTcode;
    protected JTextField JTnom;
    protected JTextField JTprenom;
    protected JTextField JTadresse;
    protected JTextField JTcp;
    protected JTextField JTville;
    protected JTextField JTtelephone;
    protected JTextField JTpotentiel;
    protected JTextField JTspecialite;

    /**
     * Constructeur de la fenêtre. Ce constructeur initialise les composants graphiques
     * pour afficher les informations d'un médecin dans des champs de texte.
     */
    public JIFMedecin() {
        p = new JPanel();  // panneau principal de la fenêtre
        pBoutons = new JPanel();    // panneau pour les boutons
        pTexte = new JPanel(new GridLayout(9, 2));  // Panneau de texte avec une grille de 9 lignes et 2 colonnes
        
        // Initialisation des labels et des champs de texte
        JLcode = new JLabel("Code");
        JLnom = new JLabel("Nom");
        JLprenom = new JLabel("Prénom");
        JLadresse = new JLabel("Adresse rue");
        JLcp = new JLabel("Code postal");
        JLville = new JLabel("Ville");
        JLtelephone = new JLabel("Téléphone");
        JLpotentiel = new JLabel("Potentiel");
        JLspecialite = new JLabel("Spécialité");

        JTcode = new JTextField(20);
        JTcode.setMaximumSize(JTcode.getPreferredSize());
        JTnom = new JTextField();
        JTprenom = new JTextField();
        JTadresse = new JTextField();    
        JTcp = new JTextField();
        JTville = new JTextField();
        JTtelephone = new JTextField();
        JTpotentiel = new JTextField();
        JTspecialite = new JTextField();
        
        // Ajout des labels et des champs de texte dans le panneau
        pTexte.add(JLcode);
        pTexte.add(JTcode);
        pTexte.add(JLnom);
        pTexte.add(JTnom);
        pTexte.add(JLprenom);
        pTexte.add(JTprenom);
        pTexte.add(JLadresse);
        pTexte.add(JTadresse);
        pTexte.add(JLcp);
        pTexte.add(JTcp);
        pTexte.add(JLville);
        pTexte.add(JTville);
        pTexte.add(JLtelephone);
        pTexte.add(JTtelephone);
        pTexte.add(JLpotentiel);
        pTexte.add(JTpotentiel);
        pTexte.add(JLspecialite);
        pTexte.add(JTspecialite);
        
        // Mise en forme de la fenêtre
        p.add(pTexte);
        p.add(pBoutons);
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    /**
     * Remplie les champs de texte avec les informations d'un médecin.
     * 
     * @param unMedecin L'objet `Medecin` dont les informations sont utilisées pour remplir les champs de texte.
     */
    public void remplirText(Medecin unMedecin) { 
        JTcode.setText(unMedecin.getCodeMed());
        JTnom.setText(unMedecin.getNom());
        JTprenom.setText(unMedecin.getPrenom());
        JTadresse.setText(unMedecin.getAdresse());    
        JTcp.setText(unMedecin.getLaLocalite().getCodePostal());
        JTville.setText(unMedecin.getLaLocalite().getVille());
        JTtelephone.setText(unMedecin.getTelephone());
        JTpotentiel.setText(unMedecin.getPotentiel());
        JTspecialite.setText(unMedecin.getSpecialite());
    }

    /**
     * Vide tous les champs de texte.
     */
    public void viderText() { 
        JTcode.setText("");
        JTnom.setText("");
        JTprenom.setText("");
        JTadresse.setText("");    
        JTcp.setText("");
        JTville.setText("");
        JTtelephone.setText("");
        JTpotentiel.setText("");
        JTspecialite.setText("");
    }
}
