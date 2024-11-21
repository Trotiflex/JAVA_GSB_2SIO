package gsb.modele.dao;

import java.sql.*;

/**
 * Classe permettant de gérer les connexions à la base de données MySQL.
 * Elle offre des méthodes pour établir des connexions, exécuter des requêtes SQL de sélection et de mise à jour,
 * ainsi que pour fermer les connexions.
 */
public class ConnexionMySql {

    // URL de la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/gsb";
    // Utilisateur pour se connecter à la base de données
    private static final String USER = "root";
    // Mot de passe pour se connecter à la base de données
    private static final String PASSWORD = "";

    /**
     * Établit une connexion à la base de données MySQL.
     * Utilise les informations de connexion définies dans les constantes {@code URL}, {@code USER}, et {@code PASSWORD}.
     *
     * @return Un objet {@link Connection} représentant la connexion à la base de données.
     */
    public static Connection getConnection() {
        Connection cnx = null;
        try {
            // Chargement du driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tentative de connexion à la base de données
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données.");
        } catch (ClassNotFoundException e) {
            System.out.println("Pilote JDBC non trouvé : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return cnx;
    }

    /**
     * Exécute une requête SQL de sélection (SELECT) sur la base de données.
     * 
     * @param laRequete La requête SQL à exécuter.
     * @return Un objet {@link ResultSet} contenant les résultats de la requête.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public static ResultSet execReqSelection(String laRequete) throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(laRequete);
            return resultSet;
        }
        throw new SQLException("Connexion échouée pour la requête : " + laRequete);
    }

    /**
     * Exécute une requête SQL de mise à jour (INSERT, UPDATE, DELETE) sur la base de données.
     * 
     * @param laRequete La requête SQL à exécuter.
     * @return Le nombre de lignes affectées par la mise à jour.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
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

    /**
     * Ferme explicitement une connexion à la base de données.
     * 
     * @param connection L'objet {@link Connection} à fermer.
     */
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
