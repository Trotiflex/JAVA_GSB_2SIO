package gsb.modele.dao;

import java.sql.*;

public class ConnexionMySql {

    private static final String URL = "jdbc:mysql://localhost:3306/gsb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Méthode pour établir une connexion à la base de données
    public static Connection getConnection() {
        Connection cnx = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données.");
        } catch (ClassNotFoundException e) {
            System.out.println("Pilote JDBC non trouvé : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return cnx;
    }

    // Exécute une requête de sélection SQL
    public static ResultSet execReqSelection(String laRequete) throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(laRequete);
            // Il est important de retourner aussi la connexion ici si vous en avez besoin plus tard
            // pour fermer la connexion explicitement après utilisation.
            return resultSet;
        }
        throw new SQLException("Connexion échouée pour la requête : " + laRequete);
    }

    // Exécute une requête de mise à jour SQL (insert, update, delete)
    public static int execReqMaj(String laRequete) throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate(laRequete);
            } finally {
                closeConnection(connection);  // Fermeture explicite de la connexion après la mise à jour
            }
        }
        throw new SQLException("Connexion échouée pour la requête : " + laRequete);
    }

    // Méthode pour fermer explicitement la connexion à la base de données
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée avec succès.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
