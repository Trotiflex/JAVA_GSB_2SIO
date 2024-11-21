package gsb.modele;

/**
 * Représente un médicament dans le système, avec ses informations principales.
 * Un médicament est défini par son dépôt légal, son nom commercial, sa composition, 
 * ses effets, ses contre-indications, son prix d'échantillon, ainsi que son code et libellé de famille.
 */
public class Medicament {
    private String medDepotLegal;
    private String medNomCommercial;
    private String medComposition;
    private String medEffets;
    private String medContreIndic;
    private String medPrixEchantillon;
    private String famCode;
    private String famLibelle; // Changer en String pour correspondre à la base de données

    /**
     * Constructeur de la classe Medicament.
     * Initialise un médicament avec les informations données.
     *
     * @param medDepotLegal Le code dépôt légal du médicament.
     * @param medNomCommercial Le nom commercial du médicament.
     * @param medComposition La composition du médicament.
     * @param medEffets Les effets secondaires du médicament.
     * @param medContreIndic Les contre-indications du médicament.
     * @param medPrixEchantillon Le prix de l'échantillon du médicament.
     * @param famCode Le code de la famille à laquelle le médicament appartient.
     * @param famLibelle Le libellé de la famille à laquelle le médicament appartient.
     */
    public Medicament(String medDepotLegal, String medNomCommercial, String medComposition,
                      String medEffets, String medContreIndic, String medPrixEchantillon,
                      String famCode, String famLibelle) {
        this.medDepotLegal = medDepotLegal;
        this.medNomCommercial = medNomCommercial;
        this.medComposition = medComposition;
        this.medEffets = medEffets;
        this.medContreIndic = medContreIndic;
        this.medPrixEchantillon = medPrixEchantillon;
        this.famCode = famCode;
        this.famLibelle = famLibelle;
    }

    // Getters et Setters

    /**
     * Récupère le dépôt légal du médicament.
     *
     * @return Le dépôt légal du médicament.
     */
    public String getMedDepotLegal() {
        return medDepotLegal;
    }

    /**
     * Définit le dépôt légal du médicament.
     *
     * @param medDepotLegal Le dépôt légal du médicament à définir.
     */
    public void setMedDepotLegal(String medDepotLegal) {
        this.medDepotLegal = medDepotLegal;
    }

    /**
     * Récupère le nom commercial du médicament.
     *
     * @return Le nom commercial du médicament.
     */
    public String getMedNomCommercial() {
        return medNomCommercial;
    }

    /**
     * Définit le nom commercial du médicament.
     *
     * @param medNomCommercial Le nom commercial du médicament à définir.
     */
    public void setMedNomCommercial(String medNomCommercial) {
        this.medNomCommercial = medNomCommercial;
    }

    /**
     * Récupère la composition du médicament.
     *
     * @return La composition du médicament.
     */
    public String getMedComposition() {
        return medComposition;
    }

    /**
     * Définit la composition du médicament.
     *
     * @param medComposition La composition du médicament à définir.
     */
    public void setMedComposition(String medComposition) {
        this.medComposition = medComposition;
    }

    /**
     * Récupère les effets du médicament.
     *
     * @return Les effets du médicament.
     */
    public String getMedEffets() {
        return medEffets;
    }

    /**
     * Définit les effets du médicament.
     *
     * @param medEffets Les effets du médicament à définir.
     */
    public void setMedEffets(String medEffets) {
        this.medEffets = medEffets;
    }

    /**
     * Récupère les contre-indications du médicament.
     *
     * @return Les contre-indications du médicament.
     */
    public String getMedContreIndic() {
        return medContreIndic;
    }

    /**
     * Définit les contre-indications du médicament.
     *
     * @param medContreIndic Les contre-indications du médicament à définir.
     */
    public void setMedContreIndic(String medContreIndic) {
        this.medContreIndic = medContreIndic;
    }

    /**
     * Récupère le prix de l'échantillon du médicament.
     *
     * @return Le prix de l'échantillon du médicament.
     */
    public String getMedPrixEchantillon() {
        return medPrixEchantillon;
    }

    /**
     * Définit le prix de l'échantillon du médicament.
     *
     * @param medPrixEchantillon Le prix de l'échantillon du médicament à définir.
     */
    public void setMedPrixEchantillon(String medPrixEchantillon) {
        this.medPrixEchantillon = medPrixEchantillon;
    }

    /**
     * Récupère le code de la famille à laquelle appartient le médicament.
     *
     * @return Le code de la famille du médicament.
     */
    public String getFamCode() {
        return famCode;
    }

    /**
     * Définit le code de la famille à laquelle appartient le médicament.
     *
     * @param famCode Le code de la famille à définir.
     */
    public void setFamCode(String famCode) {
        this.famCode = famCode;
    }

    /**
     * Récupère le libellé de la famille à laquelle appartient le médicament.
     *
     * @return Le libellé de la famille du médicament.
     */
    public String getFamLibelle() {
        return famLibelle;
    }

    /**
     * Définit le libellé de la famille à laquelle appartient le médicament.
     *
     * @param famLibelle Le libellé de la famille à définir.
     */
    public void setFamLibelle(String famLibelle) {
        this.famLibelle = famLibelle;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du médicament.
     * Cette méthode est utile pour afficher un médicament sous forme lisible dans les logs ou les interfaces utilisateurs.
     *
     * @return La chaîne représentant un médicament, avec ses propriétés.
     */
    @Override
    public String toString() {
        return "Medicament{" +
               "medDepotLegal='" + medDepotLegal + '\'' +
               ", medNomCommercial='" + medNomCommercial + '\'' +
               ", medComposition='" + medComposition + '\'' +
               ", medEffets='" + medEffets + '\'' +
               ", medContreIndic='" + medContreIndic + '\'' +
               ", medPrixEchantillon=" + medPrixEchantillon +
               ", famCode='" + famCode + '\'' +
               ", famLibelle='" + famLibelle + '\'' +
               '}';
    }
}
