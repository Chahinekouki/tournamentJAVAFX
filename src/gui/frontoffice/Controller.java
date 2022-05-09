package gui.frontoffice;

import boutique.Controller.CommentaireController;
import gui.frontoffice.*;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.SessionUser;

/**
 *
 * @author Aymen Laroussi
 */
public class Controller implements Initializable {

    private AnchorPane pane1;
    private ImageView exit;
    @FXML
    private BorderPane borderpane;
    @FXML
    private JFXButton commandesBtn;
    @FXML
    private JFXButton connexion;

   
    

  

    

    @Override
    
    public void initialize(URL location, ResourceBundle resources) {       
        loadUI("/gui/frontoffice/home.fxml");
        if(SessionUser.getInstance().getEmail() != null){
            connexion.setVisible(false);
        }
        
   }

    private void categorie(ActionEvent event) {
    }

    @FXML
    private void acceuil (ActionEvent event) {
        loadUI("/gui/frontoffice/home.fxml");
    }


    @FXML
    private void boutique(ActionEvent event) {
        
        loadUI("/boutique/views/market.fxml");
        
    }
    @FXML
    private void tournoi(ActionEvent event) {
         loadUI("/gui/tournoii/tournois.fxml");       
    }
    
    private void loadUI(String ui) {
    Parent root = null;
    try{
        root = FXMLLoader.load(getClass().getResource(ui));
        
   } catch (IOException ex) {
               Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
           }
    borderpane.setCenter(root);
    }
    
  



    

    @FXML
    private void evenemet(ActionEvent event) {
    }

    @FXML
    private void connexion(ActionEvent event) {
        try{
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/user/LoginPFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Gérer les utilisateurs");
                            stage.show();
                            } catch(IOException e) {
                                System.out.println(e);
                              }
    }
    }
   

