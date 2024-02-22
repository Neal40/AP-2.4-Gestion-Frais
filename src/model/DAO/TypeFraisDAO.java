package model.DAO;

import java.sql.ResultSet;

public class TypeFraisDAO {
	
	/**
	 * Métode permettant de récupérer les informations relatives à un utilisateur
	 * @param id (id utilidsateur)
	 * @return ResultSet
	 */
	public static ResultSet getLesTypesFrais()
     {
		 	 
	     String requete = "SELECT *  FROM typefrais" ;
                 
         return DBConnex.getRS(requete, DBConnex.connexion());
 		
 			
     }
}
