/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jeu;

import utils.MyDB;
import entities.Jeu;
import entities.Tournoi;
import services.JeuService;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chahine kouki
 */
public class ListJeuController implements Initializable {

    
    
    @FXML
    private Button CreateT;
    @FXML
    private TableColumn<Jeu,String> nomJeu;

    @FXML
    private TableView<Jeu> TableJeu; 
    
    
    ObservableList<Jeu> jeuList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Jeu, Button> updateJeu;
    @FXML
    private TableColumn<Jeu, Button> deleteJeu;
    @FXML
    private Button UpdateJeu;
    @FXML
    private Button DeleteJeu;
    @FXML
    private ImageView logo;
   
 
    @FXML
    private Button CreateJeu;
    @FXML
    private AnchorPane manePane;
 
 String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
 Jeu jeu = null;
  
   
 JeuService js = new JeuService();
    private boolean maj; 
    
    
    private void executeQuery(String query) {
       Connection cnx = MyDB.getInstance().getConnexion();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
              nomJeu.setCellValueFactory(new PropertyValueFactory<>("nom"));
              deleteJeu.setCellValueFactory(new PropertyValueFactory<>("delete"));
        updateJeu.setCellValueFactory(new PropertyValueFactory<>("update"));
                     refresh();
        
    }    

    private void  refresh() {
         JeuService js = new JeuService();
         List<Jeu> jeux = js.afficherJeu();         
        TableJeu.getItems().clear();
        TableJeu.getItems().addAll(jeux);
    }

    @FXML
    private void CreateTF(ActionEvent event) {
        refresh();
    }

    @FXML
    private void UpdateJeu(ActionEvent event) throws IOException {
       
         FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getResource("jeu.fxml"));
         jeu = TableJeu.getSelectionModel().getSelectedItem();
         if( jeu !=null) {
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("jeu.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.err.println(ex);
                            }
                            
                            JeuController jeuController = loader.getController();
                            jeuController.setUpdate(true);
                            jeuController.setTextField(jeu.getNom(),jeu.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

        } else {
     JOptionPane.showMessageDialog(null, "Veuillez selectionner le jeu à modifier");   
         }
         
    }
    

    @FXML
    private void DeleteJeu(ActionEvent event) {

        ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
  if (TableJeu.getSelectionModel().getSelectedItem() != null) {
        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous supprimé ce jeu !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
          
                JOptionPane.showMessageDialog(null, "jeu Supprimee");
                js.supprimerJeu(TableJeu.getSelectionModel().getSelectedItem().getId());
                List<Jeu> jeux =  js.afficherJeu();
         
        TableJeu.getItems().clear();
        TableJeu.getItems().addAll(jeux);

            }
            
            refresh();
       
    } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le jeu à supprimer");    
            }
    }

    @FXML
    private void CreateJeu(ActionEvent event) throws IOException {
        
          
    
        
        FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getResource("jeu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ajoutJeu");
        stage.setScene(new Scene(root1));
        stage.show();  
    }

    @FXML
    private void CreateTF(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getResource("jeu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ajoutJeu");
        stage.setScene(new Scene(root1));
        stage.show();  
    }


    
}
