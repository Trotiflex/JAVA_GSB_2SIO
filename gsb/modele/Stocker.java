package gsb.modele;

/**
 * Représente un stockage de médicament lors d'une visite.
 * La classe Stocker contient les informations sur la quantité de médicament stockée,
 * la référence de la visite (clé étrangère) et le médicament (clé étrangère).
 */
public class Stocker {
    private String referenceVisite;  // Référence à la visite (clé étrangère)
    private Medicament medDepotLegal;    // Dépôt légal du médicament (clé étrangère)
    private int quantiteStockee;     // Quantité de médicament stockée

    /**
     * Constructeur de la classe Stocker.
     * Initialise un objet Stocker avec les informations données.
     *
     * @param referenceVisite La référence de la visite liée à ce stockage de médicament.
     * @param medicament Le médicament stocké (lié par son dépôt légal).
     * @param quantiteStockee La quantité de médicament stockée.
     */
    public Stocker(String referenceVisite, Medicament medicament, int quantiteStockee) {
        this.referenceVisite = referenceVisite;
        this.medDepotLegal = medicament;
        this.quantiteStockee = quantiteStockee;
    }

    // Getters et Setters

    /**
     * Récupère la référence de la visite.
     *
     * @return La référence de la visite.
     */
    public String getReferenceVisite() {
        return referenceVisite;
    }

    /**
     * Définit la référence de la visite.
     *
     * @param referenceVisite La référence de la visite à définir.
     */
    public void setReferenceVisite(String referenceVisite) {
        this.referenceVisite = referenceVisite;
    }

    /**
     * Récupère le médicament stocké (par son dépôt légal).
     *
     * @return Le médicament stocké.
     */
    public Medicament getMedDepotLegal() {
        return medDepotLegal;
    }

    /**
     * Définit le médicament stocké.
     *
     * @param medDepotLegal Le médicament à définir.
     */
    public void setMedDepotLegal(Medicament medDepotLegal) {
        this.medDepotLegal = medDepotLegal;
    }

    /**
     * Récupère la quantité de médicament stockée.
     *
     * @return La quantité de médicament stockée.
     */
    public int getQuantiteStockee() {
        return quantiteStockee;
    }

    /**
     * Définit la quantité de médicament stockée.
     *
     * @param quantiteStockee La quantité de médicament à définir.
     */
    public void setQuantiteStockee(int quantiteStockee) {
        this.quantiteStockee = quantiteStockee;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Stocker.
     * Cette méthode est utile pour afficher les informations de stockage de manière lisible.
     *
     * @return La chaîne représentant l'objet Stocker avec ses propriétés.
     */
    @Override
    public String toString() {
        return "Stocker{" +
               "referenceVisite='" + referenceVisite + '\'' +
               ", medDepotLegal='" + medDepotLegal + '\'' +
               ", quantiteStockee=" + quantiteStockee +
               '}';
    }
}
