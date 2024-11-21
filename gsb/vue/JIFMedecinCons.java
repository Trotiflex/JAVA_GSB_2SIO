package gsb.vue;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * Fenêtre interne permettant de consulter les informations des médecins à travers des boutons de navigation.
 * Cette classe hérite de `JIFMedecin` et permet de naviguer entre les données de plusieurs médecins à l'aide de boutons
 * "Premier", "Suivant", "Précédent" et "Dernier".
 * Elle utilise un tableau de médecins récupéré depuis la base de données et permet à l'utilisateur de consulter les informations
 * des médecins un par un en fonction de leur position dans la liste.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 17 novembre 2024
 */
public class JIFMedecinCons extends JIFMedecin implements ActionListener {

    /**
     * Identifiant de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    // Boutons de navigation pour consulter les médecins
    private JButton premier;
    private JButton suivant;
    private JButton precedent;
    private JButton dernier;

    // Liste des médecins récupérés depuis la base de données
    private ArrayList<Medecin> lesMedecins;

    // Indice du médecin actuellement affiché dans la liste
    private int indiceEnCours;

    /**
     * Constructeur de la fenêtre de consultation des médecins.
     * Ce constructeur initialise les boutons de navigation et charge la liste des médecins depuis la base de données.
     * Il permet aussi de remplir les champs de texte avec les informations du premier médecin de la liste si celle-ci n'est pas vide.
     */
    public JIFMedecinCons() {
        super(); // Appel au constructeur de la classe parente (JIFMedecin)
        
        // Initialisation des boutons de navigation
        premier = new JButton("Premier");
        pBoutons.add(premier);
        suivant = new JButton("Suivant");
        pBoutons.add(suivant);
        precedent = new JButton("Précédent");
        pBoutons.add(precedent);
        dernier = new JButton("Dernier");
        pBoutons.add(dernier);

        // Ajout des écouteurs d'événements pour chaque bouton
        premier.addActionListener(this);
        suivant.addActionListener(this);
        precedent.addActionListener(this);
        dernier.addActionListener(this);

        // Paramétrage de la fenêtre
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Consultation données Médecin");

        // Récupération des données des médecins depuis la base de données
        lesMedecins = MedecinDao.retournerCollectionDesMedecins();
        indiceEnCours = 0;

        // Si la collection de médecins n'est pas vide, afficher les données du premier médecin
        if (lesMedecins.size() != 0) {
            Medecin leMedecin = lesMedecins.get(0);
            remplirText(leMedecin);
        }

        // Ajout d'un écouteur d'événement pour la fermeture de la fenêtre
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                // Code à exécuter lors de la fermeture de la fenêtre interne
            }
        });
    }

    /**
     * Gestion des événements de clic sur les boutons de navigation.
     * En fonction du bouton cliqué ("Premier", "Suivant", "Précédent" ou "Dernier"),
     * cette méthode met à jour l'affichage avec les informations du médecin correspondant.
     * 
     * @param evt L'événement de clic sur un bouton de navigation.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        
        if (source == premier) {
            // Affichage du premier médecin de la liste
            indiceEnCours = 0;
            Medecin leMedecin = lesMedecins.get(indiceEnCours);
            remplirText(leMedecin);
        } else if (source == dernier) {
            // Affichage du dernier médecin de la liste
            indiceEnCours = lesMedecins.size() - 1;
            Medecin leMedecin = lesMedecins.get(indiceEnCours);
            remplirText(leMedecin);
        } else if (source == precedent) {
            // Affichage du médecin précédent, si l'indice est supérieur à 0
            if (indiceEnCours > 0) {
                indiceEnCours = indiceEnCours - 1;
                Medecin leMedecin = lesMedecins.get(indiceEnCours);
                remplirText(leMedecin);
            }
        } else if (source == suivant) {
            // Affichage du médecin suivant, si l'indice est inférieur à la taille de la liste - 1
            if (indiceEnCours < (lesMedecins.size() - 1)) {
                indiceEnCours = indiceEnCours + 1;
                Medecin leMedecin = lesMedecins.get(indiceEnCours);
                remplirText(leMedecin);
            }
        }
    }
}
