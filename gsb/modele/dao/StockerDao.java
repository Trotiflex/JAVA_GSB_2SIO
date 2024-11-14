package gsb.modele.dao;

import gsb.modele.Stocker;
import gsb.modele.Medicament;
import java.sql.*;
import java.util.ArrayList;

public class StockerDao {

    // Méthode pour récupérer le stock pour un visiteur donné
    public static ArrayList<Stocker> getStockPourVisiteur(Connection connection, String matricule) {
        ArrayList<Stocker> listeStock = new ArrayList<>();
        String query = "SELECT s.REFERENCEVISITE, s.MED_DEPOTLEGAL, s.QUANTITESTOCKEE, m.MED_NOMCOMMERCIAL, m.MED_PRIX_ECHANTILLON "
                     + "FROM STOCKER s "
                     + "JOIN MEDICAMENT m ON s.MED_DEPOTLEGAL = m.MED_DEPOTLEGAL "
                     + "WHERE s.MATRICULE = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, matricule);  // Remplir le paramètre pour le matricule (code visiteur)
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String referenceVisite = rs.getString("REFERENCEVISITE");
                String medDepotLegal = rs.getString("MED_DEPOTLEGAL");
                int quantiteStockee = rs.getInt("QUANTITESTOCKEE");
                String nomMedicament = rs.getString("MED_NOMCOMMERCIAL");
                double prixEchantillon = rs.getDouble("MED_PRIX_ECHANTILLON");

                // Création de l'objet Medicament en appelant MedicamentDao.rechercher avec la connexion
                Medicament medicament = MedicamentDao.rechercher(connection, medDepotLegal);
                
                // Si le médicament est trouvé, créer un objet Stocker
                if (medicament != null) {
                    Stocker stock = new Stocker(referenceVisite, medicament, quantiteStockee);
                    listeStock.add(stock);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeStock;
    }
}
