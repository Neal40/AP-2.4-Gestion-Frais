package controller;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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

public class ControllerFicheFrais {
	/**
	 * Déclaration des variables du fichier FXML associé
	 */
	@FXML private Label nomVisiteur;
	@FXML private TextField mois;
	@FXML private TextField annee;
	@FXML private Label etatFiche;
	@FXML private TextField quantiteNuitees;
	@FXML private TextField quantiteRepas;
	@FXML private TextField quantiteKmParcourus;
	@FXML private TextField quantiteForfaitsEtape;
	@FXML private TextField montantDeclaree;
	
	/**
	  * Ouverture de la fiche sélectionnée
	  * Click sur le bouton "Ouvrir fiche"
	  * @param e
	  */
	@FXML	private void btnEnregistrerClick(ActionEvent e) {
		DBConnex.noQuery("INSERT INTO `fichefrais`(`idUtilisateur`, `mois`, `annee`, `dateCreation`, `idEtat`, `montantDeclare`) VALUES ('f21','"+mois+",'"+annee+"','"+LocalDate.now()+"','EC','"+montantDeclaree+"');", DBConnex.connexion());
		
		ResultSet idFicheEnCours = DBConnex.getRS("SELECT idFiche FROM FICHEFRAIS WHERE dateCreation ='"+LocalDate.now()+"';", DBConnex.connexion());
		
		DBConnex.noQuery("INSERT INTO `lignefrais`(`idFiche`, `idTypeFrais`, `quantiteDeclaree`) VALUES ('"+idFicheEnCours+"','NUI','"+quantiteNuitees+"');", DBConnex.connexion());
		DBConnex.noQuery("INSERT INTO `lignefrais`(`idFiche`, `idTypeFrais`, `quantiteDeclaree`) VALUES ('"+idFicheEnCours+"','REP','"+quantiteRepas+"');", DBConnex.connexion());
		DBConnex.noQuery("INSERT INTO `lignefrais`(`idFiche`, `idTypeFrais`, `quantiteDeclaree`) VALUES ('"+idFicheEnCours+"','KM','"+quantiteKmParcourus+"');", DBConnex.connexion());
		DBConnex.noQuery("INSERT INTO `lignefrais`(`idFiche`, `idTypeFrais`, `quantiteDeclaree`) VALUES ('"+idFicheEnCours+"','ETP','"+quantiteForfaitsEtape+"');", DBConnex.connexion());
		
		
	}
	
	public void initialize() {
		
	}
	
}
