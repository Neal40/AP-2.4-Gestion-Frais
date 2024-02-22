package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO.FicheFraisDAO;
import model.DAO.UtilisateurDAO;
import model.DTO.FicheFrais;
import model.DAO.DBConnex;

import model.DTO.LigneFrais;
import model.DTO.Utilisateur;


public class ControllerGestionDesComptables{

	
	 @FXML 	private TableView<Utilisateur> tableComptable;
	 @FXML 	private TableColumn<Utilisateur , String > colNomComptable;
	 @FXML 	private TableColumn<Utilisateur , String > colPrenomComptable;
	 
	 @FXML  private Button btnAjouterComptable;
	 @FXML  private Button btnModifierComptable;
	 @FXML  private Button btnSupprimerComptable;
	 @FXML  private Button btnQuitter;
	 @FXML  private Button btnValider;
	 
	 @FXML  private Label  lblNom;
	 @FXML  private Label  lblProfil;
	 @FXML  private Label  lblFiches;
	 
	 @FXML private TextField txtNom;
	 @FXML private TextField txtPrenom;
	 @FXML private TextField txtAdresse;
	 @FXML private TextField txtCP;
	 @FXML private TextField txtVille;
	 
	 @FXML private DatePicker dpEmbauche;
	 
	 
	 
	 //Déclaration de l'ObservableList nécessaire au remplissage de la tableView
	 private ObservableList<Utilisateur> data = FXCollections.observableArrayList();
	 
	 
	 /**
	  * Ouverture de la fiche sélectionnée
	  * Click sur le bouton "Ouvrir fiche s�lectionn�e"
	  * @param e
	  */
	
		{
			
			int index = tableComptable.getSelectionModel().getSelectedIndex();
			
			
			if(index >= 0)
			{
			
			//FicheFrais uneFiche = tableListeFichesGestionnaire.getSelectionModel().getSelectedItem();
						
			
			
			}
			else 
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Sélection un Comptable");
				alert.getDialogPane().setContentText("Vous devez sélectionner un comptable pour afficher ces informations");
				alert.showAndWait();
			}
			
			 private void remplissagetableViewGestionDesComptables() 
			 {
				 try
				 {
					 			 
					 ResultSet rsLesComptables = UtilisateurDAO.lesUtilisateurs();
					 
					if(rsLesComptables!= null)
					{
						
						ArrayList<LigneUtilisateur> lesLignesUtilisateurs;
						rsLesComptables.beforeFirst();
						
						while (rsLesComptables.next()) 
						{
							ResultSet rsUnUtilisateur = UtilisateurDAO.unUtilisateur(rsListeFiches.getString(2));			 
							Utilisateur utilisateur = new Utilisateur(rsUtilisateur.getString(1), rsUnVisiteur.getString(2)  , rsUnVisiteur.getString(3)   , rsUnVisiteur.getString(4)  , rsUnVisiteur.getString(5) , rsUnVisiteur.getString(6) , rsUnVisiteur.getString(7) , rsUnVisiteur.getString(8)  , rsUnVisiteur.getString(9), rsUnVisiteur.getDate(10)) ;				
							FicheFrais uneFicheFrais = new FicheFrais(rsListeFiches.getInt(1) ,rsListeFiches.getInt(3) ,rsListeFiches.getInt(4)  ,rsListeFiches.getDate(5)  ,rsListeFiches.getDate(6)  ,rsListeFiches.getString(7) , rsListeFiches.getFloat(8), utilisateur);
						 
							ResultSet rsLesLignes = UtilisateurDAO.lesLignesUtilisateurs(rsLesComptables.getInt(1));
							lesLignesUtilisateurs = new ArrayList<LigneUtilisateur>();
							if(rsLesLignes != null)
							{		
								rsLesLignes.beforeFirst();
								while (rsLesLignes.next()) 
								{
									LigneUtilisateur unUtilisateur = new LigneUtilisateur(rsLesLignes.getInt(1),rsLesLignes.getString(2) ,rsLesLignes.getInt(3), rsLesLignes.getString(5), rsLesLignes.getFloat(6),rsLesLignes.getInt(4));
									lesLignesUtilisateurs.add(unUtilisateur);
								}
							}
							unComptables.setLesLignes(lesLignesUtilisateurs); 				 
							data.add(unComptable);
						}
					}
					 
					 
					 
				 }
				 catch (Exception e) 
				 {
					 e.printStackTrace();
				 }
				 
				 
				colNomComptable.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Nom"));
				colPrenomComptable.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Prénom"));
				tableComptable.setItems(data);
			 
			 }
			 
