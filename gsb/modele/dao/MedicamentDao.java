package gsb.modele.dao;

import gsb.modele.Medicament;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'interagir avec la table `MEDICAMENT` de la base de données.
 * Elle fournit des méthodes pour rechercher un médicament par son code dépôt légal, 
 * récupérer une collection de médicaments ou rechercher des médicaments par famille.
 */
public class MedicamentDao {

    /**
     * Recherche un médicament dans la base de données en fonction de son code dépôt légal.
     * Cette méthode effectue une requête SQL pour récupérer les informations d'un médicament à partir du code dépôt légal.
     *
     * @param connection La connexion à la base de données.
     * @param depotLegal Le code dépôt légal du médicament à rechercher.
     * @return Un objet {@link Medicament} contenant les informations du médicament, ou {@code null} si aucun médicament n'a été trouvé.
     */
    public static Medicament rechercher(Connection connection, String depotLegal) {
        Medicament medicament = null;
        String sql = "SELECT m.MED_DEPOTLEGAL, m.MED_NOMCOMMERCIAL, m.MED_COMPOSITION, m.MED_EFFETS, m.MED_CONTREINDIC, "
                   + "m.FAM_CODE, m.FAM_LIBELLE, m.MED_PRIXECHANTILLON "
                   + "FROM MEDICAMENT m "
                   + "WHERE m.MED_DEPOTLEGAL = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, depotLegal);  // Paramétrage du code dépôt légal dans la requête
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Création de l'objet Medicament avec les résultats de la requête
                    medicament = new Medicament(
                        resultSet.getString("MED_DEPOTLEGAL"),
                        resultSet.getString("MED_NOMCOMMERCIAL"),
                        resultSet.getString("MED_COMPOSITION"),
                        resultSet.getString("MED_EFFETS"),
                        resultSet.getString("MED_CONTREINDIC"),
                        resultSet.getString("FAM_CODE"),
                        resultSet.getString("FAM_LIBELLE"),
                        resultSet.getString("MED_PRIXECHANTILLON")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicament;
    }

    /**
     * Récupère une collection de tous les médicaments présents dans la base de données.
     * Cette méthode effectue une requête SQL pour récupérer toutes les informations des médicaments.
     *
     * @param connection La connexion à la base de données.
     * @return Une {@link List} contenant tous les objets {@link Medicament} récupérés.
     */
    public static List<Medicament> retournerCollectionDesMedicaments(Connection connection) {
        List<Medicament> medicaments = new ArrayList<>();  // Liste pour stocker les médicaments récupérés
        String sql = "SELECT m.MED_DEPOTLEGAL, m.MED_NOMCOMMERCIAL, m.MED_COMPOSITION, m.MED_EFFETS, m.MED_CONTREINDIC, "
                   + "m.FAM_CODE, m.FAM_LIBELLE, m.MED_PRIXECHANTILLON "
                   + "FROM MEDICAMENT m";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                // Création de l'objet Medicament pour chaque ligne de la requête
                Medicament medicament = new Medicament(
                    resultSet.getString("MED_DEPOTLEGAL"),
                    resultSet.getString("MED_NOMCOMMERCIAL"),
                    resultSet.getString("MED_COMPOSITION"),
                    resultSet.getString("MED_EFFETS"),
                    resultSet.getString("MED_CONTREINDIC"),
                    resultSet.getString("FAM_CODE"),
                    resultSet.getString("FAM_LIBELLE"),
                    resultSet.getString("MED_PRIXECHANTILLON")
                );
                medicaments.add(medicament);  // Ajout du médicament à la liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;  // Retourne la liste des médicaments
    }

    /**
     * Recherche des médicaments par leur code de famille dans la base de données.
     * Cette méthode effectue une requête SQL pour récupérer les médicaments associés à une famille particulière.
     *
     * @param connection La connexion à la base de données.
     * @param familleCode Le code de la famille des médicaments à rechercher.
     * @return Une {@link List} contenant tous les objets {@link Medicament} correspondant à la famille.
     */
    public static List<Medicament> rechercherParFamille(Connection connection, String familleCode) {
        List<Medicament> medicaments = new ArrayList<>();  // Liste pour stocker les médicaments récupérés
        String sql = "SELECT m.MED_DEPOTLEGAL, m.MED_NOMCOMMERCIAL, m.MED_COMPOSITION, m.MED_EFFETS, "
                   + "m.MED_CONTREINDIC, m.FAM_CODE, m.FAM_LIBELLE, m.MED_PRIXECHANTILLON "
                   + "FROM MEDICAMENT m WHERE m.FAM_CODE = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, familleCode);  // Paramétrage du code de la famille dans la requête
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Création de l'objet Medicament pour chaque ligne de la requête
                    Medicament medicament = new Medicament(
                        resultSet.getString("MED_DEPOTLEGAL"),
                        resultSet.getString("MED_NOMCOMMERCIAL"),
                        resultSet.getString("MED_COMPOSITION"),
                        resultSet.getString("MED_EFFETS"),
                        resultSet.getString("MED_CONTREINDIC"),
                        resultSet.getString("FAM_CODE"),
                        resultSet.getString("FAM_LIBELLE"),
                        resultSet.getString("MED_PRIXECHANTILLON")
                    );
                    medicaments.add(medicament);  // Ajout du médicament à la liste
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;  // Retourne la liste des médicaments de la famille donnée
    }
    public static ArrayList<Object[]> getMedicamentsByVisite(String reference) {
        ArrayList<Object[]> medicaments = new ArrayList<>();
        String query = "SELECT MED_OFFERT_1, QUANTITE_MED_1, MED_OFFERT_2, QUANTITE_MED_2 " +
                       "FROM VISITE WHERE REFERENCE = ?";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reference);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Récupérer le premier médicament et sa quantité
                    String medicament1 = resultSet.getString("MED_OFFERT_1");
                    int quantite1 = resultSet.getInt("QUANTITE_MED_1");
                    if (medicament1 != null && !medicament1.isEmpty()) {
                        medicaments.add(new Object[] { medicament1, quantite1 });
                    }

                    // Récupérer le second médicament et sa quantité
                    String medicament2 = resultSet.getString("MED_OFFERT_2");
                    int quantite2 = resultSet.getInt("QUANTITE_MED_2");
                    if (medicament2 != null && !medicament2.isEmpty()) {
                        medicaments.add(new Object[] { medicament2, quantite2 });
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;
    }
}
