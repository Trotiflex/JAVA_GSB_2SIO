package gsb.modele;

public class Stocker {
    private String referenceVisite;  // Référence à la visite (clé étrangère)
    private Medicament medDepotLegal;    // Dépôt légal du médicament (clé étrangère)
    private int quantiteStockee;     // Quantité de médicament stockée

    // Constructeur
    public Stocker(String referenceVisite, Medicament medicament, int quantiteStockee) {
        this.referenceVisite = referenceVisite;
        this.medDepotLegal = medicament;
        this.quantiteStockee = quantiteStockee;
    }

    // Getters et Setters
    public String getReferenceVisite() {
        return referenceVisite;
    }

    public void setReferenceVisite(String referenceVisite) {
        this.referenceVisite = referenceVisite;
    }

    public Medicament getMedDepotLegal() {
        return medDepotLegal;
    }

    public void setMedDepotLegal(Medicament medDepotLegal) {
        this.medDepotLegal = medDepotLegal;
    }

    public int getQuantiteStockee() {
        return quantiteStockee;
    }

    public void setQuantiteStockee(int quantiteStockee) {
        this.quantiteStockee = quantiteStockee;
    }

    @Override
    public String toString() {
        return "Stocker{" +
               "referenceVisite='" + referenceVisite + '\'' +
               ", medDepotLegal='" + medDepotLegal + '\'' +
               ", quantiteStockee=" + quantiteStockee +
               '}';
    }
}
