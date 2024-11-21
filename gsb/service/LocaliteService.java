package gsb.service;

import gsb.modele.Localite; // Import du modèle Localite
import gsb.modele.dao.LocaliteDao;

public class LocaliteService {

    /**
     * Recherche une localité par son code postal.
     * @param code Le code postal de la localité à rechercher.
     * @return L'objet Localite correspondant au code postal, ou null si non trouvé.
     */
    public static Localite rechercherLocalite(String code) {
        Localite uneLocalite = null;
        
        // Vérifier que le code n'est pas nul ou vide
        if (code == null || code.trim().isEmpty()) {
            System.out.println("Erreur : le code de la localité est obligatoire.");
            return null;
        }
        
        try {
            // Appel à la méthode du DAO pour rechercher la localité
            uneLocalite = LocaliteDao.rechercher(code);
            
            // Si aucune localité n'est trouvée
            if (uneLocalite == null) {
                System.out.println("Aucune localité trouvée pour le code postal : " + code);
            }
        } catch (Exception e) {
            // Afficher une erreur si une exception est lancée lors de la recherche
            System.out.println("Erreur lors de la recherche de la localité : " + e.getMessage());
            e.printStackTrace();
        }
        
        return uneLocalite;
    }
}
