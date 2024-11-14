package gsb.tests;

import gsb.modele.dao.ConnexionMySql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test de la connexion à la base de données
 * @author Isabelle
 * 17 nov. 2021
 */
public class ConnexionTest {

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
