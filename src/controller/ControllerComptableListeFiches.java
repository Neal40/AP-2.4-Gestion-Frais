package controller;

import java.io.IOException;
import java.util.List;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO.DBConnex;
import model.DAO.FicheFraisDAO;
import model.DTO.FicheFrais;

public class ControllerComptableListeFiches {

	/**
	 * Déclaration des variables du fichier FXML associé
	 */
	 @FXML 	private TableView<FicheFrais> tableListeFichesComptable;
	 @FXML 	private TableColumn<FicheFrais , Integer > colIDFiche;
	 @FXML 	private TableColumn<FicheFrais , String > colNomFiche;
	 @FXML 	private TableColumn<FicheFrais , String > colPrénomFiche;
	 @FXML 	private TableColumn<FicheFrais , Integer > colMoisFiche;
	 @FXML 	private TableColumn<FicheFrais , Integer > colAnnéeFiche;
	 @FXML  private TableColumn<FicheFrais , String > colEtatFiche;
	 @FXML  private TableColumn<FicheFrais , String > colMontantFiche;
	 @FXML  private Button btnValiderFiche;
	 @FXML  private Button btnQuitterValidation;
	 @FXML  private Button btnOuvrirFiche;
	 @FXML  private Button btnGestionVisiteurs;
	 @FXML  private Button btnQuitterGestionVisiteurs;
	 
	 /**
	  * Ouverture de la fiche sélectionnée
	  * Click sur le bouton "Ouvrir fiche s�lectionn�e"
	  * @param e
	  */
		@FXML	private void buttonOuvrirFicheClick(ActionEvent e) {
			
			int index = tableListeFichesComptable.getSelectionModel().getSelectedIndex();
			
			
			if(index >= 0) {
			
			FicheFrais uneFiche = tableListeFichesComptable.getSelectionModel().getSelectedItem();
						
		
			
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("S�lectionnez une fiche de frais");
				alert.getDialogPane().setContentText("Vous devez s�lectionner une fiche de frais afin de la visualiser");
				alert.showAndWait();
			}
		
				 		
		}
	 
	 
			
	/**
	* Remplissage de la tableView "liste des fiches de frais"
	*/
	private void remplissagetableViewListeFichesComptable() {
		List<FicheFrais> lesFichesFrais = FicheFraisDAO.lesFichesFrais();
		
		/*colIDFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("idFiche"));
		colNomFiche.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUtilisateur().getNom()));
		colPrénomFiche.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUtilisateur().getPrenom()));
		colMoisFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("mois"));
		colMontantFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,String>("montantDeclare"));
		colAnnéeFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,Integer>("annee"));
		colEtatFiche.setCellValueFactory(new PropertyValueFactory<FicheFrais,String>("etat"));*/
		
		//	tableListeFichesComptable.setItems(FXCollections.observableList(lesFichesFrais));
		
		// En fin de cours, j'ai mis en commentaire, maintenant j'ai eu l'accès à l'autre view.
	}
	 
	 public void btnGestionVisiteursClick(ActionEvent e) throws IOException {
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("../view/viewComptableGestionDesVisiteurs.fxml"));
		 Pane gestionnaireFicheLayout = (Pane) loader.load();
			
	 	 Scene gestionnaireListeFichesScene = new Scene(gestionnaireFicheLayout);
         Stage gestionnaireListeFichesStage = new Stage();
         gestionnaireListeFichesStage.setScene(gestionnaireListeFichesScene);
       		
         gestionnaireListeFichesStage.setTitle("GSB Gestion des frais - Comptable Fiches de frais");
         gestionnaireListeFichesStage.initModality(Modality.APPLICATION_MODAL);		 		
         gestionnaireListeFichesStage.show();
	 	}
	 
	 

	 /**
	  * Fermeture de la vue
	  * @param e
	  */
	 public void btnQuitterValidationClick(ActionEvent e) {
			
		Stage stage = (Stage) btnQuitterValidation.getScene().getWindow();
	    stage.close();
		
	 }
	 
	 public void btnQuitterGestionVisiteursClick(ActionEvent e) {
		
	 }
	 
	 
	
	 @FXML 	private AnchorPane paneId ;
	 
	 
	 /**
	  * Appel de la fonction de remplissage de la tableView "liste des fiches"
	  * initialisation de la vue 
	  */
	 public void initialize() {
		     	 
			remplissagetableViewListeFichesComptable(); 
			

			
	 } 
	 
	 public Integer btnValiderFicheClick(ActionEvent e) {
		 		 
		     String unIdFiche = null;
			String nouvelEtat = null;
			
			String requete = "update ficheFrais set idEtat = '" + nouvelEtat + "' where idFiche = " + unIdFiche ;
	                 
	         return DBConnex.noQuery(requete, DBConnex.connexion());

		 
	 }
	 



	
	 
	


				
	 
	
	 
	
				
			
			
		
	

	
	

}
