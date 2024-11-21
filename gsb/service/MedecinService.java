package gsb.service;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;

/**
 * Service pour la gestion des médecins.
 * Cette classe contient des méthodes pour rechercher des médecins par leur code.
 * 
 * @author Isabelle
 * @version 17 nov. 2021
 */
public class MedecinService {
    
    /**
     * Recherche un médecin à partir de son code.
     * 
     * @param unCodeMedecin Le code du médecin à rechercher.
     * @return Le médecin trouvé ou null si non trouvé.
     */
    public static Medecin rechercherMedecin(String unCodeMedecin) {
        Medecin unMedecin = null;
        
        // Vérification de la validité du code médecin
        if (unCodeMedecin == null || unCodeMedecin.trim().isEmpty()) {
            System.out.println("Erreur : le code médecin est obligatoire.");
            return null;
        }
        
        try {
            // Recherche du médecin via le DAO
            unMedecin = MedecinDao.rechercher(unCodeMedecin);
            
            // Vérifier si aucun médecin n'a été trouvé
            if (unMedecin == null) {
                System.out.println("Aucun médecin trouvé avec le code : " + unCodeMedecin);
            }
        } catch (Exception e) {
            // Affichage d'un message d'erreur détaillé
            System.out.println("Erreur lors de la recherche du médecin : " + e.getMessage());
            e.printStackTrace();
        }
        
        return unMedecin;
    }
}
