package controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DAO.DBConnex;
import model.DAO.FicheFraisDAO;
import model.DAO.UtilisateurDAO;
import model.DTO.FicheFrais;

import model.DTO.LigneFrais;
import model.DTO.Utilisateur;

public class ControllerVisiteurListeFiches{

	/**
	 * Déclaration des variables du fichier FXML associé
	 */
	 @FXML 	private TableView<FicheFrais> tableListeFichesVisiteur;
	 @FXML 	private TableColumn<FicheFrais , Integer > colIdFiche;
	 @FXML 	private TableColumn<FicheFrais , String > colNomVisiteur;
	 @FXML 	private TableColumn<FicheFrais , Integer > colMoisFiche;
	 @FXML  private TableColumn<FicheFrais , String > colEtatFiche;
	 @FXML  private Button btnCloseListeFichesVisiteur;
	 
	
	 
	 //Déclaration de l'ObservableList nécessaire au remplissage de la tableView
	 private ObservableList<FicheFrais> data = FXCollections.observableArrayList();
	 
	 
	 /**
	  * Ouverture de la fiche sélectionnée
	  * Click sur le bouton "Ouvrir fiche"
	  * @param e
	  */
		@FXML	private void btnOuvrirFicheVisiteurClick(ActionEvent e) {
			
			int index = tableListeFichesVisiteur.getSelectionModel().getSelectedIndex();
			
			if(index >= 0) {
			
				FicheFrais uneFiche = tableListeFichesVisiteur.getSelectionModel().getSelectedItem();
						
		
			
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Sélectionnez une fiche de frais");
				alert.getDialogPane().setContentText("Vous devez sélectionner une fiche de frais afin de la visualiser");
				alert.showAndWait();
			}	 		
		}
		
		
		/**
		  * Suppression de la fiche sélectionnée
		  * Click sur le bouton "Supprimer fiche "
		  * @param e
		  */
			@FXML	private void btnSupprimerFicheClick(ActionEvent e) {
				int index = tableListeFichesVisiteur.getSelectionModel().getSelectedIndex();
				
				int idFiche = colIdFiche.getCellData(index);
				
				if(index >= 0) {			
					Integer sup = DBConnex.noQuery("DELETE FROM fichefrais WHERE id = "+idFiche, DBConnex.connexion());
						
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText("Sélectionnez une fiche de frais");
					alert.getDialogPane().setContentText("Vous devez sélectionner une fiche de frais afin de la supprimer");
					alert.showAndWait();
				}
			}
			
	 /**
	  * Remplissage le la tableView "liste des fiches de frais"
	  */
	 private void remplissagetableViewListeFichesVisiteur() {
		 try {
			 			 
			 /*ResultSet rsListeFiches = FicheFraisDAO.lesFichesFrais();
			 
			if(rsListeFiches!= null) {
					
				ArrayList<LigneFrais> lesLignesFrais;
				rsListeFiches.beforeFirst();
				
				while (rsListeFiches.next()) {
					ResultSet rsUnVisiteur = UtilisateurDAO.unUtilisateur(rsListeFiches.getString(2));			 
					Utilisateur utilisateur = new Utilisateur(rsUnVisiteur.getString(1), rsUnVisiteur.getString(2)  , rsUnVisiteur.getString(3)   , rsUnVisiteur.getString(4)  , rsUnVisiteur.getString(5) , rsUnVisiteur.getString(6) , rsUnVisiteur.getString(7) , rsUnVisiteur.getString(8)  , rsUnVisiteur.getString(9), rsUnVisiteur.getDate(10)) ;				
					FicheFrais uneFicheFrais = new FicheFrais(rsListeFiches.getInt(1) ,rsListeFiches.getInt(3) ,rsListeFiches.getInt(4)  ,rsListeFiches.getDate(5)  ,rsListeFiches.getDate(6)  ,rsListeFiches.getString(7) , rsListeFiches.getFloat(8), utilisateur);
				 
					ResultSet rsLesLignes = FicheFraisDAO.lesLignesFicheFrais(rsListeFiches.getInt(1));
					lesLignesFrais = new ArrayList<LigneFrais>();
					if(rsLesLignes != null){		
						rsLesLignes.beforeFirst();
						while (rsLesLignes.next()) {
							LigneFrais uneLigneFrais = new LigneFrais(rsLesLignes.getInt(1),rsLesLignes.getString(2) ,rsLesLignes.getInt(3), rsLesLignes.getString(5), rsLesLignes.getFloat(6),rsLesLignes.getInt(4));								lesLignesFrais.add(uneLigneFrais);
						}
					}
					uneFicheFrais.setLesLignes(lesLignesFrais); 				 
					data.add(uneFicheFrais);
				}
			}
			*/
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 
		colIdFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("idFiche"));
		colMoisFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("moisAnnee"));
		colNomVisiteur.setCellValueFactory(new PropertyValueFactory<FicheFrais,String>("nomPrenomUtilisateur"));
		colEtatFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,String>("etatLong"));
		
		tableListeFichesVisiteur.setItems(data);
	 
	 }
	 
	 

	 /**
	  * Fermeture de la vue
	  * @param e
	  */
	 public void btnQuitterListeFichesVisiteurClick(ActionEvent e) {
			
		Stage stage = (Stage) btnCloseListeFichesVisiteur.getScene().getWindow();
	    stage.close();
		
	 }
	
	 @FXML 	private AnchorPane paneId ;
	 
	 
	 /**
	  * Appel de la fonction de remplissage de la tableView "liste des fiches"
	  * initialisation de la vue 
	  */
	 public void initialize() {
		     	 
		 remplissagetableViewListeFichesVisiteur(); 
			
	 } 
	 



	
}
