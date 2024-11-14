package gsb.service;

import gsb.modele.Medicament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicamentService {

    // Méthode pour rechercher un médicament par son code dépôt légal avec connexion en paramètre
    public static Medicament rechercher(Connection connection, String depotLegal) {
        Medicament medicament = null;
        String query = "SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, depotLegal);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                medicament = new Medicament(
                    rs.getString("MED_DEPOTLEGAL"),
                    rs.getString("MED_NOMCOMMERCIAL"),
                    rs.getString("MED_COMPOSITION"),
                    rs.getString("MED_EFFETS"),
                    rs.getString("MED_CONTREINDIC"),
                    rs.getString("FAM_CODE"),
                    rs.getString("FAM_LIBELLE"),
                    rs.getString("MED_PRIXECHANTILLON")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicament;
    }
}
