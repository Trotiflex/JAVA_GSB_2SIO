package gsb.vue;

import gsb.modele.Visite;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.VisiteDao;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * La classe <code>JIFRecapVisite</code> représente une fenêtre interne (JInternalFrame) affichant le
 * récapitulatif des détails d'une visite spécifique. Cette fenêtre affiche les informations de la visite,
 * telles que la référence, la date de la visite, le matricule du visiteur, le code du médecin, et un commentaire
 * associé. Elle affiche également un tableau des médicaments associés à cette visite.
 * 
 * Cette classe utilise des composants Swing pour créer une interface utilisateur permettant d'afficher les
 * informations d'une visite.
 * 
 * @author Louise 
 */
public class JIFRecapVisite extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtReference, txtDateVisite, txtMatricule, txtCodeMed, txtCommentaire;
    private JTable tableMedicaments;

    /**
     * Constructeur de la classe <code>JIFRecapVisite</code>.
     * Ce constructeur initialise la fenêtre interne avec des champs pour afficher les informations de la visite
     * et un tableau des médicaments associés. Il charge également les données depuis la base de données
     * pour remplir ces champs.
     *
     * @param reference La référence de la visite à afficher.
     *                  Ce paramètre est utilisé pour récupérer les détails de la visite depuis la base de données.
     */
    public JIFRecapVisite(String reference) {
        super("Récapitulatif d'une visite", true, true, true, true);
        setSize(400, 300);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Champs texte
        mainPanel.add(new JLabel("Référence:"));
        txtReference = new JTextField();
        txtReference.setEditable(false);
        mainPanel.add(txtReference);

        mainPanel.add(new JLabel("Date visite:"));
        txtDateVisite = new JTextField();
        txtDateVisite.setEditable(false);
        mainPanel.add(txtDateVisite);

        mainPanel.add(new JLabel("Matricule visiteur:"));
        txtMatricule = new JTextField();
        txtMatricule.setEditable(false);
        mainPanel.add(txtMatricule);

        mainPanel.add(new JLabel("Code médecin:"));
        txtCodeMed = new JTextField();
        txtCodeMed.setEditable(false);
        mainPanel.add(txtCodeMed);

        mainPanel.add(new JLabel("Commentaire:"));
        txtCommentaire = new JTextField();
        txtCommentaire.setEditable(false);
        mainPanel.add(txtCommentaire);

        // Tableau pour les médicaments
        String[] columnNames = {"Dépôt légal", "Quantité offerte"};
        Object[][] data = {};
        tableMedicaments = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tableMedicaments);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Charger les données depuis la base de données
        chargerDonnees(reference);
    }

    /**
     * Charge les détails d'une visite depuis la base de données et les affiche dans les champs de texte de la fenêtre.
     * Si aucune visite n'est trouvée pour la référence spécifiée, affiche un message d'erreur.
     * 
     * @param reference La référence de la visite à charger.
     *                  Ce paramètre permet d'identifier la visite dans la base de données.
     * @return void
     */
    private void chargerDonnees(String reference) {
        Connection connection = ConnexionMySql.getConnection();
        Visite visite = VisiteDao.getVisiteByReference(reference, connection);
        if (visite != null) {
            txtReference.setText(visite.getReference());
            txtDateVisite.setText(visite.getDateVisite().toString());
            txtMatricule.setText(visite.getMatricule());
            txtCodeMed.setText(visite.getCodeMed());
            txtCommentaire.setText(visite.getCommentaire());
        } else {
            JOptionPane.showMessageDialog(this, "Détails de la visite introuvables.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Récupérer les médicaments associés à la visite
        ArrayList<Object[]> medicaments = MedicamentDao.getMedicamentsByVisite(reference);
        Object[][] data = medicaments.toArray(new Object[0][]);
        tableMedicaments.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"Dépôt légal", "Quantité offerte"}));
    }
}
