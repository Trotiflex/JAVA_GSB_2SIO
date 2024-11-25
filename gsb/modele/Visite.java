package gsb.modele;

import java.sql.Date;

public class Visite {
    private String reference;
    private String codeMed;
    private Date dateVisite;
    private String commentaire;
    private String matricule;
    private Localite localite;

    // Constructeur avec les paramètres mentionnés
    public Visite(String reference, String codeMed, Date date, String commentaire, String matricule, Localite localite) {
        this.reference = reference;
        this.codeMed = codeMed;
        this.dateVisite = date;
        this.commentaire = commentaire;
        this.matricule = matricule;
        this.localite = localite;
    }

    // Getters et setters pour tous les champs

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCodeMed() {
        return codeMed;
    }

    public void setCodeMed(String codeMed) {
        this.codeMed = codeMed;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }
}


