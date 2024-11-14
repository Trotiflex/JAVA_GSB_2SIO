/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.vue;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class JIFMedecinListeDic extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	//private ArrayList<Medecin> lesMedecins;
	private HashMap<String,Medecin> diccoMedecin;


	protected JPanel p;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected JTextField JTcodeMedecin;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;
	protected JTable table;

	public JIFMedecinListeDic(MenuPrincipal uneFenetreContainer) {

		fenetreContainer = uneFenetreContainer;
		// recuperation des donnees Medecin dans la collection
		//lesMedecins = MedecinDao.retournerCollectionDesMedecins();

		//int nbLignes = lesMedecins.size();
		diccoMedecin = MedecinDao.retournerDictionnaireDesMedecins();
		int nbLignes= diccoMedecin.size();
		
		p = new JPanel(); // panneau principal de la fenetre

		int i=0;
		String[][] data = new String[nbLignes][4] ;
		//for(Medecin unMedecin : lesMedecins){
		
		for (Map.Entry<String,Medecin> uneEntree : diccoMedecin.entrySet()){
			data[i][0] = uneEntree.getValue().getCodeMed();
			data[i][1] = uneEntree.getValue().getNom();
			data[i][2] = uneEntree.getValue().getPrenom();
			data[i][3] = uneEntree.getValue().getLaLocalite().getVille() ;
			i++;
			}
		String[] columnNames = {"Code", "Nom","Prenom","Ville"};
		table = new JTable(data, columnNames);
		table.getSelectionModel().addListSelectionListener(table);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		p.add(scrollPane);
		
		pSaisie = new JPanel();
		JTcodeMedecin = new JTextField(20);
		JTcodeMedecin.setMaximumSize(JTcodeMedecin.getPreferredSize());
		JBafficherFiche = new JButton("Afficher Fiche medecin");
		JBafficherFiche.addActionListener(this); // source d'evenement
		pSaisie.add(JTcodeMedecin);
		pSaisie.add(JBafficherFiche);
		p.add(pSaisie);
		
		// mise en forme de la fenetre
		Container contentPane = getContentPane();
		contentPane.add(p);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
   		if (source == JBafficherFiche){
   			if (diccoMedecin.containsKey(JTcodeMedecin.getText())){
   	   			Medecin unMedecin = diccoMedecin.get(JTcodeMedecin.getText());
   	   			fenetreContainer.ouvrirFenetre(new JIFMedecinFiche(unMedecin));
   			}
   		}
   		if(source == table){
   			JTcodeMedecin.setText((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
   			
   		}
	}
}
