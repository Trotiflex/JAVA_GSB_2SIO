package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe permettant d'interagir avec la table `MEDECIN` de la base de données.
 * Elle fournit des méthodes pour rechercher des médecins, récupérer une liste de médecins,
 * ou obtenir un dictionnaire des médecins par code.
 */
public class MedecinDao {

    /**
     * Recherche un médecin dans la base de données à partir de son code.
     * Cette méthode utilise une requête SQL pour récupérer les informations d'un médecin,
     * y compris les données de la localité associée.
     *
     * @param codeMedecin Le code du médecin à rechercher.
     * @return Un objet {@link Medecin} contenant les informations du médecin, ou {@code null} si aucun médecin n'a été trouvé.
     */
    public static Medecin rechercher(String codeMedecin) {
        Medecin unMedecin = null;  // Initialisation de la variable pour stocker le médecin trouvé
        String requete = "SELECT M.*, L.VILLE FROM MEDECIN M " +
                         "LEFT JOIN LOCALITE L ON M.CODEPOSTAL = L.CODEPOSTAL " +
                         "WHERE M.CODEMED = ?";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
            preparedStatement.setString(1, codeMedecin);  // Paramétrage du code médecin dans la requête
            try (ResultSet reqSelection = preparedStatement.executeQuery()) {
                if (reqSelection.next()) {
                    // Récupération des données de la localité
                    Localite uneLocalite = new Localite(
                        reqSelection.getString("CODEPOSTAL"),
                        reqSelection.getString("VILLE")
                    );

                    // Création de l'objet Medecin avec les données récupérées
                    unMedecin = new Medecin(
                        reqSelection.getString("CODEMED"),
                        reqSelection.getString("NOM"),
                        reqSelection.getString("PRENOM"),
                        reqSelection.getString("ADRESSE"),
                        uneLocalite,
                        reqSelection.getString("TELEPHONE"),
                        reqSelection.getString("POTENTIEL"),
                        reqSelection.getString("SPECIALITE")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du médecin avec le code : " + codeMedecin);
            e.printStackTrace();
        }
        return unMedecin;
    }

    /**
     * Récupère la liste complète de tous les médecins de la base de données.
     * Cette méthode récupère tous les codes des médecins et les recherche un à un
     * pour construire une collection d'objets {@link Medecin}.
     *
     * @return Une {@link ArrayList} contenant tous les objets {@link Medecin} récupérés.
     */
    public static ArrayList<Medecin> retournerCollectionDesMedecins() {
        ArrayList<Medecin> collectionDesMedecins = new ArrayList<>();  // Initialisation de la liste des médecins
        String requete = "SELECT CODEMED FROM MEDECIN";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete);
             ResultSet reqSelection = preparedStatement.executeQuery()) {
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);  // Récupération du code du médecin
                Medecin medecin = MedecinDao.rechercher(codeMedecin);  // Recherche du médecin via son code
                if (medecin != null) {
                    collectionDesMedecins.add(medecin);  // Ajout du médecin à la collection si trouvé
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la collection des médecins.");
            e.printStackTrace();
        }
        return collectionDesMedecins;  // Retourne la collection des médecins
    }

    /**
     * Récupère un dictionnaire des médecins, où la clé est le code du médecin et la valeur est l'objet {@link Medecin}.
     * Cette méthode fonctionne de manière similaire à la méthode `retournerCollectionDesMedecins`, mais elle
     * utilise un dictionnaire {@link HashMap} pour stocker les médecins, ce qui permet un accès plus rapide par code.
     *
     * @return Un {@link HashMap} contenant des paires clé-valeur où la clé est le code du médecin et la valeur est l'objet {@link Medecin}.
     */
    public static HashMap<String, Medecin> retournerDictionnaireDesMedecins() {
        HashMap<String, Medecin> diccoDesMedecins = new HashMap<>();  // Initialisation du dictionnaire des médecins
        String requete = "SELECT CODEMED FROM MEDECIN";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete);
             ResultSet reqSelection = preparedStatement.executeQuery()) {
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);  // Récupération du code du médecin
                Medecin medecin = MedecinDao.rechercher(codeMedecin);  // Recherche du médecin via son code
                if (medecin != null) {
                    diccoDesMedecins.put(codeMedecin, medecin);  // Ajout du médecin au dictionnaire
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du dictionnaire des médecins.");
            e.printStackTrace();
        }
        return diccoDesMedecins;  // Retourne le dictionnaire des médecins
    }
}
