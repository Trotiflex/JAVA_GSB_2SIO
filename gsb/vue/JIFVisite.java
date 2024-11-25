package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gsb.modele.Visite;

/**
 * La classe <code>JIFVisite</code> représente une fenêtre interne (JInternalFrame) permettant d'afficher
 * et de gérer les informations d'une visite. Elle inclut des zones de texte pour afficher les détails
 * de la visite, ainsi que des boutons pour les actions futures (bien que les boutons ne soient pas implémentés
 * dans cette version).
 * 
 * Cette classe est principalement utilisée pour afficher des informations comme le code du médecin, la référence
 * de la visite, la date de la visite, le commentaire et le matricule du visiteur.
 * 
 * @author Louise
 */
public class JIFVisite extends JInternalFrame {
    
    private static final long serialVersionUID = 1L;
    
    // Déclaration des panneaux et composants
    protected JPanel p;  
    protected JPanel pTexte;
    protected JPanel pBoutons;
    
    protected JLabel JLcode;
    protected JLabel JLreference;
    protected JLabel JLdateVisite;
    protected JLabel JLcommentaire;
    protected JLabel JLmatricule;
    
    protected JTextField JTcode;
    protected JTextField JTreference;
    protected JTextField JTdateVisite;
    protected JTextField JTcommentaire;
    protected JTextField JTmatricule;
    
    /**
     * Constructeur de la classe <code>JIFVisite</code>.
     * Ce constructeur initialise l'interface utilisateur de la fenêtre, en créant les composants nécessaires
     * pour afficher les informations de la visite et en disposant ces composants dans des panneaux.
     * 
     * Il prépare également les champs de texte pour les informations liées à une visite.
     */
    public JIFVisite() {
        p = new JPanel();  // Panneau principal de la fenêtre
        pBoutons = new JPanel();    // Panneau supportant les boutons (à ajouter si nécessaire)
        pTexte = new JPanel(new GridLayout(5, 2));  // Ajustement du GridLayout pour 5 lignes (ajustement des zones de texte)
        
        // Initialisation des étiquettes (JLabel) pour chaque champ
        JLcode = new JLabel("Code médecin");
        JLreference = new JLabel("Référence");
        JLdateVisite = new JLabel("Date visite");
        JLcommentaire = new JLabel("Commentaire");
        JLmatricule = new JLabel("Matricule visiteur");
        
        // Initialisation des champs de texte (JTextField) pour chaque champ
        JTcode = new JTextField(20);
        JTcode.setMaximumSize(JTcode.getPreferredSize());
        JTreference = new JTextField();
        JTdateVisite = new JTextField();
        JTcommentaire = new JTextField();    
        JTmatricule = new JTextField();
        
        // Ajout des éléments dans le panneau texte
        pTexte.add(JLcode);
        pTexte.add(JTcode);
        pTexte.add(JLreference);
        pTexte.add(JTreference);
        pTexte.add(JLdateVisite);
        pTexte.add(JTdateVisite);
        pTexte.add(JLcommentaire);
        pTexte.add(JTcommentaire);
        pTexte.add(JLmatricule);
        pTexte.add(JTmatricule);
        
        // Mise en forme de la fenêtre
        p.add(pTexte);
        p.add(pBoutons);  // Ajout des boutons (optionnel)
        Container contentPane = getContentPane();
        contentPane.add(p);
    }
    
    /**
     * Remplit les zones de texte avec les informations d'une visite donnée.
     * Ce procédé prend un objet de type <code>Visite</code> en paramètre et met à jour les champs de texte
     * de la fenêtre en fonction des informations contenues dans l'objet.
     * 
     * @param uneVisite L'objet <code>Visite</code> contenant les informations à afficher.
     *                  Il peut contenir des informations comme le code médecin, la référence,
     *                  la date de la visite, le commentaire et le matricule du visiteur.
     */
    public void remplirText(Visite uneVisite) {  
        // Remplir les zones de texte avec les valeurs de l'objet Visite
        JTcode.setText(uneVisite.getCodeMed());
        JTreference.setText(uneVisite.getReference());
        
        // Formatage de la date avant d'afficher dans le champ texte
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(uneVisite.getDateVisite());  // Conversion de la date en chaîne
        JTdateVisite.setText(dateStr);
        
        JTcommentaire.setText(uneVisite.getCommentaire());
        JTmatricule.setText(uneVisite.getMatricule());
    }
    
    /**
     * Vide toutes les zones de texte de la fenêtre.
     * Cette méthode est utilisée pour réinitialiser le formulaire en supprimant les informations actuelles
     * des champs de texte.
     */
    public void viderText() {  
        // Vider les zones de texte
        JTcode.setText("");
        JTreference.setText("");
        JTdateVisite.setText("");
        JTcommentaire.setText("");
        JTmatricule.setText("");
    }
}
