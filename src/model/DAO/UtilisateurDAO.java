package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DTO.Utilisateur;

public class UtilisateurDAO {
	
	/**
	 * M�tode permettant de r�cup�rer les informations relatives � un utilisateur
	 * @param id (id utilidsateur)
	 * @return ResultSet
	 */
	public static Utilisateur unUtilisateur(String id)
     {
		 Utilisateur unUtilisateur = null;
	     String requete = "SELECT *  FROM utilisateur where  id = '" + id + "'";
         ResultSet rs = DBConnex.getRS(requete, DBConnex.connexion());
         
         try {
			unUtilisateur = new Utilisateur(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
 		return unUtilisateur;
 			
     }
}
