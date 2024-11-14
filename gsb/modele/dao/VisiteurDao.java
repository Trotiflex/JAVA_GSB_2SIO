package gsb.modele.dao;

import gsb.modele.Visiteur;

import java.sql.*;
import java.util.ArrayList;

public class VisiteurDao {

    // Méthode pour rechercher un visiteur par son matricule
    public static Visiteur rechercher(String matricule) {
        Visiteur unVisiteur = null;

        // Requête SQL pour récupérer un visiteur par son matricule
        String sql = "SELECT MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT FROM VISITEUR WHERE MATRICULE = ?";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet reqSelection = null;

        try {
            connection = ConnexionMySql.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matricule);
            reqSelection = preparedStatement.executeQuery();

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

        // Connexion et ResultSet ne sont pas fermés ici, vous pouvez les utiliser ailleurs si nécessaire.
        return unVisiteur;
    }

    // Méthode pour récupérer toute la collection des visiteurs
    public static ArrayList<Visiteur> retournerCollectionDesVisiteurs() {
        ArrayList<Visiteur> collectionDesVisiteurs = new ArrayList<>();

        // Requête pour récupérer toutes les informations des visiteurs en une seule fois
        String sql = "SELECT MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT FROM VISITEUR";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet reqSelection = null;

        try {
            connection = ConnexionMySql.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            reqSelection = preparedStatement.executeQuery();

            // Parcourir les résultats du ResultSet pour instancier chaque visiteur
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
                collectionDesVisiteurs.add(unVisiteur);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la collection des visiteurs");
            e.printStackTrace();
        }

        // Connexion et ResultSet ne sont pas fermés ici, vous pouvez les utiliser ailleurs si nécessaire.
        return collectionDesVisiteurs;
    }

    // Méthode pour créer un nouveau visiteur dans la base de données
    public static void creer(Visiteur visiteur) {
        String sql = "INSERT INTO VISITEUR (MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnexionMySql.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, visiteur.getMatricule());
            preparedStatement.setString(2, visiteur.getNom());
            preparedStatement.setString(3, visiteur.getPrenom());
            preparedStatement.setString(4, visiteur.getLogin());
            preparedStatement.setString(5, visiteur.getMdp());
            preparedStatement.setString(6, visiteur.getAdresse());
            preparedStatement.setString(7, visiteur.getCodePostal());
            preparedStatement.setDate(8, new Date(visiteur.getDateEntree().getTime())); // Conversion correcte de la date
            preparedStatement.setString(9, visiteur.getCodeUnit());
            preparedStatement.setString(10, visiteur.getNomUnit());

            preparedStatement.executeUpdate();
            System.out.println("Visiteur ajouté avec succès : " + visiteur.getMatricule());

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du visiteur : " + visiteur.getMatricule());
            e.printStackTrace();
        }
    }
}
