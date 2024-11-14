package gsb.service;
import gsb.modele.Visiteur; // Make sure to import the Visiteur model
import gsb.modele.dao.VisiteurDao;
public class VisiteurService {

	    public static Visiteur rechercherVisiteur(String Matricule) {
	        Visiteur unVisiteur = null;
	        try {
	            if (Matricule == null) {
	                throw new Exception("Donnee obligatoire : code"); // Required data: code
	            }
	            unVisiteur = VisiteurDao.rechercher(Matricule); // Call to VisiteurDao to search
	        } catch (Exception e) {
	            System.out.println(e.getMessage()); // Print the error message
	        }
	        return unVisiteur; // Return the found visitor or null
	    }
	}


