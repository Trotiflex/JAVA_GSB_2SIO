/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.modele;

/**
 * @author Trotiflex
 * 17 nov. 2024

 */
public class Medecin {
	protected String codeMed ;
	protected String nom;
	protected String prenom;
	protected String adresse;
	protected Localite laLocalite;
	protected String telephone;
	protected String potentiel;
	protected String specialite;
	/**
	 * @param codeMed
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param laLocalite
	 * @param telephone
	 * @param potentiel
	 * @param specialite
	 */
	public Medecin(String codeMed, String nom, String prenom,
			String adresse, Localite laLocalite, String telephone,
			String potentiel, String specialite) {
		this.codeMed = codeMed;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.laLocalite = laLocalite;
		this.telephone = telephone;
		this.potentiel = potentiel;
		this.specialite = specialite;
	}
	/**
	 * @return Renvoie codeMed.
	 */
	public String getCodeMed() {
		return codeMed;
	}
	/**
	 * @param codeMed codeMed � d�finir.
	 */
	public void setCodeMed(String codeMed) {
		this.codeMed = codeMed;
	}
	/**
	 * @return Renvoie nom.
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom nom � d�finir.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return Renvoie prenom.
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom prenom � d�finir.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return Renvoie adresse.
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse adresse � d�finir.
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return Renvoie laLocalite.
	 */
	public Localite getLaLocalite() {
		return laLocalite;
	}
	/**
	 * @param laLocalite laLocalite � d�finir.
	 */
	public void setLaLocalite(Localite laLocalite) {
		this.laLocalite = laLocalite;
	}
	/**
	 * @return Renvoie telephone.
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone telephone � d�finir.
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return Renvoie potentiel.
	 */
	public String getPotentiel() {
		return potentiel;
	}
	/**
	 * @param potentiel potentiel � d�finir.
	 */
	public void setPotentiel(String potentiel) {
		this.potentiel = potentiel;
	}
	/**
	 * @return Renvoie specialite.
	 */
	public String getSpecialite() {
		return specialite;
	}
	/**
	 * @param specialite specialite � d�finir.
	 */
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	
	
	

}
