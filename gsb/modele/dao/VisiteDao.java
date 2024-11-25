package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Visite;

import java.sql.*;
import java.util.ArrayList;

/**
 * La classe <code>VisiteDao</code> fournit des méthodes pour interagir avec la base de données
 * concernant les visites. Elle permet de rechercher, créer, modifier et récupérer des visites.
 * Elle utilise une connexion à la base de données pour exécuter les opérations SQL.
 */
public class VisiteDao {

    /**
     * Recherche une visite par sa référence.
     * 
     * Cette méthode permet de récupérer une visite en fonction de sa référence dans la base de données.
     * Elle utilise une connexion ouverte à la base de données pour exécuter la requête et retourne
     * l'objet <code>Visite</code> correspondant à la référence donnée, ou <code>null</code> si la visite
     * n'est pas trouvée.
     *
     * @param reference La référence de la visite.
     * @param conn Connexion ouverte à la base de données.
     * @return La visite correspondante à la référence ou <code>null</code> si non trouvée.
     */
    public static Visite rechercher(String reference, Connection conn) {
        Visite uneVisite = null;
        String query = "SELECT v.reference, v.codemed, v.datevisite, v.commentaire, v.matricule, " +
                       "l.codepostal AS localite_codepostal, l.ville AS localite_ville " +
                       "FROM visite v " +
                       "LEFT JOIN medecin m ON v.codemed = m.codemed " +
                       "LEFT JOIN localite l ON m.codepostal = l.codepostal " +
                       "WHERE v.reference = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, reference);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Localite uneLocalite = new Localite(
                        rs.getString("localite_codepostal"),
                        rs.getString("localite_ville")
                    );
                    uneVisite = new Visite(
                        rs.getString("reference"),
                        rs.getString("codemed"),
                        rs.getDate("datevisite"),
                        rs.getString("commentaire"),
                        rs.getString("matricule"),
                        uneLocalite
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la visite pour la référence : " + reference);
            e.printStackTrace();
        }

