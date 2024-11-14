package gsb.service;

import gsb.modele.Localite; // Import the Localite model
import gsb.modele.dao.LocaliteDao;

public class LocaliteService {

    // Méthode pour rechercher une localité par son code
    public static Localite rechercherLocalite(String code) {
        Localite uneLocalite = null;
        try {
            if (code == null) {
                throw new Exception("Donnee obligatoire : code de la localité"); // Required data: code
            }
            uneLocalite = LocaliteDao.rechercher(code); // Call to LocaliteDao to search
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Print the error message
        }
        return uneLocalite; // Return the found localité or null
    }
}

