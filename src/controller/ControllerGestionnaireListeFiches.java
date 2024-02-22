package controller;

import java.sql.ResultSet;


import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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

public class ControllerGestionnaireListeFiches
{

	/**
	 * Les variables du fichier FXML associ�
	 */
	 @FXML 	private TableView<FicheFrais> tableListeFichesGestionnaire;
	 @FXML 	private TableColumn<FicheFrais , Integer > colFiche;
	 @FXML 	private TableColumn<FicheFrais , String > colNomVisiteur;
	 @FXML 	private TableColumn<FicheFrais , Integer > colMois;
	 @FXML  private TableColumn<FicheFrais , String > colEtatFiche;
	 @FXML  private TableColumn<FicheFrais , String > colPrenom;
	 @FXML  private TableColumn<FicheFrais , Integer> colAnnees;
	 
	 @FXML  private Button buttonCloseListeFichesGestionnaire;
	 @FXML  private Button btnMettreEnPaiement;
	 @FXML  private Button btnOuvrirFiche;
	 
	 @FXML  private Label  lblNom;
	 @FXML  private Label  lblProfil;
	 @FXML  private Label  lblFiches;
	 
	 
	
	 
	 //Déclaration de l'ObservableList nécessaire au remplissage de la tableView
	 private ObservableList<FicheFrais> data = FXCollections.observableArrayList();
	 
	 
	 /**
	  * Ouverture de la fiche sélectionnée
	  * Click sur le bouton "Ouvrir fiche s�lectionn�e"
	  * @param e
	  */
		@FXML	private void buttonOuvrirFicheGestionnaireClick(ActionEvent e) 
		{
			
			int index = tableListeFichesGestionnaire.getSelectionModel().getSelectedIndex();
			
			
			if(index >= 0)
			{
			
			// FicheFrais uneFiche = tableListeFichesGestionnaire.getSelectionModel().getSelectedItem();
						
			
			
			}
			else 
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("S�lectionnez une fiche de frais");
				alert.getDialogPane().setContentText("Vous devez s�lectionner une fiche de frais afin de la visualiser");
				alert.showAndWait();
			}
		
				 		
		}
	 
	 
			
	 /**
	  * Remplissage le la tableView "liste des fiches de frais"
	  */
	 private void remplissagetableViewListeFichesGestionnaire() 
	 {
		 try
		 {
			 			 
<<<<<<< HEAD
			 ResultSet rsListeFiches = FicheFraisDAO.lesFichesFrais();
=======
			 ResultSet rsListeFiches = (ResultSet) FicheFraisDAO.lesFichesFrais();
>>>>>>> b42b19ecaa93a7eec3f3151edc8b96abe0df5323
			 
			if(rsListeFiches!= null)
			{
				
				ArrayList<LigneFrais> lesLignesFrais;
				rsListeFiches.beforeFirst();
				
<<<<<<< HEAD
				while (rsListeFiches.next()) 
				{
					ResultSet rsUnVisiteur = UtilisateurDAO.unUtilisateur(rsListeFiches.getString(2));			 
=======
				while (rsListeFiches.next()) {
					ResultSet rsUnVisiteur = (ResultSet) UtilisateurDAO.unUtilisateur(rsListeFiches.getString(2));			 
>>>>>>> b42b19ecaa93a7eec3f3151edc8b96abe0df5323
					Utilisateur utilisateur = new Utilisateur(rsUnVisiteur.getString(1), rsUnVisiteur.getString(2)  , rsUnVisiteur.getString(3)   , rsUnVisiteur.getString(4)  , rsUnVisiteur.getString(5) , rsUnVisiteur.getString(6) , rsUnVisiteur.getString(7) , rsUnVisiteur.getString(8)  , rsUnVisiteur.getString(9), rsUnVisiteur.getDate(10)) ;				
					FicheFrais uneFicheFrais = new FicheFrais(rsListeFiches.getInt(1) ,rsListeFiches.getInt(3) ,rsListeFiches.getInt(4)  ,rsListeFiches.getDate(5)  ,rsListeFiches.getDate(6)  ,rsListeFiches.getString(7) , rsListeFiches.getFloat(8), utilisateur);
				 
					ResultSet rsLesLignes = (ResultSet) FicheFraisDAO.lesLignesFicheFrais(rsListeFiches.getInt(1));
					lesLignesFrais = new ArrayList<LigneFrais>();
					if(rsLesLignes != null)
					{		
						rsLesLignes.beforeFirst();
						while (rsLesLignes.next()) 
						{
							LigneFrais uneLigneFrais = new LigneFrais(rsLesLignes.getInt(1),rsLesLignes.getString(2) ,rsLesLignes.getInt(3), rsLesLignes.getString(5), rsLesLignes.getFloat(6),rsLesLignes.getInt(4));
							lesLignesFrais.add(uneLigneFrais);
						}
					}
					uneFicheFrais.setLesLignes(lesLignesFrais); 				 
					data.add(uneFicheFrais);
				}
			}
			 
			 
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
		 
		 
		colFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("idFiche"));
		colMois.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("moisAnnee"));
		colNomVisiteur.setCellValueFactory(new PropertyValueFactory<FicheFrais,String>("nomPrenomUtilisateur"));
		colEtatFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,String>("etatLong"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<FicheFrais,String>("nomPrenomUtilisateur"));
		colAnnees.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("moisAnnee"));
		tableListeFichesGestionnaire.setItems(data);
	 
	 }
	 
	 //Bouton permettant d'aller vers la vue gestion des comptable gérer par le gestionnaire 
	 
	 public void btnGestionComptableClick(ActionEvent e) {
		 
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("../view/GestionDesComptable.fxml"));
	 }
	 
	 
	 /**
	  * Fermeture de la vue
	  * @param e
	  */
	 
	 
	 
	 public void buttonCloseListeFichesGestionnaireClick(ActionEvent e) 
	 {
			
		 Stage stage = (Stage) buttonCloseListeFichesGestionnaire.getScene().getWindow();
	     stage.close();
	 }
	
	 @FXML 	private AnchorPane paneId ;
	 
	 
	 /**
	  * Appel de la fonction de remplissage de la tableView "liste des fiches"
	  * initialisation de la vue 
	  */
	 public void initialize() 
	 {
		     	 
		 remplissagetableViewListeFichesGestionnaire(); 
		 
			

			
	 } 
	 
	 protected void btnMettreEnPaiementClick(ActionEvent e) 
	 {
		 ResultSet rs = DBConnex.getRS("update fichefrais set idEtat = mise en paiement where idEtat = validee", DBConnex.connexion());
		 
		 
	 }
	 
	 
	 


	

	 
	 
	 public void refreshtableListeFichesComptable()
	 {	 
		 
		 tableListeFichesGestionnaire.refresh();		
		
	 }
 
	 
	 protected void btnGestionComptableClick(ActionEvent e) 
	 {
			
			try 
			{
				
				
				//Traitement de la réponse du SGBD
				//Chargement des différentes vues en fonction du statut de la personne authentifiée.
		
				if(rs != null)
				{
					 FXMLLoader loader = new FXMLLoader();
					 messageConnexionLabel.setText("");
				 
				 
					if(rs.getNString("statut").equals("comptable")) 
					{
						loader.setLocation(Main.class.getResource("../view/viewComptableListeFiches.fxml"));
				 		Pane comptableListeFichesLayout = (Pane) loader.load(); 		
				 		Scene comptableListeFichesScene = new Scene(comptableListeFichesLayout);
		            	Stage comptableListeFichesStage = new Stage();
				 		comptableListeFichesStage.setScene(comptableListeFichesScene);
		           		
				 		comptableListeFichesStage.setTitle("GSB Gestion des frais - Comptable Fiches de frais");
				 		comptableListeFichesStage.initModality(Modality.APPLICATION_MODAL);		 		
				 		comptableListeFichesStage.show();
					}
				}
			}
	 }
		 
	 
					

}
