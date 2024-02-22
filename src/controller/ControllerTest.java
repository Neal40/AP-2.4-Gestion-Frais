package controller;

import java.sql.ResultSet; 
import java.sql.SQLException; 
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList; 
import javafx.event.ActionEvent; 
import javafx.fxml.FXML; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.control.TableColumn; 
import javafx.scene.control.TableView; 
import javafx.scene.control.cell.PropertyValueFactory; 
import model.DAO.DBConnex; 
import model.DAO.TypeFraisDAO; 
import model.DTO.TypeFrais; 



public class ControllerTest {
	//Déclaration des variables du fichier fxml associé
	@FXML private Label lblPrenom;
	@FXML private Label lblNom;
	@FXML private Label lblMessage;
	@FXML private Button btn1;
	@FXML private Button btn2;

	@FXML private TableView<TypeFrais> tableViewTypesFrais;
 	@FXML  private TableColumn<TypeFrais , String > colIdTypeFrais;
 	@FXML  private TableColumn<TypeFrais , String > colLibelleTypeFrais;
 	@FXML  private TableColumn<TypeFrais , Float > colMontant;

 	//Déclaration de l'ObservableList nécessaire au remplissage de la tableView
 	private ObservableList<TypeFrais> data = FXCollections.observableArrayList();
	//Méthode appelée lors de l'initialisation du contrôleur.
	public void initialize() {
	 lblMessage.setText("Bonjour - Cliquez sur un bouton");
	}
	
	@FXML protected void onBtn1Clik(ActionEvent e) { 
		ResultSet nom = DBConnex.getRS("select nom from utilisateur where statut = 'comptable'", DBConnex.connexion() );
		ResultSet prenom = DBConnex.getRS("select prenom from utilisateur where statut = 'comptable'", DBConnex.connexion() );
 	 	ResultSet rs = DBConnex.getRS("select count(*) as nbUtilisateurs  from utilisateur", DBConnex.connexion() );     	
 	 	try { 
 	 		lblMessage.setText("Nombre d'utilisateurs : " + String.valueOf(rs.getInt("nbUtilisateurs"))); 
 	 		lblNom.setText(String.valueOf(nom.getString("nom")));
 	 		lblPrenom.setText(String.valueOf(prenom.getString("prenom")));
 	 	} catch (SQLException e1) { 
 	 		lblMessage.setText("erreur : " + e1.getMessage());
 	 	} 
	}
	
	
	  
	  
	 @FXML protected void onBtn2Clik(ActionEvent e) { 
	 	  
	 	 ResultSet rsListeTypesFrais = TypeFraisDAO.getLesTypesFrais();   
	 	 if(rsListeTypesFrais!= null) { 	 	 	 	 	 	 	 
	 	 	 try { 
	 	 	 	rsListeTypesFrais.beforeFirst(); 
	 	 	 	data.clear(); 
	 	 	 	while (rsListeTypesFrais.next()) { 
	 	 	 	 	TypeFrais unTypeFrais = new TypeFrais(rsListeTypesFrais.getString(1), rsListeTypesFrais.getString(2), rsListeTypesFrais.getFloat(3));
	 	 	 	 	data.add(unTypeFrais); 
	 	 	 	} 
	 	 	 	 
	 	 	 	lblMessage.setText("Nombre de types de frais : " + String.valueOf(data.size())); 
	 	 	 	colIdTypeFrais.setCellValueFactory(new PropertyValueFactory<TypeFrais,String>("idTypeFrais"));
	 	 	 	colLibelleTypeFrais.setCellValueFactory(new PropertyValueFactory<TypeFrais,String>("libelleTypeFrais"));
	 	 	 	colMontant.setCellValueFactory(new PropertyValueFactory<TypeFrais,Float>("montantTypeFrais"));
	 	 	 	tableViewTypesFrais.setItems(data); 
	 	 	 } catch (SQLException e1) { 
	 	 	 	e1.printStackTrace(); 
	 	 	 } 	 	 	
	 	 }
	 }
}
	 
