package gsb.modele.dao;

import gsb.modele.Localite;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe permettant d'interagir avec la table `LOCALITE` de la base de données.
 * Elle offre des méthodes pour rechercher une localité à partir d'un code postal.
 */
public class LocaliteDao {

    /**
     * Recherche une localité dans la base de données en fonction du code postal.
     * Cette méthode effectue une requête SQL pour récupérer les informations relatives à la localité 
     * correspondant au code postal donné.
     *
     * @param codeLocalite Le code postal de la localité à rechercher.
     * @return Un objet {@link Localite} contenant les informations de la localité, ou {@code null} 
     *         si aucune localité n'a été trouvée avec le code postal spécifié.
     */
    public static Localite rechercher(String codeLocalite) {
        Localite uneLocalite = null;  // Initialisation de la variable pour stocker la localité trouvée
        ResultSet reqSelection = null;  // Déclaration de la variable pour stocker le résultat de la requête SQL

        try {
            // Exécution de la requête SQL pour récupérer la localité en fonction du code postal
            reqSelection = ConnexionMySql.execReqSelection("SELECT * FROM LOCALITE WHERE CODEPOSTAL='" + codeLocalite + "'");

            // Si un résultat est trouvé, création d'un objet Localite à partir des données récupérées
            if (reqSelection.next()) {
                uneLocalite = new Localite(reqSelection.getString(1), reqSelection.getString(2));
            }
        } catch (SQLException e) {
            // Gestion des exceptions SQL et affichage d'un message d'erreur
            System.out.println("Erreur lors de la recherche de la localité avec le code postal : " + codeLocalite);
            e.printStackTrace();
        } finally {
            // Fermeture du ResultSet pour libérer les ressources
            if (reqSelection != null) {
                try {
                    reqSelection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Retourne l'objet Localite trouvé ou null si aucune correspondance
        return uneLocalite;
    }
}
