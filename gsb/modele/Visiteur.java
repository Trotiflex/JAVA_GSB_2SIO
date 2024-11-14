package gsb.modele;

import java.sql.Date;

public class Visiteur {
    private String matricule;
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private String adresse;
    private String codePostal;
    private Date dateEntree;
    private String codeUnit;
    private String nomUnit;

    // Constructeur avec tous les paramètres
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

    // Getters et Setters pour chaque attribut
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String getCodeUnit() {
        return codeUnit;
    }

    public void setCodeUnit(String codeUnit) {
        this.codeUnit = codeUnit;
    }

    public String getNomUnit() {
        return nomUnit;
    }

    public void setNomUnit(String nomUnit) {
        this.nomUnit = nomUnit;
    }
}
