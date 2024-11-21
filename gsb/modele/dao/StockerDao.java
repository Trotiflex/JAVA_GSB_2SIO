package gsb.modele.dao;

import gsb.modele.Stocker;
import gsb.modele.Medicament;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'interagir avec la table `STOCK` de la base de données.
 * Elle fournit des méthodes pour récupérer le stock d'échantillons pour un visiteur,
 * ainsi que pour ajouter ou mettre à jour la quantité d'échantillons d'un médicament pour un visiteur.
 */
public class StockerDao {

    /**
     * Récupère le stock des médicaments pour un visiteur donné.
     * Cette méthode effectue une requête SQL pour récupérer les informations du stock d'un visiteur.
     * Pour chaque médicament, elle utilise `MedicamentDao` pour récupérer les détails du médicament correspondant.
     *
     * @param connection La connexion à la base de données.
     * @param matricule Le matricule du visiteur pour lequel récupérer le stock.
     * @return Une {@link ArrayList} contenant des objets {@link Stocker} qui représentent
     *         les médicaments et leur stock pour ce visiteur.
     */
    public static ArrayList<Stocker> getStockPourVisiteur(Connection connection, String matricule) {
        ArrayList<Stocker> listeStock = new ArrayList<>();  // Liste pour stocker les objets Stocker

        if (connection == null) {
            System.err.println("La connexion à la base de données est nulle.");
            return listeStock; // Retourne une liste vide si la connexion est nulle
        }

        String query = "SELECT s.MATRICULE, s.MED_DEPOTLEGAL, s.STOCK "
                     + "FROM STOCK s "
                     + "WHERE s.MATRICULE = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, matricule);  // Paramétrage du matricule dans la requête
            ResultSet rs = stmt.executeQuery();  // Exécution de la requête

            while (rs.next()) {
                String medDepotLegal = rs.getString("MED_DEPOTLEGAL");  // Récupération du code dépôt légal du médicament
                int quantiteStockee = rs.getInt("STOCK");  // Récupération de la quantité en stock

                // Récupérer les détails du médicament à partir de son code dépôt légal
                Medicament medicament = MedicamentDao.rechercher(connection, medDepotLegal);

                // Si le médicament est trouvé, créer un objet Stocker et l'ajouter à la liste
                if (medicament != null) {
                    Stocker stock = new Stocker(matricule, medicament, quantiteStockee);
                    listeStock.add(stock);  // Ajout du stock à la liste
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeStock;  // Retourne la liste des stocks pour le visiteur
    }

    /**
     * Ajoute ou met à jour la quantité d'échantillons d'un médicament pour un visiteur donné.
     * Si l'échantillon existe déjà dans la table `STOCK`, la quantité est mise à jour.
     * Sinon, un nouvel enregistrement est créé.
     *
     * @param connection La connexion à la base de données.
     * @param matricule Le matricule du visiteur pour lequel l'échantillon doit être ajouté ou mis à jour.
     * @param depotLegal Le code dépôt légal du médicament à ajouter ou mettre à jour.
     * @param quantite La quantité d'échantillons à ajouter.
     * @return {@code true} si l'ajout ou la mise à jour a réussi, sinon {@code false}.
     */
    public static boolean ajouterEchantillon(Connection connection, String matricule, String depotLegal, int quantite) {
        if (connection == null) {
            System.err.println("La connexion à la base de données est nulle.");
            return false;
        }

        String query = "INSERT INTO STOCK (MATRICULE, MED_DEPOTLEGAL, STOCK) VALUES (?, ?, ?) "
                     + "ON DUPLICATE KEY UPDATE STOCK = STOCK + VALUES(STOCK)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, matricule);  // Paramétrage du matricule dans la requête
            stmt.setString(2, depotLegal);  // Paramétrage du code dépôt légal
            stmt.setInt(3, quantite);  // Paramétrage de la quantité

            // Exécution de la requête et vérification de l'exécution
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
