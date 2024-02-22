package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.FicheFrais;
import model.DTO.LigneFrais;

public class FicheFraisDAO {
	
	/**
	 * M�thode permettant de r�cup�rer les fiches de frais
	 * @return ResultSet (la liste des fiches de frais
	 */
	 public static List<FicheFrais> lesFichesFrais() {
		 List<FicheFrais> lesFichesFrais = new ArrayList<FicheFrais>();
		 
		 String requete = "SELECT idFiche, mois, annee, dateCreation, dateCloture, idEtat, montantDeclare, idUtilisateur  FROM fichefrais order by annee desc , mois desc";
		 ResultSet rs =  DBConnex.getRS(requete, DBConnex.connexion());
		 
		 try {
			do {
				 FicheFrais ff = new FicheFrais(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getDate(4), rs.getDate(5), 
						 rs.getString(6), rs.getFloat(7), UtilisateurDAO.unUtilisateur(rs.getString(8)));
				 ArrayList<LigneFrais> lesLignesFrais = FicheFraisDAO.lesLignesFicheFrais(ff.getIdFiche());
				 ff.setLesLignes(lesLignesFrais);
				 lesFichesFrais.add(ff);
			 } while (rs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return lesFichesFrais;
     }
	 

	 
	/**
		* M�thode permettant de r�cup�rer les lignes d'une fiche de frais
		* @param unIdFiche
		* @return ResultSet (la liste des lignes d'une fiche de frais
	*/
	public static ArrayList<LigneFrais> lesLignesFicheFrais(int unIdFiche) {
		ArrayList<LigneFrais> lesLignesFrais = new ArrayList<>();
		String requete = "SELECT idFiche, idTypeFrais, quantiteDeclaree, libelle , montant, montant, quantiteValidee  FROM lignefrais join typefrais on lignefrais.idTypeFrais = typefrais.id  where idFiche = " + unIdFiche ;
		ResultSet rs = DBConnex.getRS(requete, DBConnex.connexion());
		try {
			if (rs!=null) {
				do {
					LigneFrais lf = new LigneFrais(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getInt(6));
					lesLignesFrais.add(lf);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lesLignesFrais;
	}
	
	 
	 /**
	  * M�thode permettant de modifier l'�tat d'une fiche de frais
	  * @param unIdFiche
	  * @param nouvelEtat (code �tat)
	  * @return Interger 
	  */
	 public static Integer changerEtat(int unIdFiche , String nouvelEtat)
     {
		 		 
	     String requete = "update ficheFrais set idEtat = '" + nouvelEtat + "' where idFiche = " + unIdFiche ;
                 
         return DBConnex.noQuery(requete, DBConnex.connexion());
 		
 		
     }
	 
	 
	 public static Integer changerQuantiteValidee(int unIdFiche , int quantite , String typeFrais)
     {
		 		 
	     String requete = "update lignefrais set quantiteValidee = " + quantite + " where idFiche = " + unIdFiche + " and idTypeFrais ='"  + typeFrais+ "'";
                 
	     System.out.println(requete);
	     
         return DBConnex.noQuery(requete, DBConnex.connexion());
 		
 		
     }
	 
	 
	 
}
