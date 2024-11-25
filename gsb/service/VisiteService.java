package gsb.service;

import gsb.modele.Localite;
import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.LocaliteDao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisiteService {
    
    /**
     * Recherche une visite par sa référence.
     * @param reference La référence de la visite.
     * @return La visite correspondante ou null si non trouvée.
     */
    public static Visite rechercherVisite(String reference) {
        Visite uneVisite = null;
        try {
            if (reference == null || reference.isEmpty()) {
                throw new Exception("Référence obligatoire.");
            }
            Connection connection = ConnexionMySql.getConnection();
            uneVisite = VisiteDao.rechercher(reference, connection);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return uneVisite;
    }

    /**
     * Crée une nouvelle visite.
     * @param codeMed Le code du médecin.
     * @param reference La référence de la visite.
     * @param dateVisite La date de la visite (au format String).
     * @param commentaire Le commentaire sur la visite.
     * @param matricule Le matricule du visiteur.
     * @param codePostal Le code postal de la localité de la visite.
     * @return Le résultat de l'insertion (1 si succès, 0 sinon).
     */
    public int creerVisite(String codeMed, String reference, String dateVisite, String commentaire,
                           String matricule, String codePostal) {
        Visite uneVisite = null;
        int resultat = 0;
        try {
            // Contrôle des paramètres d'entrée
            if (codeMed == null || reference == null || dateVisite == null || commentaire == null || matricule == null || codePostal == null) {
                throw new Exception("Données obligatoires : codeMed, reference, dateVisite, commentaire, matricule, codePostal.");
            }

            // Vérification si une visite avec la même référence existe déjà
            Connection connection = ConnexionMySql.getConnection();
            if (VisiteDao.rechercher(reference, connection) != null) {
                throw new Exception("Une visite avec la référence " + reference + " existe déjà.");
            }

            // Recherche de la localité par code postal
            Localite laLocalite = LocaliteDao.rechercher(codePostal);
            if (laLocalite == null) {
                throw new Exception("Aucune localité trouvée avec le code postal " + codePostal);
            }

            // Conversion de la date de visite (chaîne -> Date)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateVisite); // La chaîne doit être au format "yyyy-MM-dd"
            
            // Création de l'objet Visite
            uneVisite = new Visite(codeMed, reference, (java.sql.Date) date, commentaire, matricule, laLocalite);
            
            // Insertion de la visite dans la base de données
            resultat = VisiteDao.creer(uneVisite, connection);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la visite : " + e.getMessage());
        }        
        return resultat;    
    }
}
