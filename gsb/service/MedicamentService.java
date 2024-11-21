package gsb.service;

import gsb.modele.Medicament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service pour la gestion des médicaments.
 * Cette classe contient des méthodes pour rechercher des médicaments en fonction de leur code dépôt légal.
 */
public class MedicamentService {

    /**
     * Recherche un médicament à partir de son code dépôt légal.
     * 
     * @param connection La connexion à la base de données.
     * @param depotLegal Le code dépôt légal du médicament à rechercher.
     * @return Le médicament trouvé ou null si aucun médicament n'a été trouvé avec le code fourni.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de données.
     */
    public static Medicament rechercher(Connection connection, String depotLegal) {
        Medicament medicament = null;
        String query = "SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, depotLegal); // Définit le code dépôt légal dans la requête SQL.
            ResultSet rs = stmt.executeQuery(); // Exécute la requête.

            if (rs.next()) {
                // Si un résultat est trouvé, créer un objet Medicament avec les données.
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
            // Gestion des erreurs de base de données
            e.printStackTrace();
        }

        return medicament; // Retourne le médicament trouvé ou null si non trouvé.
    }
}
