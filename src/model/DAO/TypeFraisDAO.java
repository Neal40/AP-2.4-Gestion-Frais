package model.DAO;

import java.sql.ResultSet;

public class TypeFraisDAO {
	
	/**
	 * M�tode permettant de r�cup�rer les informations relatives � un utilisateur
	 * @param id (id utilidsateur)
	 * @return ResultSet
	 */
	public static ResultSet getLesTypesFrais()
     {
		 	 
	     String requete = "SELECT *  FROM typefrais" ;
                 
         return DBConnex.getRS(requete, DBConnex.connexion());
 		
 			
     }
}
