package gsb.modele;

import java.sql.Date;

/**
 * Représente un visiteur du système avec des informations détaillées.
 * La classe Visiteur contient des informations sur un visiteur, telles que son matricule,
 * son nom, son adresse, son code postal, la date d'entrée dans l'entreprise, etc.
 */
public class Visiteur {
    private String matricule;        // Matricule du visiteur
    private String nom;              // Nom du visiteur
    private String prenom;           // Prénom du visiteur
    private String login;            // Login du visiteur
    private String mdp;              // Mot de passe du visiteur
    private String adresse;          // Adresse du visiteur
    private String codePostal;       // Code postal du visiteur
    private Date dateEntree;         // Date d'entrée dans l'entreprise
    private String codeUnit;         // Code de l'unité du visiteur
    private String nomUnit;          // Nom de l'unité du visiteur

    /**
     * Constructeur de la classe Visiteur.
     * Initialise un objet Visiteur avec les informations données.
     *
     * @param matricule Matricule unique du visiteur.
     * @param nom Nom du visiteur.
     * @param prenom Prénom du visiteur.
     * @param login Login utilisé par le visiteur.
     * @param mdp Mot de passe du visiteur.
     * @param adresse Adresse physique du visiteur.
     * @param codePostal Code postal du visiteur.
     * @param dateEntree Date d'entrée du visiteur dans l'entreprise.
     * @param codeUnit Code de l'unité du visiteur.
     * @param nomUnit Nom de l'unité du visiteur.
     */
    public Visiteur(String matricule, String nom, String prenom, String login, String mdp, String adresse,
                    String codePostal, Date dateEntree, String codeUnit, String nomUnit) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.dateEntree = dateEntree;
        this.codeUnit = codeUnit;
        this.nomUnit = nomUnit;
    }

    // Getters et Setters

    /**
     * Récupère le matricule du visiteur.
     *
     * @return Le matricule du visiteur.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Définit le matricule du visiteur.
     *
     * @param matricule Le matricule à définir.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Récupère le nom du visiteur.
     *
     * @return Le nom du visiteur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du visiteur.
     *
     * @param nom Le nom à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom du visiteur.
     *
     * @return Le prénom du visiteur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du visiteur.
     *
     * @param prenom Le prénom à définir.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère le login du visiteur.
     *
     * @return Le login du visiteur.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit le login du visiteur.
     *
     * @param login Le login à définir.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Récupère le mot de passe du visiteur.
     *
     * @return Le mot de passe du visiteur.
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Définit le mot de passe du visiteur.
     *
     * @param mdp Le mot de passe à définir.
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Récupère l'adresse du visiteur.
     *
     * @return L'adresse du visiteur.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse du visiteur.
     *
     * @param adresse L'adresse à définir.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Récupère le code postal du visiteur.
     *
     * @return Le code postal du visiteur.
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit le code postal du visiteur.
     *
     * @param codePostal Le code postal à définir.
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Récupère la date d'entrée du visiteur dans l'entreprise.
     *
     * @return La date d'entrée du visiteur.
     */
    public Date getDateEntree() {
        return dateEntree;
    }

    /**
     * Définit la date d'entrée du visiteur dans l'entreprise.
     *
     * @param dateEntree La date d'entrée à définir.
     */
    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    /**
     * Récupère le code de l'unité à laquelle le visiteur appartient.
     *
     * @return Le code de l'unité.
     */
    public String getCodeUnit() {
        return codeUnit;
    }

    /**
     * Définit le code de l'unité à laquelle le visiteur appartient.
     *
     * @param codeUnit Le code de l'unité à définir.
     */
    public void setCodeUnit(String codeUnit) {
        this.codeUnit = codeUnit;
    }

    /**
     * Récupère le nom de l'unité à laquelle le visiteur appartient.
     *
     * @return Le nom de l'unité.
     */
    public String getNomUnit() {
        return nomUnit;
    }

    /**
     * Définit le nom de l'unité à laquelle le visiteur appartient.
     *
     * @param nomUnit Le nom de l'unité à définir.
     */
    public void setNomUnit(String nomUnit) {
        this.nomUnit = nomUnit;
    }
}
