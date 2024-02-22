package controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO.DBConnex;


public class Controllerauthentification implements Initializable {
	

	/**
	 * Déclaration des variables du fichier FXML associé
	 */
	@FXML private Label messageConnexionLabel;
	@FXML private TextField serveurTextField;
	@FXML private TextField portTextField;
	@FXML private TextField loginTextField;
	@FXML private PasswordField mdpPasswordField;
	
	
	/**
	 * Méthode associée  à l'évènement click sur le bouton valider
	 * @param e
	 */
	@FXML	protected void buttonValiderIdentificationClick(ActionEvent e) {
			
		//Appel de la méthode statique authentification de la classe DBConnex
		//En paramétre le login et mdp saisis et la déclaration de connexion.
		//Le ResultSet récupère la réponse du serveur SGBD.
	
		ResultSet rs = DBConnex.authentification(loginTextField.getText() , mdpPasswordField.getText(), DBConnex.connexion() );
		
		try {
			
			
			//Traitement de la réponse du SGBD
			//Chargement des différentes vues en fonction du statut de la personne authentifiée.
	
			if(rs != null) {
				 FXMLLoader loader = new FXMLLoader();
				 messageConnexionLabel.setText("");
			 
			 
				if(rs.getNString("statut").equals("comptable")) {
					loader.setLocation(Main.class.getResource("../view/viewComptableValidationDesFiches.fxml"));
			 		Pane comptableListeFichesLayout = (Pane) loader.load(); 		
			 		Scene comptableListeFichesScene = new Scene(comptableListeFichesLayout);
	            	Stage comptableListeFichesStage = new Stage();
			 		comptableListeFichesStage.setScene(comptableListeFichesScene);
	           		
			 		comptableListeFichesStage.setTitle("GSB Gestion des frais - Comptable Fiches de frais");
			 		comptableListeFichesStage.initModality(Modality.APPLICATION_MODAL);		 		
			 		comptableListeFichesStage.show();
			 	}
				else if(rs.getNString("statut").equals("visiteur")) {
					loader.setLocation(Main.class.getResource("../view/viewVisiteurListeFiches.fxml"));
					Pane visiteurListeFichesLayout = (Pane) loader.load();
			 		Scene visiteurListeFichesScene = new Scene(visiteurListeFichesLayout);
	            	Stage visiteurListeFichesStage = new Stage();
	            	visiteurListeFichesStage.setScene(visiteurListeFichesScene);
	           		
	            	visiteurListeFichesStage.setTitle("GSB Gestion des frais - Visiteurs Fiches de frais");
	            	visiteurListeFichesStage.initModality(Modality.APPLICATION_MODAL);		 		
	            	visiteurListeFichesStage.show();
			 	}
				else if(rs.getNString("statut").equals("gestion")) {
					loader.setLocation(Main.class.getResource("../view/viewGestionnaireListeFiches.fxml"));
					Pane gestionnaireListeFichesLayout = (Pane) loader.load();
			 		Scene gestionnaireListeFichesScene = new Scene(gestionnaireListeFichesLayout);
	            	Stage gestionnaireListeFichesStage = new Stage();
	            	gestionnaireListeFichesStage.setScene(gestionnaireListeFichesScene);
	           		
	            	gestionnaireListeFichesStage.setTitle("GSB Gestion des frais - Gestionnaires Fiches de frais");
	            	gestionnaireListeFichesStage.initModality(Modality.APPLICATION_MODAL);		 		
	            	gestionnaireListeFichesStage.show();
			 	}
			}
			else {
				messageConnexionLabel.setText("Login ou mot de passe incorrect !");
			}
	        
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
			
	}

	/**
	 * Fermeture de l'application associée au click sur le boton quitter
	 * @param e
	 */
	@FXML
	protected void quitterIdentificationButton(ActionEvent e) {
		Platform.exit();
		
	}

	/**
	 * Valeurs par défaut pour la connexion au SGBD
	 * A l'inicilisation du contrôleur 
	 ***/
	@Override
	public void initialize(URL location , ResourceBundle ressources) {
		serveurTextField.setText("127.0.0.1");
		portTextField.setText("3306");
	}
	
	

}
