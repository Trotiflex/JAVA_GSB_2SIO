package gsb.modele.dao;

import gsb.modele.Medicament;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDao {

    // Récupérer un médicament par son code dépôt légal, avec la connexion manuelle
    public static Medicament rechercher(Connection connection, String depotLegal) {
        Medicament medicament = null;
        String sql = "SELECT m.MED_DEPOTLEGAL, m.MED_NOMCOMMERCIAL, m.MED_COMPOSITION, m.MED_EFFETS, m.MED_CONTREINDIC, "
                   + "m.FAM_CODE, m.FAM_LIBELLE, m.MED_PRIXECHANTILLON "
                   + "FROM MEDICAMENT m "
                   + "WHERE m.MED_DEPOTLEGAL = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, depotLegal);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
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

    // Méthode pour retourner une collection de tous les médicaments
    public static List<Medicament> retournerCollectionDesMedicaments(Connection connection) {
        List<Medicament> medicaments = new ArrayList<>();
        String sql = "SELECT m.MED_DEPOTLEGAL, m.MED_NOMCOMMERCIAL, m.MED_COMPOSITION, m.MED_EFFETS, m.MED_CONTREINDIC, "
                   + "m.FAM_CODE, m.FAM_LIBELLE, m.MED_PRIXECHANTILLON "
                   + "FROM MEDICAMENT m";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
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
                medicaments.add(medicament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;
    }

    // Méthode pour rechercher des médicaments par famille
    public static List<Medicament> rechercherParFamille(Connection connection, String familleCode) {
        List<Medicament> medicaments = new ArrayList<>();
        String sql = "SELECT m.MED_DEPOTLEGAL, m.MED_NOMCOMMERCIAL, m.MED_COMPOSITION, m.MED_EFFETS, "
                   + "m.MED_CONTREINDIC, m.FAM_CODE, m.FAM_LIBELLE, m.MED_PRIXECHANTILLON "
                   + "FROM MEDICAMENT m WHERE m.FAM_CODE = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, familleCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
                    medicaments.add(medicament);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;
    }
}
