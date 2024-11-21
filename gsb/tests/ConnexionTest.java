package gsb.tests;

import gsb.modele.dao.ConnexionMySql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test de la connexion à la base de données.
 * Cette classe permet de tester la connexion à la base de données en exécutant une requête SQL simple 
 * pour vérifier si l'accès à la table LOCALITE fonctionne correctement.
 * 
 * @author Isabelle
 * @version 1.0
 * @since 17 nov. 2021
 */
public class ConnexionTest {

    /**
     * Méthode principale pour tester la connexion à la base de données et exécuter une requête.
     * Cette méthode essaie d'établir une connexion à la base de données, puis d'exécuter une requête SQL
     * pour récupérer les données de la table LOCALITE.
     * Les résultats de la requête sont affichés dans la console.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Utilisation d'un try-with-resources pour s'assurer que les ressources sont fermées automatiquement
        String query = "SELECT * FROM LOCALITE";
        
        // Essayez d'établir une connexion et de récupérer des résultats
        try (Connection connection = ConnexionMySql.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Parcours des résultats et affichage
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2));
            }

            System.out.println("fin");

        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de l'exécution de la requête.");
            e.printStackTrace();
        }
    }
}
