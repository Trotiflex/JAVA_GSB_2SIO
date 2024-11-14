/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.vue;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class JIFMedecinCons extends JIFMedecin  implements ActionListener {
	
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private JButton premier;
    private JButton suivant;
    private JButton precedent; 
    private JButton dernier; 
    private ArrayList<Medecin> lesMedecins;
	private int indiceEnCours;
        
    public JIFMedecinCons() {
        super();
        premier = new JButton("Premier");
        pBoutons.add(premier);
        suivant = new JButton("Suivant");
        pBoutons.add(suivant);
        precedent = new JButton("Precedent");
        pBoutons.add(precedent);
        dernier = new JButton("Dernier");
        pBoutons.add(dernier);
        // declaration des sources d'evenements
        premier.addActionListener(this);
        suivant.addActionListener(this);
        precedent.addActionListener(this);
        dernier.addActionListener(this);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Consultation données Medecin");
        
        // recuperation des donnees Medecin dans la collection
        lesMedecins = MedecinDao.retournerCollectionDesMedecins();
        indiceEnCours = 0;
        
        if (lesMedecins.size()!=0){ // si collection non vide, affichage des donnees du premier Medecin
        	Medecin leMedecin = lesMedecins.get(0);
	    	remplirText(leMedecin);
	    	}
        
        // ajout d'un ecouteur d'evenement pour la fermeture de la fenetre
        addInternalFrameListener(new InternalFrameAdapter(){
            public void  internalFrameClosing(InternalFrameEvent e) {
                          //le code que tu veux executer a la fermeture de la JInternalFrame
            }
        });
    }
	
    public void actionPerformed(ActionEvent evt) { 
		Object source = evt.getSource();
   		if (source == premier){
				indiceEnCours = 0;
				Medecin leMedecin = lesMedecins.get(indiceEnCours);
		    	remplirText(leMedecin);				}
		 else if (source == dernier){
				indiceEnCours = lesMedecins.size() - 1;
				Medecin leMedecin = lesMedecins.get(indiceEnCours);
		    	remplirText(leMedecin);
				}
		 else if (source == precedent){
				if (indiceEnCours > 0) indiceEnCours = indiceEnCours - 1;
				Medecin leMedecin = lesMedecins.get(indiceEnCours);
		    	remplirText(leMedecin);				}
		 else if (source == suivant){
				if (indiceEnCours < (lesMedecins.size() - 1)) indiceEnCours = indiceEnCours + 1;
				Medecin leMedecin = lesMedecins.get(indiceEnCours);
		    	remplirText(leMedecin);		    	}
 } // fin actionPerformed

}
