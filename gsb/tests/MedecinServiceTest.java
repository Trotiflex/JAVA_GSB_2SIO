package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.service.MedecinService;

/**
 * Test de la classe MedecinService.
 */
public class MedecinServiceTest {

    /**
     * Méthode principale pour tester la recherche d'un médecin.
     * @param args
     */
    public static void main(String[] args) {
        // Recherche du médecin par son code
        Medecin unMedecin = MedecinService.rechercherMedecin("m002");

        // Vérification que le médecin a bien été trouvé
        if (unMedecin != null) {
            // Affichage des informations du médecin
            System.out.println("Nom : " + unMedecin.getNom());
            System.out.println("Prénom : " + unMedecin.getPrenom());
            System.out.println("Adresse : " + unMedecin.getAdresse());

            // Vérification que la localité est bien renseignée
            if (unMedecin.getLaLocalite() != null) {  // Vérifie si la localité est renseignée (c'est un objet Localite)
                Localite localite = unMedecin.getLaLocalite();  // Récupère l'objet Localite associé au médecin
                System.out.println("Code Postal : " + localite.getCodePostal());  // Accéder au code postal de Localite
                System.out.println("Ville : " + localite.getVille());  // Accéder à la ville de Localite
            } else {
                System.out.println("Localité non renseignée.");
            }


            // Affichage des autres informations du médecin
            System.out.println("Téléphone : " + unMedecin.getTelephone());
            System.out.println("Potentiel : " + unMedecin.getPotentiel());
            System.out.println("Spécialité : " + unMedecin.getSpecialite());
        } else {
            System.out.println("Médecin avec le code 'm002' non trouvé.");
        }
    }
}
