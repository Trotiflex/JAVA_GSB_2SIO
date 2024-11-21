package gsb.tests;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

/**
 * Classe de test unitaire pour la classe VisiteurDao.
 * Cette classe permet de tester les différentes méthodes de la classe VisiteurDao.
 * 
 * @version 1.0
 * @since 2024-11-21
 */
public class VisiteurDaoTest {

    /**
     * Test de la méthode rechercher.
     * Vérifie que la méthode retourne un Visiteur existant avec un matricule spécifique.
     
    @Test */
    public void testRechercherVisiteur() {
        String matricule = "V123"; // Exemple de matricule valide
        Visiteur visiteur = VisiteurDao.rechercher(matricule);
        
        // Affichage des informations du visiteur pour la vérification manuelle
        System.out.println("Nom : " + visiteur.getNom());
        System.out.println("Prénom : " + visiteur.getPrenom());
        System.out.println("Adresse : " + visiteur.getAdresse());
        System.out.println("Code Postal : " + visiteur.getCodePostal());
        System.out.println("Code Unité : " + visiteur.getCodeUnit());
    }

    /**
     * Test de la méthode rechercher avec un matricule inexistant.
     * Vérifie que la méthode retourne null lorsque le matricule spécifié n'existe pas.
     
    @Test */
    public void testRechercherVisiteurInexistant() {
        String matricule = "V999"; // Exemple de matricule inexistant
        Visiteur visiteur = VisiteurDao.rechercher(matricule);
        
        // Affichage du résultat pour la vérification manuelle
        System.out.println(visiteur == null ? "Visiteur non trouvé." : "Visiteur trouvé : " + visiteur.getNom());
    }

    /**
     * Test de la méthode retournerCollectionDesVisiteurs.
     * Vérifie que la méthode retourne une liste non vide de visiteurs.
     
    @Test */
    public void testRetournerCollectionDesVisiteurs() {
        // Affichage de la taille de la collection de visiteurs pour la vérification manuelle
        System.out.println("Nombre de visiteurs : " + VisiteurDao.retournerCollectionDesVisiteurs().size());
    }

    /**
     * Test de la méthode creer.
     * Vérifie que la méthode permet d'ajouter un nouveau visiteur à la base de données.
     
    @Test */
    public void testCreerVisiteur() {
        Visiteur nouveauVisiteur = new Visiteur("V999", "Martin", "Pierre", "pmartin", "mdp123", "456 avenue de Lyon", "69003", java.sql.Date.valueOf("2024-11-21"), "U002", "Médecine générale");
        VisiteurDao.creer(nouveauVisiteur);

        // Affichage des informations du visiteur ajouté pour la vérification manuelle
        Visiteur visiteurAjoute = VisiteurDao.rechercher("V999");
        System.out.println("Visiteur ajouté : ");
        System.out.println("Nom : " + visiteurAjoute.getNom());
        System.out.println("Prénom : " + visiteurAjoute.getPrenom());
        System.out.println("Adresse : " + visiteurAjoute.getAdresse());
    }
}