        return uneVisite;
    }

    /**
     * Retourne la liste de toutes les visites.
     * 
     * Cette méthode récupère toutes les visites dans la base de données et les retourne sous forme
     * de liste d'objets <code>Visite</code>.
     *
     * @param conn Connexion ouverte à la base de données.
     * @return Liste des visites.
     */
    public static ArrayList<Visite> retournerCollectionDesVisites(Connection conn) {
        ArrayList<Visite> visites = new ArrayList<>();
        String sql = "SELECT v.reference, v.codemed, v.datevisite, v.commentaire, v.matricule, " +
                     "l.codepostal AS localite_codepostal, l.ville AS localite_ville " +
                     "FROM visite v " +
                     "LEFT JOIN medecin m ON v.codemed = m.codemed " +
                     "LEFT JOIN localite l ON m.codepostal = l.codepostal";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Localite localite = new Localite(
                    rs.getString("localite_codepostal"),
                    rs.getString("localite_ville")
                );

                Visite visite = new Visite(
                    rs.getString("reference"),
                    rs.getString("codemed"),
                    rs.getDate("datevisite"),
                    rs.getString("commentaire"),
                    rs.getString("matricule"),
                    localite
                );
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visites;
    }

    /**
     * Recherche une visite par matricule du visiteur et par date.
     * 
     * Cette méthode permet de rechercher une visite en fonction du matricule du visiteur et de la
     * date de la visite dans la base de données. Elle retourne l'objet <code>Visite</code> correspondant
     * ou <code>null</code> si aucune visite n'est trouvée.
     *
     * @param matricule Le matricule du visiteur.
     * @param dateVisite La date de la visite.
     * @param conn Connexion ouverte à la base de données.
     * @return La visite correspondante ou <code>null</code> si non trouvée.
     */
    public static Visite rechercherParVisiteurEtDate(String matricule, String dateVisite, Connection conn) {
        Visite visite = null;
        String query = "SELECT v.reference, v.codemed, v.datevisite, v.commentaire, v.matricule, " +
                       "l.codepostal AS localite_codepostal, l.ville AS localite_ville " +
                       "FROM visite v " +
                       "LEFT JOIN medecin m ON v.codemed = m.codemed " +
                       "LEFT JOIN localite l ON m.codepostal = l.codepostal " +
                       "WHERE v.matricule = ? AND v.datevisite = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, matricule);
            stmt.setString(2, dateVisite);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Localite localite = new Localite(
                        rs.getString("localite_codepostal"),
                        rs.getString("localite_ville")
                    );

                    visite = new Visite(
                        rs.getString("reference"),
                        rs.getString("codemed"),
                        rs.getDate("datevisite"),
                        rs.getString("commentaire"),
                        rs.getString("matricule"),
                        localite
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visite;
    }

    /**
     * Crée une nouvelle visite dans la base de données.
     * 
     * Cette méthode permet d'insérer une nouvelle visite dans la base de données. Elle retourne
     * le nombre de lignes affectées par l'insertion, qui sera 0 en cas d'échec.
     *
     * @param uneVisite La visite à insérer.
     * @param conn Connexion ouverte à la base de données.
     * @return Le nombre de lignes affectées par l'insertion (0 si échec).
     */
    public static int creer(Visite uneVisite, Connection conn) {
        int result = 0;
        String requeteInsertion = "INSERT INTO visite (codemed, reference, datevisite, commentaire, matricule) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(requeteInsertion)) {
            stmt.setString(1, uneVisite.getCodeMed());
            stmt.setString(2, uneVisite.getReference());
            stmt.setDate(3, uneVisite.getDateVisite());
            stmt.setString(4, uneVisite.getCommentaire());
            stmt.setString(5, uneVisite.getMatricule());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Échec de l'insertion de la visite");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Mise à jour d'une visite dans la base de données.
     * 
     * Cette méthode permet de mettre à jour une visite existante dans la base de données. Elle retourne
     * le nombre de lignes affectées par la mise à jour, qui sera 0 en cas d'échec.
     *
     * @param uneVisite La visite à mettre à jour.
     * @param conn Connexion ouverte à la base de données.
     * @return Le nombre de lignes affectées par la mise à jour (0 si échec).
     */
    public static int modifier(Visite uneVisite, Connection conn) {
        int result = 0;
        String requeteUpdate = "UPDATE visite SET codemed = ?, datevisite = ?, commentaire = ?, matricule = ? WHERE reference = ?";

        try (PreparedStatement stmt = conn.prepareStatement(requeteUpdate)) {
            stmt.setString(1, uneVisite.getCodeMed());
            stmt.setDate(2, uneVisite.getDateVisite());
            stmt.setString(3, uneVisite.getCommentaire());
            stmt.setString(4, uneVisite.getMatricule());
            stmt.setString(5, uneVisite.getReference());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Récupère une visite en fonction de sa référence.
     * 
     * Cette méthode permet de récupérer une visite par sa référence en utilisant la connexion à la base de données.
     *
     * @param reference La référence de la visite.
     * @param conn Connexion ouverte à la base de données.
     * @return L'objet <code>Visite</code> correspondant à la référence donnée, ou <code>null</code> si non trouvé.
     */
    public static Visite getVisiteByReference(String reference, Connection conn) {
        Visite visite = null;
        String query = "SELECT v.reference, v.codemed, v.datevisite, v.commentaire, v.matricule, " +
                       "l.codepostal AS localite_codepostal, l.ville AS localite_ville " +
                       "FROM visite v " +
                       "LEFT JOIN medecin m ON v.codemed = m.codemed " +
                       "LEFT JOIN localite l ON m.codepostal = l.codepostal " +
                       "WHERE v.reference = ?";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reference);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Localite localite = new Localite(
                        resultSet.getString("localite_codepostal"),
                        resultSet.getString("localite_ville")
                    );
                    visite = new Visite(
                        resultSet.getString("reference"),
                        resultSet.getString("codemed"),
                        resultSet.getDate("datevisite"),
                        resultSet.getString("commentaire"),
                        resultSet.getString("matricule"),
                        localite
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visite;
    }
}
