package gsb.service;

import gsb.modele.Stocker;
import gsb.modele.dao.StockerDao;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Service pour gérer les opérations liées aux stocks de médicaments pour les visiteurs.
 * Cette classe fait appel aux méthodes de la classe StockerDao pour interagir avec la base de données.
 */
public class StockerService {

    /**
     * Récupère la liste des médicaments en stock pour un visiteur donné.
     * Cette méthode utilise le StockerDao pour récupérer le stock du visiteur depuis la base de données.
     *
     * @param matricule Le matricule du visiteur pour lequel on souhaite obtenir le stock.
     * @param connection La connexion à la base de données.
     * @return Une liste d'objets {@link Stocker} représentant le stock pour le visiteur.
     */
    public ArrayList<Stocker> getStockPourVisiteur(String matricule, Connection connection) {
        // Appelle la méthode du StockerDao pour obtenir le stock du visiteur
        return StockerDao.getStockPourVisiteur(connection, matricule);
    }

    /**
     * Ajoute ou met à jour la quantité d'échantillons pour un visiteur.
     * Si le médicament existe déjà dans le stock, la quantité est mise à jour.
     * Sinon, un nouvel enregistrement est ajouté dans la base de données.
     *
     * @param matricule Le matricule du visiteur auquel les échantillons seront ajoutés.
     * @param depotLegal Le code du médicament (dépôt légal) pour lequel la quantité est mise à jour.
     * @param quantite La quantité d'échantillons à ajouter.
     * @param connection La connexion à la base de données.
     * @return true si l'opération a réussi, sinon false.
     */
    public boolean ajouterEchantillon(String matricule, String depotLegal, int quantite, Connection connection) {
        // Appelle la méthode du StockerDao pour ajouter ou mettre à jour l'échantillon
        return StockerDao.ajouterEchantillon(connection, matricule, depotLegal, quantite);
    }
}
