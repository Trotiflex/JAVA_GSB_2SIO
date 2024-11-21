package gsb.service;

import gsb.modele.Visiteur; // Importer le modèle Visiteur
import gsb.modele.dao.VisiteurDao;

/**
 * Service pour la gestion des visiteurs.
 * Cette classe contient des méthodes pour rechercher un visiteur en fonction de son matricule.
 */
public class VisiteurService {

    /**
     * Recherche un visiteur en fonction de son matricule.
     * 
     * @param Matricule Le matricule du visiteur à rechercher.
     * @return Le visiteur trouvé ou null si aucun visiteur n'a été trouvé avec ce matricule.
     * @throws Exception Si le matricule est null ou si une erreur se produit pendant la recherche.
     */
    public static Visiteur rechercherVisiteur(String Matricule) {
        Visiteur unVisiteur = null;
        try {
            if (Matricule == null) {
                throw new Exception("Donnee obligatoire : code"); // Donnée obligatoire : matricule
            }
            unVisiteur = VisiteurDao.rechercher(Matricule); // Appel à VisiteurDao pour rechercher le visiteur
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Affiche le message d'erreur
        }
        return unVisiteur; // Retourne le visiteur trouvé ou null si non trouvé
    }
}
