package gsb.vue;

import gsb.modele.Medecin;

/**
 * Fenêtre interne permettant de consulter et afficher les informations détaillées d'un médecin.
 * Cette classe hérite de `JIFMedecin` et est utilisée pour afficher les informations complètes d'un médecin dans
 * des champs de texte pré-remplis avec ses données.
 * 
 * @author Trotiflex
 * @version 1.0
 * @since 17 novembre 2024
 */
public class JIFMedecinFiche extends JIFMedecin {

    /**
     * Identifiant de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de la fenêtre de fiche médecin.
     * Ce constructeur prend un objet `Medecin` en paramètre et appelle la méthode `remplirText()` de la classe parente
     * (`JIFMedecin`) pour remplir les champs de texte avec les informations du médecin passé en paramètre.
     * 
     * @param unMedecin Le médecin dont les informations seront affichées dans la fenêtre.
     */
    public JIFMedecinFiche(Medecin unMedecin) {
        super(); // Appel au constructeur de la classe parente (JIFMedecin)
        this.remplirText(unMedecin); // Remplir les champs de texte avec les données du médecin
    }
}
