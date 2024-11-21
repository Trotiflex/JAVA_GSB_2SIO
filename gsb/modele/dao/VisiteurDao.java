package gsb.modele.dao;

import gsb.modele.Visiteur;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'interagir avec la table `VISITEUR` dans la base de données.
 * Elle fournit des méthodes pour rechercher un visiteur par son matricule, récupérer tous les visiteurs,
 * et créer un nouveau visiteur dans la base de données.
 */
public class VisiteurDao {

    /**
     * Recherche un visiteur en fonction de son matricule.
     * Cette méthode effectue une requête SQL pour récupérer les informations d'un visiteur.
     *
     * @param matricule Le matricule du visiteur à rechercher.
     * @return Un objet {@link Visiteur} correspondant au matricule donné, ou {@code null} si le visiteur n'existe pas.
     */
    public static Visiteur rechercher(String matricule) {
        Visiteur unVisiteur = null;

        // Requête SQL pour récupérer un visiteur par son matricule
        String sql = "SELECT MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT "
                   + "FROM VISITEUR WHERE MATRICULE = ?";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet reqSelection = null;

        try {
            connection = ConnexionMySql.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matricule);  // Paramétrage du matricule dans la requête
            reqSelection = preparedStatement.executeQuery();  // Exécution de la requête

            // Si un visiteur est trouvé, l'instancier
            if (reqSelection.next()) {
                unVisiteur = new Visiteur(
                        reqSelection.getString("MATRICULE"),
                        reqSelection.getString("NOM"),
                        reqSelection.getString("PRENOM"),
                        reqSelection.getString("LOGIN"),
                        reqSelection.getString("MDP"),
                        reqSelection.getString("ADRESSE"),
                        reqSelection.getString("CODEPOSTAL"),
                        reqSelection.getDate("DATEENTREE"),
                        reqSelection.getString("CODEUNIT"),
                        reqSelection.getString("NOMUNIT")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du visiteur avec le matricule : " + matricule);
            e.printStackTrace();
        }

        return unVisiteur;  // Retourne le visiteur trouvé, ou null si aucun résultat
    }

    /**
     * Récupère tous les visiteurs présents dans la base de données.
     * Cette méthode effectue une requête SQL pour récupérer les informations de tous les visiteurs.
     *
     * @return Une {@link ArrayList} contenant tous les objets {@link Visiteur}.
     */
    public static ArrayList<Visiteur> retournerCollectionDesVisiteurs() {
        ArrayList<Visiteur> collectionDesVisiteurs = new ArrayList<>();

        // Requête pour récupérer toutes les informations des visiteurs
        String sql = "SELECT MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT "
                   + "FROM VISITEUR";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet reqSelection = null;

        try {
            connection = ConnexionMySql.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            reqSelection = preparedStatement.executeQuery();  // Exécution de la requête

            // Parcours des résultats et ajout des visiteurs à la liste
            while (reqSelection.next()) {
                Visiteur unVisiteur = new Visiteur(
                        reqSelection.getString("MATRICULE"),
                        reqSelection.getString("NOM"),
                        reqSelection.getString("PRENOM"),
                        reqSelection.getString("LOGIN"),
                        reqSelection.getString("MDP"),
                        reqSelection.getString("ADRESSE"),
                        reqSelection.getString("CODEPOSTAL"),
                        reqSelection.getDate("DATEENTREE"),
                        reqSelection.getString("CODEUNIT"),
                        reqSelection.getString("NOMUNIT")
                );
                collectionDesVisiteurs.add(unVisiteur);  // Ajout à la collection
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la collection des visiteurs");
            e.printStackTrace();
        }

        return collectionDesVisiteurs;  // Retourne la collection de tous les visiteurs
    }

    /**
     * Crée un nouveau visiteur dans la base de données.
     * Cette méthode permet d'ajouter un visiteur à la table `VISITEUR`.
     *
     * @param visiteur L'objet {@link Visiteur} à ajouter à la base de données.
     */
    public static void creer(Visiteur visiteur) {
        String sql = "INSERT INTO VISITEUR (MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Paramétrage des paramètres de la requête SQL avec les valeurs de l'objet visiteur
            preparedStatement.setString(1, visiteur.getMatricule());
            preparedStatement.setString(2, visiteur.getNom());
            preparedStatement.setString(3, visiteur.getPrenom());
            preparedStatement.setString(4, visiteur.getLogin());
            preparedStatement.setString(5, visiteur.getMdp());
            preparedStatement.setString(6, visiteur.getAdresse());
            preparedStatement.setString(7, visiteur.getCodePostal());
            preparedStatement.setDate(8, new Date(visiteur.getDateEntree().getTime()));  // Conversion de la date en format SQL
            preparedStatement.setString(9, visiteur.getCodeUnit());
            preparedStatement.setString(10, visiteur.getNomUnit());

            preparedStatement.executeUpdate();  // Exécution de la requête d'insertion
            System.out.println("Visiteur ajouté avec succès : " + visiteur.getMatricule());

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du visiteur : " + visiteur.getMatricule());
            e.printStackTrace();
        }
    }
}