			 //initialise l'interface lors du lancement de l'application
			 
		public void initialize() 
		{
				 
			//Permet de rendre inutilisable les zones de texte lors du démarrage de l'interface 
			
			 remplissagetableViewGestionDesComptables(); 
			 txtNom.setDisable(true);
			 txtPrenom.setDisable(true);
			 txtAdresse.setDisable(true);
			 txtCP.setDisable(true);
			 txtVille.setDisable(true);
			 dpEmbauche.setDisable(true);
			 btnValider.setDisable(true);
				 
				 
				 
		
				 		
		  }
			 
		//Bouton permettant d'ajouter un comptable à la base de donnée
			 
		public void btnAjouterComptableClick(ActionEvent e) {
			
			
			//Permet de rendre les zones de texte utilsables quand en souhaite ajouter un comptable
			
		    txtNom.setDisable(false);
			txtPrenom.setDisable(false);
			txtAdresse.setDisable(false);
			txtCP.setDisable(false);
			txtVille.setDisable(false);
			dpEmbauche.setDisable(false);
			btnValider.setDisable(false);
			  
			String textFieldValue = txtNom.Text;
			  
		  }
		  
		  
		//Bouton Modifier
		  
		public void btnModifierComptableClick(ActionEvent e) {
			
			//Rendre utilisable les zones de texte quand on veut modifier un comptable
			
			txtNom.setDisable(false);
			txtPrenom.setDisable(false);
			txtAdresse.setDisable(false);
			txtCP.setDisable(false);
			txtVille.setDisable(false);
			dpEmbauche.setDisable(false);
			btnValider.setDisable(false);
			
			
			//Permet de modifier les information lié à un comptable
			
			
			try {
				PreparedStatement preparedStatement = DBConnex.getRS;
				
				String query = "UPDATE utilisateur SET nom =? , prenom=? , adresse=? , cp=? , ville =? , dateEmbauche =? ";
				preparedStatement = connexion.preparedStatement(query);
				preparedStatement.setString(1, txtNom.getText());
				preparedStatement.setString(2, txtPrenom.getText());
				preparedStatement.setString(3, txtAdresse.getText());
				preparedStatement.setString(4, txtCP.getText());
				preparedStatement.setString(5,  txtVille.getText());
				preparedStatement.setDate(6, dpEmbauche.getDate());
				
			}
			catch (SQLException e) {
				System.out.println("Impossible de changer les valeurs du Comptable");;
				e.printStackTrace();
			}
			
		}
		
		
		//Bouton Supprimer
		
		public void btnSupprimerComptableClick(ActionEvent e ) {
			
			try {
				PreparedStatement preparedStatement = DBConnex.getRS("DELETE FROM utilisateur WHERE nom =?, prenom =?");
				UtilisateurData Utilisateurdata = utilisateurtable.getSelectionModel().getSelectedItem();
				preparedStatement.setString(1, txtNom.getText);
				preparedStatement.setString(2, txtPrenom.getText);
				
				
				
			}
		}
			
		}
		
		//Bouton Quitter
		
		public void btnQuitterClick(ActionEvent e) {
			Platform.exit();
		}

		
	 



	
}