package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MedecinDao {

    // Méthode pour rechercher un médecin par son code
    public static Medecin rechercher(String codeMedecin) {
        Medecin unMedecin = null;
        String requete = "SELECT M.*, L.VILLE FROM MEDECIN M " +
                         "LEFT JOIN LOCALITE L ON M.CODEPOSTAL = L.CODEPOSTAL " +
                         "WHERE M.CODEMED = ?";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
            preparedStatement.setString(1, codeMedecin);
            try (ResultSet reqSelection = preparedStatement.executeQuery()) {
                if (reqSelection.next()) {
                    Localite uneLocalite = new Localite(
                        reqSelection.getString("CODEPOSTAL"),
                        reqSelection.getString("VILLE")
                    );

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

    // Méthode pour retourner une collection de tous les médecins
    public static ArrayList<Medecin> retournerCollectionDesMedecins() {
        ArrayList<Medecin> collectionDesMedecins = new ArrayList<>();
        String requete = "SELECT CODEMED FROM MEDECIN";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete);
             ResultSet reqSelection = preparedStatement.executeQuery()) {
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);
                Medecin medecin = MedecinDao.rechercher(codeMedecin);  // Appel séparé à la recherche
                if (medecin != null) {
                    collectionDesMedecins.add(medecin);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la collection des médecins.");
            e.printStackTrace();
        }
        return collectionDesMedecins;
    }

    // Méthode pour retourner un dictionnaire des médecins (clé : code, valeur : Medecin)
    public static HashMap<String, Medecin> retournerDictionnaireDesMedecins() {
        HashMap<String, Medecin> diccoDesMedecins = new HashMap<>();
        String requete = "SELECT CODEMED FROM MEDECIN";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requete);
             ResultSet reqSelection = preparedStatement.executeQuery()) {
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);
                Medecin medecin = MedecinDao.rechercher(codeMedecin);  // Appel séparé à la recherche
                if (medecin != null) {
                    diccoDesMedecins.put(codeMedecin, medecin);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du dictionnaire des médecins.");
            e.printStackTrace();
        }
        return diccoDesMedecins;
    }
}
